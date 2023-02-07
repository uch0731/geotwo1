package org.example.database;

import org.example.metadata.ColumnInfo;
import org.example.metadata.ConnectManager;
import org.example.txt.CreateTxt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class DataInput {
    //데이터 입력
    public static void insertIntoTable(String tableName, ArrayList<ColumnInfo> columnsInfoFromTable,
                                       ArrayList<ArrayList<String>> data) throws SQLException {
        boolean error = false;
        ConnectManager manager = ConnectManager.getInstance();
        PreparedStatement pst = null;
        String s = " values (" + "?,".repeat(columnsInfoFromTable.size() - 1) + "?)";

        manager.getConn().setAutoCommit(false);
        pst = manager.getConn().prepareStatement("insert into " + tableName + s);
        int[] columnSequence = new int[columnsInfoFromTable.size()];

        for (int i = 0; i < columnSequence.length; i++) {
            columnSequence[i] = data.get(0).indexOf(columnsInfoFromTable.get(i).getName());
        }
        System.out.println(Arrays.toString(columnSequence));

        for (int i = 1; i < data.size(); i++) {
            try {
                for (int j = 0; j < columnSequence.length; j++) {
                    try {
                        if (columnsInfoFromTable.get(j).getType().equals("VARCHAR2")) {
                            boolean isNumeric = data.get(i).get(columnSequence[j]).chars().allMatch(Character::isDigit);
                            if (isNumeric) {
                                throw new Exception("error");
                            } else {
                                pst.setString(j + 1, data.get(i).get(columnSequence[j]));
                            }
                        } else if (columnsInfoFromTable.get(j).getType().equals("NUMBER")) {
                            pst.setInt(j + 1, Integer.parseInt(data.get(i).get(columnSequence[j])));

                        } else if (columnsInfoFromTable.get(j).getType().equals("DATE")) {
                            pst.setDate(j + 1, java.sql.Date.valueOf(data.get(i).get(columnSequence[j])));
                        }
                    } catch (Exception e) {
                        error = true;
                        CreateTxt errorTxt = CreateTxt.getInstance();
                        //실패 row and column
                        int errorRow = i+1, errorCol = columnSequence[j]+1;
                        String errorLocation = "행: " + errorRow + " 열: " + errorCol;
                        errorTxt.createTxt(errorLocation);
                        System.out.println(errorLocation);
                        throw new Exception();
                    }
                }
                pst.addBatch();

                if ((i % 10) == 0) {
                    pst.executeBatch();
                    pst.clearBatch();
                    manager.getConn().commit();
                }
            }catch (Exception e){
                System.out.println("롤백");
                manager.getConn().rollback();
            }
        }
        pst.executeBatch();
        manager.getConn().commit();

        pst.close();
    }
}
