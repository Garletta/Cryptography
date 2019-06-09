import javax.swing.*;
import java.awt.*;
import java.io.*;

public class DESAlgorithm {

    private JFileChooser jfc;   //文件选择器
    private File chooseFile;    //待操作文件
    private FileInputStream fis;//文件输入流
    private FileWriter fw;      //文件输出流
    private StringBuffer sb;    //字符串缓冲
    private int key;            //密钥
    private int initialization; //初始化向量

    public DESAlgorithm() {
        sb = new StringBuffer();
        key = 20;   //初始化密钥
        initialization = 127;   //初始化向量
    }

    //加密方法
    public void Encrypt() {
        chooseFile = OpenFile("请选择待加密文件");
        ReadFile(); //从文件中读取明文
        //ECB
        for(int i = 0;i < sb.length();i++) {
            sb.setCharAt(i, (char) ((sb.charAt(i) + key) % 128));
        }
        //CBC
        int init = initialization;
        for(int i = 0;i < sb.length();i++) {
            sb.setCharAt(i, (char) (init ^ sb.charAt(i)));
            sb.setCharAt(i, (char) ((sb.charAt(i) + key) % 128));
            init = sb.charAt(i);
        }
        WriteFile();    //将密文写回文件
    }

    //解密方法
    public void Decode() {
        chooseFile = OpenFile("请选择待解密文件");
        ReadFile(); //从文件中读取密文
        //CBC
        int init = initialization, next;
        for(int i = 0;i < sb.length();i++) {
            next = sb.charAt(i);
            sb.setCharAt(i, (char) ((sb.charAt(i) + 128 - key) % 128));
            sb.setCharAt(i, (char) (init ^ sb.charAt(i)));
            init = next;
        }
        //ECB
        for(int i = 0;i < sb.length();i++) {
            sb.setCharAt(i, (char) ((sb.charAt(i) + 128 - key) % 128));
        }
        WriteFile();    //将明文写回文件
    }

    //使用文件选择器打开待操作文件
    public File OpenFile(String Title) {
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new Label(),Title);
        return jfc.getSelectedFile();
    }

    //从文件中读取明文或密文
    public void ReadFile() {
        try {
            int temp;
            fis = new FileInputStream(chooseFile);
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

    //将明文或密文写回文件
    public void WriteFile() {
        try {
            fw = new FileWriter(chooseFile);
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
