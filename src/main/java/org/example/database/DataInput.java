package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataInput {
    //데이터 입력
    public static void insertIntoTable(Connection conn, String tableName, ArrayList<ArrayList<String>> data) throws SQLException {

        String s = " values (" + "?,".repeat(data.get(0).size()-1) + "?)";
        PreparedStatement pst = conn.prepareStatement("insert into " + tableName + s);

        for(int i =0; i<data.size(); i++) {
            for(int j =0; j<data.get(i).size(); j++) {
                pst.setString(j+1, data.get(i).get(j));
            }
            pst.addBatch();
        }
        pst.executeBatch();
        pst.close();
    }
}
