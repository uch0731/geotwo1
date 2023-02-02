package org.example.database;

import org.example.metadata.ColumnInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectAll {
    public static  ArrayList<ArrayList<String>> selectAllFromTable(Connection conn, String tableName, ArrayList<ColumnInfo> columnsInfo) throws SQLException {

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        String query = "SELECT * FROM " + tableName;
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();

        while(rs.next()) {
            ArrayList<String> temp = new ArrayList<>();
            for(int i =0; i< columnsInfo.size(); i++) {
                String tempData = rs.getString(i+1);
                temp.add(tempData);
            }
            data.add(temp);
        }
        rs.close();
        pstm.close();
        return data;
    }
}
