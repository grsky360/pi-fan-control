package cn.hssnow.pi.support;

import java.io.*;

public class FileUtil {

    public static void writeToFile(String path, Object value) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(String.valueOf(value));
            writer.flush();
        }
    }

    public static String readFromFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.readLine();
        }
    }

}
