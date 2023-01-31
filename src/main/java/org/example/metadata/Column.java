package org.example.metadata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Column {

    public String name;
    public String type;
    public int size;

    public static ArrayList<Column> getColumn(Connection conn, String Schema, String tableName) throws SQLException {
        ArrayList<Column> columns = new ArrayList<>();
        ResultSet rs = conn.getMetaData().getColumns(null, Schema, tableName, "%");

        while(rs.next()) {
            Column temp = new Column();
            temp.name = rs.getString("COLUMN_NAME");
            temp.type = rs.getString("TYPE_NAME");
            temp.size = rs.getInt("COLUMN_SIZE");
            System.out.println("Column Name : " + temp.name + "  Column Type : " + temp.type + "  Column Size : " + temp.size);
            columns.add(temp);
        }

        return columns;
    }
}
