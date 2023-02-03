package org.example.metadata;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

//table 정보
public class TableInfo {

    private ArrayList<String> tableNames;
//    private HashMap<String, ArrayList<ColumnInfo>> tableAndColumn = new HashMap<>();

    public ArrayList<String> getTableNames(){
        return this.tableNames;
    }

    public void setTableNames( ArrayList<String> names) throws SQLException{

        tableNames = names;
    }

//    public void setTableNames(ConnectManager manager, String Schema) throws SQLException{
//
//        tableNames = manager.getTableInfo(Schema);
//    }

//    public HashMap<String, ArrayList<ColumnInfo>> getTableAndColumn() {
//        return this.tableAndColumn;
//    }
//
//    public void setTableAndColumn(String tableName, ArrayList<ColumnInfo> columnInfo) {
//        tableAndColumn.put(tableName,columnInfo);
//    }

}
