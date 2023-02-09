package org.example.metadata;

import org.apache.poi.ss.usermodel.Table;
import org.example.database.DatabaseManager;
import org.example.helper.EncryptUtil;
import org.example.user.UserInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//
public class ConnectManager {

    private Connection conn;
    private UserInfo userInfo;

    public ConnectManager(UserInfo userInfo) throws Exception{
        this.userInfo = userInfo;
//        _checkParam();
    }

//    private void _checkParam() throws Exception {
//        if (this.userNm == null
//            || this.userNm.trim().length() == 0){
//            throw new Exception("Param [UserNM] is empty");
//        }
//
//        if (this.userPW == null
//        || this.userPW.trim().length() ==0){
//            throw new Exception("Param [UserPW] is empty");
//        }
        
        // 암호화 / 복호화
        // 텍스트 -> 해쉬코드 작업
        // 사용자 이메일, 이름, 전화번호 : 특정 사용자를 지칭할 수 있는 정보는 암호화
        // AES256, SHA
        // 실무에서는 EncrytUtil 클래스를 만들어서 공동 활용
        //   _checkPW();


//        if (!EncryptUtil.checkUserPW(this.userPW)){
//            // 에러 로그 기록
//            throw new Exception("패쓰워드 이상해");
//        }
//    }

    //DB연결
    public void connectDB (DatabaseType type){
        String dbUrl = type.getStartUrl() + userInfo.getHost() + type.getEndUrl();
        try {
            Class.forName(type.getDriver());
            System.out.println("드라이버 검색 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 검색 실패");
        }

        try {
            this.conn = DriverManager.getConnection(dbUrl, userInfo.getUserNm(), userInfo.getUserPW());
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패(dbUrl, id, pw 확인)");
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
