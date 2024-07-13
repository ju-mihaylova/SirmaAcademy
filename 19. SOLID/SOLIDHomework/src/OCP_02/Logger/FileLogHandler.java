package OCP_02.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogHandler implements LogHandler {
    private String filename;

    public FileLogHandler(String filename) {
        this.filename = filename;
    }
    @Override
    public void log(String message) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
