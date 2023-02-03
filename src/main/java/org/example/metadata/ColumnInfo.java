package org.example.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ColumnInfo {
    public String name;
    public String type;
    public int size;

//    public static ArrayList<ColumnInfo> getColumnInfo(Connection conn, String Schema, String tableName) throws SQLException {
//        ArrayList<ColumnInfo> columns = new ArrayList<>();
//        tableName = tableName;
//        ResultSet rs = conn.getMetaData().getColumns(null, Schema, tableName, "%");
//
//        while(rs.next()) {
//            ColumnInfo temp = new ColumnInfo();
//            temp.name = rs.getString("COLUMN_NAME");
//            temp.type = rs.getString("TYPE_NAME");
//            temp.size = rs.getInt("COLUMN_SIZE");
//            columns.add(temp);
//        }
//        rs.close();
//        return columns;
//    }

    public static HashMap<String, ArrayList<ColumnInfo>> getColumnInfo(Connection conn, String Schema, String tableName) throws SQLException {
        HashMap<String, ArrayList<ColumnInfo>> tableAndColumn = new HashMap<>();
        ArrayList<ColumnInfo> columns = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getColumns(null, Schema, tableName, "%");

        while(rs.next()) {
            ColumnInfo temp = new ColumnInfo();
            temp.name = rs.getString("COLUMN_NAME");
            temp.type = rs.getString("TYPE_NAME");
            temp.size = rs.getInt("COLUMN_SIZE");
            columns.add(temp);
        }
        tableAndColumn.put(tableName,columns);
        rs.close();
        return tableAndColumn;
    }
}
