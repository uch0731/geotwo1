package org.example.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tables {
    public static void getTables(Connection conn, String Schema) throws SQLException{

        ResultSet rs = conn.getMetaData().getTables(null, Schema, null, null);
        while(rs.next()) {
            String table = rs.getString("TABLE_NAME");
            System.out.println("Table Name : " + table);
        }

        rs.close();
    }
}
