package org.example.database;

import org.example.metadata.ColumnInfo;
import org.example.metadata.ConnectManager;
import org.example.txt.CreateTxt;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;



public class DataInput {
    //데이터 입력
    public static void insertIntoTable(String tableName, ArrayList<ColumnInfo> columnsInfoFromTable,
                                       ArrayList<ArrayList<String>> data) throws SQLException, IOException {
        boolean error = false;
        ConnectManager manager = ConnectManager.getInstance();
        String s = " values (" + "?,".repeat(columnsInfoFromTable.size() - 1) + "?)";

        manager.getConn().setAutoCommit(false);
        PreparedStatement pst = manager.getConn().prepareStatement("insert into " + tableName + s);
        int[] columnSequence = new int[columnsInfoFromTable.size()];

        //칼럼 이름으로 칼럼 순서 맞추기
        for (int i = 0; i < columnSequence.length; i++) {
            columnSequence[i] = data.get(0).indexOf(columnsInfoFromTable.get(i).getName());
        }

        System.out.println(Arrays.toString(columnSequence));

        for (int i = 1; i < data.size(); i++) {
            for (int j = 0; j < columnSequence.length; j++) {
                try {
                    //type비교해서 맞으면 파라미터값 세팅, 틀리면 에러
                    if (columnsInfoFromTable.get(j).getType().equals("VARCHAR2")) {
                        boolean isNumeric = data.get(i).get(columnSequence[j]).chars().allMatch(Character::isDigit);
                        //문자열에 정수 들어온경우 체크
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
                } catch (Exception e) { // 에러 나왔을 경우 error text 만들어주기
                    error = true; //error flag true로 바꾸기
                    CreateTxt errorTxt = CreateTxt.getInstance();
                    //실패 row and column 전달
                    errorTxt.createTxt(i+1, columnSequence[j]+1);
                }
            }
            //error 발생시 insert문 add안함
            if(!error) {
                pst.addBatch();
                if ((i % 10) == 0) {
                    pst.executeBatch();
                    pst.clearBatch();
                }
            }
        }
        //error 발생시 커밋안함
        if(error) {
            System.out.println("ERROR ");
            manager.getConn().rollback();
        }else{
            System.out.println("Data Insert");
            pst.executeBatch();
            manager.getConn().commit();
        }

        pst.close();
    }
}
