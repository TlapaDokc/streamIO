import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {
    private String log = "productNum,amount,\n";

    public void log(int productNum, int amount) {
        log += productNum + "," + amount + ",\n";
    }

    public void exportAsCSV(File txtFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile))) {
            writer.writeNext(log.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}