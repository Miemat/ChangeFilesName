package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    @FXML
    Button changeName;

    @FXML
    TextField pathId;

    @FXML
    Label resultId;

    public Controller() {

        changeName = new Button();
        pathId = new TextField();
        resultId = new Label();
    }


    public void changeName(javafx.event.ActionEvent actionEvent) {
        System.out.println("Start change Name");

        if(pathId != null && !pathId.equals("")){
            File directory = new File(pathId.getText());
            if(directory.isDirectory()){
                resultId.setText("Poprawna scieżka do folderu");
                changeName(directory);
            }else{
                resultId.setText("Błędna scieżka folderu");
            }
        }
    }


    private void changeName(File directory){

        ArrayList<File> files = new ArrayList<File>();

        System.out.println("Podaj sciezke do fodleru:");
        FileUtils fileUtils = new FileUtils();
        files = fileUtils.listFilesForFolder(directory);

        for (File index : files) {
            try {
                Long dateModifi = index.lastModified();

                Date date = new Date();
                date.setTime(dateModifi);
                String dateToName = fileUtils.createDate(date);
                String extension = getExtensions(index);
                System.out.println("zmiana nazwy pliku na: " + date.getTime());

                try {
                    File newFile = new File(index.getParent(), dateToName + "." + extension);

                    boolean success = index.renameTo(newFile);

                    System.out.println("Udalo sie zamienic nazwy: " + success);

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                Path source = Paths.get(index.getPath());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getExtensions(File index) {
        String extension = "";

        int i = index.toString().lastIndexOf('.');
        if (i > 0) {
            extension = index.toString().substring(i+1);
        }
        return extension;
    }
}
