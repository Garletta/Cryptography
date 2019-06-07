import java.io.*;

public class Decode {

    public void decode(File file) {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        FileWriter fw = null;
        try {
            fis = new FileInputStream(file);
            int temp;
            while((temp = fis.read()) != -1) {
                temp = (temp + 128 - 10) % 128;
                sb.append((char)temp);
            }
            fw = new FileWriter(file);
            fw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
