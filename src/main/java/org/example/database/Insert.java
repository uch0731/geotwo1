package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Insert {
    public static void insertToTable(Connection conn, String tableName, ArrayList<ArrayList<String>> data) throws SQLException {

        String s = " values (" + "?,".repeat(data.get(0).size()-1) + "?)";
        PreparedStatement pstm = conn.prepareStatement("insert into " + tableName + s);

        for(int i =0; i<data.size(); i++) {
            for(int j =0; j<data.get(i).size(); j++) {
                pstm.setString(j+1, data.get(i).get(j));
            }
            pstm.addBatch();
        }
        pstm.executeBatch();
        pstm.close();
    }
}
