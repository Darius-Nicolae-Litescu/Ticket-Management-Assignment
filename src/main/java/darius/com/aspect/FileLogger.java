package darius.com.aspect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger {
    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private String fullFilePath;

    public FileLogger(String fullFilePath) {
        this.fullFilePath = fullFilePath;
        this.file = new File(this.fullFilePath);
    }

    public void openFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String text) {
        openFile();
        try {
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}