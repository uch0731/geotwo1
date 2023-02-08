package org.example.metadata;

import org.apache.poi.ss.usermodel.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//
public class ConnectManager {

    private static ConnectManager instance;
    private Connection conn;
    private String host;
    private String id;
    private String pw;


    //싱글톤 방식
    public static ConnectManager getInstance() {
        if (instance == null) {
            synchronized(ConnectManager.class) {
                instance = new ConnectManager();
            }
        }
        return instance;
    }

    //DB연결
    public void connectDB (DatabaseType type){
        String dbUrl = type.getStartUrl() + this.host + type.getEndUrl();
        try {
            Class.forName(type.getDriver());
            System.out.println("드라이버 검색 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 검색 실패");
        }

        try {
            this.conn = DriverManager.getConnection(dbUrl, this.id, this.pw);
            System.out.println("데이터베이스 연결 성공");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패(dbUrl, id, pw 확인)");
        }

    }

    //table 정보 가져와 set
    public void setTableInfo( TableInfo tablesInfo, String Schema) throws SQLException{

        ArrayList<String> names = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getTables(null, Schema, null, null);
        while(rs.next()) {
            String table = rs.getString("TABLE_NAME");
            names.add(table);
        }
        rs.close();
        tablesInfo.setTableNames(names);
    }

    //column 정보 가져와 set
    public void setTableAndColumn(TableInfo tableInfo, ColumnInfo columnInfo, String Schema, String tableName) throws SQLException {
        ArrayList<ColumnInfo> columns = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getColumns(null, Schema, tableName, "%");

        while(rs.next()) {
            ColumnInfo temp = new ColumnInfo();
            temp.setName(rs.getString("COLUMN_NAME"));
            temp.setType(rs.getString("TYPE_NAME"));
            temp.setSize(rs.getInt("COLUMN_SIZE"));
            columns.add(temp);
        }
        tableInfo.setTableAndColumn(tableName,columns);
        columnInfo.setTableAndColumn(tableName, columns);
        rs.close();
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Connection getConn() {
        return conn;
    }
}
