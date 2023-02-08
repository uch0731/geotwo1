//package org.example.database;
//
//import org.example.metadata.ColumnInfo;
//import org.example.metadata.ConnectManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
////data 추출
//public class DataExtraction {
//
//    //모든 데이터 가져오기
//    public static  ArrayList<ArrayList<String>> selectAllFromTable
//    (String tableName, HashMap<String, ArrayList<ColumnInfo>> tableAndColumn) throws SQLException {
//        ConnectManager manager = ConnectManager.getInstance();
//        ArrayList<ArrayList<String>> data = new ArrayList<>();
//
//        String query = "SELECT * FROM " + tableName;
//        PreparedStatement pst = manager.getConn().prepareStatement(query);
//        ResultSet rs = pst.executeQuery();
//
//        while(rs.next()) {
//            ArrayList<String> temp = new ArrayList<>();
//            for(int i =0; i< tableAndColumn.get(tableName).size(); i++) {
//                String tempData = rs.getString(i+1);
//                temp.add(tempData);
//            }
//            data.add(temp);
//        }
//    rs.close();
//    pst.close();
//    return data;
//    }
//}
