package org.example.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableInfo {

    public String name;
    public static ArrayList<String> getTableInfo(Connection conn, String Schema) throws SQLException{

        ArrayList<String> tableInfo = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getTables(null, Schema, null, null);
        while(rs.next()) {
            String table = rs.getString("TABLE_NAME");
            tableInfo.add(table);
        }
        rs.close();
        return tableInfo;
    }
}
