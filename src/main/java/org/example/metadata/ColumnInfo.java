package org.example.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

//column 정보
public class ColumnInfo {

    private HashMap<String, ArrayList<ColumnInfo>> tableAndColumn;
    private String name;
    private String type;
    private int size;

    public ArrayList<ColumnInfo> getColumInfo(String tableName){
        return tableAndColumn.get(tableName);
    }

    public HashMap<String, ArrayList<ColumnInfo>> getTableAndColumn() {
        return tableAndColumn;
    }
    public void setTableAndColumn(HashMap<String, ArrayList<ColumnInfo>> tableAndColumn){
        this.tableAndColumn = tableAndColumn;
    }

//    public void setTableAndColumn(ConnectManager manager, String Schema, String tableName) throws SQLException{
//        tableAndColumn = manager.getColumnInfo(Schema, tableName);
//    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
