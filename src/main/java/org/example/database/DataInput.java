package org.example.database;

import org.example.metadata.ConnectManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class DataInput {
    //데이터 입력
    public static void insertIntoTable(String tableName, ArrayList<ArrayList<String>> data) throws SQLException {
        ConnectManager manager = ConnectManager.getInstance();
        String s = " values (" + "?,".repeat(data.get(0).size()-1) + "?)";
        PreparedStatement pst = manager.getConn().prepareStatement("insert into " + tableName + s);

        for(int i =0; i<data.size(); i++) {
            for(int j =0; j<data.get(i).size(); j++) {
                pst.setString(j+1, data.get(i).get(j));
            }
            pst.addBatch();
            if( (i % 100) == 0){
                pst.executeBatch() ;
                pst.clearBatch();
            }
        }
        pst.executeBatch();
        pst.close();
    }
}
