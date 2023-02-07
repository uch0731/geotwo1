package org.example.txt;

import org.example.metadata.ConnectManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//txt 파일 만들기
public class CreateTxt {
    private static CreateTxt instance;
    private Date date;

    private CreateTxt(){
        this.date = new Date(System.currentTimeMillis());
    }

    public static CreateTxt getInstance()
    {
        if (instance == null)
        {
            synchronized(CreateTxt.class)
            {
                instance = new CreateTxt();
            }
        }

        return instance;
    }

    public void createTxt(String errorLocation) throws IOException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-z");
        String uploadTxtPath = "C:\\Users\\GEOTWO\\Desktop\\error_" + formatter.format(date) +".txt";

        File file = new File(uploadTxtPath);
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(errorLocation);
        writer.newLine();
        writer.flush();
        writer.close();
    }
}
