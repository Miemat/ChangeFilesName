package sample;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUtils {

    public ArrayList<File> listFilesForFolder(final File folder) {
        ArrayList<File> fileFromFolder = new ArrayList<File>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                fileFromFolder.add(fileEntry);
            }
        }
        return fileFromFolder;
    }

    public String createDate(Date date) throws ParseException {
        String date_s = date.toString();

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy_MM_dd'_at_'hh_mm_ss");

        System.out.println("Current Date: " + ft.format(date));
        date_s = ft.format(date);

        return date_s;
    }
}
