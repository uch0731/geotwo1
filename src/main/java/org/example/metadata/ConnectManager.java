package org.example.metadata;


import org.example.database.DatabaseManager;
import org.example.helper.EncryptUtil;
import org.example.user.UserInfo;
import java.sql.*;
import java.util.ArrayList;

import static org.example.helper.EncryptUtil._checkParam;


//
public class ConnectManager {

    private Connection conn;
    private UserInfo userInfo;

    public ConnectManager(UserInfo userInfo) throws Exception{
        this.userInfo = userInfo;
//        _checkParam();
    }
    //DB연결
    public void connectDB (DatabaseType type) throws Exception {
        String dbUrl = type.getUrl().replace("[HOST]", userInfo.getHost())
                                    .replace("[PORT]", userInfo.getPort()+"")
                                    .replace("[SID]", userInfo.getSid());

        try {
            Class.forName(type.getDriver());
            System.out.println("드라이버 검색 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 검색 실패");
        }

        if(_checkParam(userInfo.getUserNm(),userInfo.getUserPW())){
            try {
                this.conn = DriverManager.getConnection(dbUrl, userInfo.getUserNm(), userInfo.getUserPW());
                System.out.println("데이터베이스 연결 성공");
            } catch (SQLException e) {
                System.out.println("데이터베이스 연결 실패(HOST,PORT,SID,ID,PW 확인)");
            }
        }else{
            throw new Exception("UserNm or UserPW is empty");
        }


    }

    //table 정보 가져와 set
    public ArrayList<String> getTableNameList() throws SQLException{

        ArrayList<String> tableNames = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getTables(null, userInfo.getSchema(), null, null);
        while(rs.next()) {
            String tableNm = rs.getString("TABLE_NAME");
            tableNames.add(tableNm);
        }
        rs.close();

        return tableNames;
    }


    //column 정보 가져와 set
    public TableInfo getTableInfo() throws SQLException {
        TableInfo table = new TableInfo();
        ArrayList<ColumnInfo> columns = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getColumns(null, userInfo.getSchema(), userInfo.getTableNm(), "%");

        while(rs.next()) {
            ColumnInfo temp = new ColumnInfo();
            temp.setName(rs.getString("COLUMN_NAME"));
            temp.setType(rs.getString("TYPE_NAME"));
            temp.setSize(rs.getInt("COLUMN_SIZE"));
            columns.add(temp);
        }

        table.setTableName(userInfo.getTableNm());
        table.setColumnInfo(columns);
        rs.close();
        return table;
    }

    //DB connection 주기
    public void giveConnToDB(DatabaseManager dbManager){
        dbManager.setConn(conn);
    }


    public void closeConnection() throws SQLException {
        try{
            if (conn != null
                || conn.isClosed())
            {
                conn.close();
            }
        } catch(Exception e){
            // 로그
        }
    }
}
