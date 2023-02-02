package org.example.metadata;

import java.sql.*;

public class Connect {

    public Connection conn;
    private String dbUrl;
    private String id;
    private String pw;

    public Connect(String dburl, String id, String pw) {
        this.conn =  null;
        this.dbUrl = dburl;
        this.id = id;
        this.pw = pw;
    }

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
}
