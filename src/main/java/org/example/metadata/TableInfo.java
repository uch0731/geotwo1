package org.example.metadata;

import java.util.HashMap;
import java.sql.SQLException;
import java.util.ArrayList;

//table 정보
public class TableInfo {

    private ArrayList<String> tableNames;
    private HashMap<String, ArrayList<ColumnInfo>> tableAndColumn = new HashMap<>();

    public ArrayList<String> getTableNames(){
        return this.tableNames;
    }

    public void setTableNames( ArrayList<String> names) throws SQLException{
        tableNames = names;
    }

    public HashMap<String, ArrayList<ColumnInfo>> getTableAndColumn() {
        return tableAndColumn;
    }

    public void setTableAndColumn(String tableName, ArrayList<ColumnInfo> columnInfo) {
        this.tableAndColumn.put(tableName, columnInfo);
    }
}
