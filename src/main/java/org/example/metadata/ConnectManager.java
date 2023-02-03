package org.example.metadata;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//
public class ConnectManager {

    public Connection conn;
    private String dbUrl;
    private String id;
    private String pw;

    public ConnectManager(String dbUrl, String id, String pw) {
        this.conn =  null;
        this.dbUrl = dbUrl;
        this.id = id;
        this.pw = pw;
    }

    //DB연결
    public void connectDB (String dbDriver){

        try {
            Class.forName(dbDriver);
            System.out.println("드라이버 검색 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 검색 실패");
        }

        try {
            this.conn = DriverManager.getConnection(this.dbUrl, this.id, this.pw);
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


//    public ArrayList<String> getTableInfo(String Schema) throws SQLException{
//
//        ArrayList<String> tableInfo = new ArrayList<>();
//        ResultSet rs = conn.getMetaData().getTables(null, Schema, null, null);
//        while(rs.next()) {
//            String table = rs.getString("TABLE_NAME");
//            tableInfo.add(table);
//        }
//        rs.close();
//        return tableInfo;
//    }

    //column 정보 가져와 set
    public void setColumnInfo(ColumnInfo columnInfo, String Schema, String tableName) throws SQLException {
        HashMap<String, ArrayList<ColumnInfo>> tableAndColumn = new HashMap<>();
        ArrayList<ColumnInfo> columns = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getColumns(null, Schema, tableName, "%");

        while(rs.next()) {
            ColumnInfo temp = new ColumnInfo();
            temp.setName(rs.getString("COLUMN_NAME"));
            temp.setType(rs.getString("TYPE_NAME"));
            temp.setSize(rs.getInt("COLUMN_SIZE"));
            columns.add(temp);
        }
        tableAndColumn.put(tableName,columns);
        rs.close();
        columnInfo.setTableAndColumn(tableAndColumn);
    }

//    public HashMap<String, ArrayList<ColumnInfo>> getColumnInfo(String Schema, String tableName) throws SQLException {
//        HashMap<String, ArrayList<ColumnInfo>> tableAndColumn = new HashMap<>();
//        ArrayList<ColumnInfo> columns = new ArrayList<>();
//        ResultSet rs = conn.getMetaData().getColumns(null, Schema, tableName, "%");
//
//        while(rs.next()) {
//            ColumnInfo temp = new ColumnInfo();
//            temp.setName(rs.getString("COLUMN_NAME"));
//            temp.setType(rs.getString("TYPE_NAME"));
//            temp.setSize(rs.getInt("COLUMN_SIZE"));
//            columns.add(temp);
//        }
//        tableAndColumn.put(tableName,columns);
//        rs.close();
//        return tableAndColumn;
//    }
}
