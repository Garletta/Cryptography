```java
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DESAlgorithm {

    private JFileChooser jfc;
    private StringBuffer sb;
    private FileInputStream fis;
    private FileWriter fw;

    public DESAlgorithm() {
        sb = new StringBuffer();
    }

    public void Encrypt() {
        File chooseFile = OpenFile("");
        ReadFile(chooseFile);
        for(int i = 0;i < sb.length();i++) {
            sb.setCharAt(i, (char) ((sb.charAt(i) + 10) % 128));
        }
        WriteFile(chooseFile);
    }

    public void Decode() {
        File chooseFile = OpenFile("");
        ReadFile(chooseFile);
        for(int i = 0;i < sb.length();i++) {
            sb.setCharAt(i, (char) ((sb.charAt(i) + 118) % 128));
        }
        WriteFile(chooseFile);
    }

    public File OpenFile(String Title) {
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new Label(),Title);
        return jfc.getSelectedFile();
    }

    public void ReadFile(File file) {
        try {
            int temp;
            fis = new FileInputStream(file);
            while((temp = fis.read()) != -1) {
                sb.append((char)temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void WriteFile(File file) {
        try {
            fw = new FileWriter(file);
            fw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

