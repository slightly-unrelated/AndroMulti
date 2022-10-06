package java.main.AndroMulti.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.main.AndroMulti.ui.main.SharedPrefIO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CSVReaderWriter {

    private final LocalDate localDate;

    public CSVReaderWriter() {
        Date date = new Date();
        localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String readPreviousCSV(@NonNull Context context) {
        return "";
        //TODO do implement reading of previous CSV
    }

    public String readCurrentCSV(@NonNull Context context) {
        return SharedPrefIO.getCurrentCSV(context);
    }

    public void writeToCSV(@NonNull Context context, String sBody, Boolean append) {
        File mydir = new File(context.getExternalFilesDir(
                        localDate.getYear() + File.separator + localDate.getMonth().toString() + File.separator)
                .getAbsolutePath());
        if (!mydir.exists()) {
            mydir.mkdirs();
        }
        try {
            File gpxfile = new File(mydir, localDate + ".csv");
            FileWriter writer = new FileWriter(gpxfile, append);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(sBody);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
