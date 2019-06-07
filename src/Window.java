import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Window extends JFrame {

    private JLabel welcome;
    private JLabel result;
    private JFileChooser jfc;
    private JButton encrypt;
    private JButton decode;

    public static void main(String[] args) {
        new Window();
    }

    public Window() {
        super("信息安全小工具");   //标题
        initWindow();   //初始化
    }

    private void initWindow() {
        //设置窗体大小、位置、布局、关闭操作、可见性
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //加入中间容器（自适应窗体大小），传入主体句柄便于修改主体标题
        MyJPanel panel = new MyJPanel(this);
        add(panel,BorderLayout.CENTER);

        //使窗体可见
        setVisible(true);
    }

    //该窗体的中间容器类
    class MyJPanel extends JPanel {

        private Window window;    //获得当前窗体的句柄

        public MyJPanel(Window window) {
            super();
            this.window = window; //保存传参句柄
            initMyJPanel();
        }

        public void initMyJPanel() {

            //设置中间容器的布局
            setLayout(new FlowLayout(FlowLayout.CENTER,30,30));

            //欢迎语
            welcome = new JLabel("欢迎使用加密解密小工具", JLabel.CENTER);
            welcome.setPreferredSize(new Dimension(400,40));
            welcome.setFont(new Font("微软雅黑", 1, 30));
            welcome.setForeground(Color.BLUE);
            add(welcome,BorderLayout.NORTH);

            //加密
            encrypt = new JButton("加密");
            encrypt.setPreferredSize(new Dimension(100,40));
            encrypt.setFont(new Font("楷体", 1, 30));
            encrypt.setFocusPainted(false);
            encrypt.setBorderPainted(false);
            encrypt.setContentAreaFilled(false);
            encrypt.setForeground(Color.MAGENTA);
            encrypt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    jfc.showDialog(new Label(),"选择加密文件");
                    File file = jfc.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String str;
                        while ((str = br.readLine()) != null) {
                            System.out.println(str);
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    /*
                    加密程序
                     */
                    result.setText("加密成功!");
                }
            });
            add(encrypt);

            //解密
            decode = new JButton("解密");
            decode.setPreferredSize(new Dimension(100,40));
            decode.setFont(new Font("楷体", 1, 30));
            decode.setFocusPainted(false);
            decode.setBorderPainted(false);
            decode.setContentAreaFilled(false);
            decode.setForeground(Color.MAGENTA);
            decode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*
                    解密程序
                     */
                    result.setText("解密成功!");
                }
            });
            add(decode);

            //结果
            result = new JLabel("", JLabel.CENTER);
            result.setPreferredSize(new Dimension(400,40));
            result.setFont(new Font("微软雅黑", 1, 30));
            result.setForeground(Color.ORANGE);
            add(result,BorderLayout.SOUTH);

            setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) { //主体背景
            ImageIcon icon = new ImageIcon(new File("").getAbsolutePath() + "/Images/background.jpg");
            Image image = icon.getImage();
            g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
        }
    }
}
