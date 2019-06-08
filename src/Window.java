import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//信息安全小工具主窗体
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
        //设置窗体大小、位置、窗体大小固定、关闭操作、可见性
        setSize(400,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //加入中间容器（自适应窗体大小），传入主体句柄便于数据修改
        MyJPanel panel = new MyJPanel(this);
        setContentPane(panel);

        //使窗体可见
        setVisible(true);
    }

    //该窗体的中间容器类
    class MyJPanel extends JPanel {

        private Window window;    //获得当前窗体的句柄

        public MyJPanel(Window window) {
            super();
            this.window = window; //保存传参句柄
            initMyJPanel();     //初始化
        }

        public void initMyJPanel() {

            //设置中间容器为流式布局
            setLayout(new FlowLayout(FlowLayout.CENTER,30,30));

            //欢迎语
            welcome = new JLabel("欢迎使用加密解密小工具", JLabel.CENTER);
            welcome.setFont(new Font("微软雅黑", 1, 30));
            welcome.setForeground(Color.BLUE);
            add(welcome,BorderLayout.NORTH);

            //加密
            encrypt = new JButton("加密");
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
                    Encrypt enf = new Encrypt();
                    enf.encrypt(jfc.getSelectedFile());
                    result.setText("加密成功!");
                }
            });
            add(encrypt);

            //解密
            decode = new JButton("解密");
            decode.setFont(new Font("楷体", 1, 30));
            decode.setFocusPainted(false);
            decode.setBorderPainted(false);
            decode.setContentAreaFilled(false);
            decode.setForeground(Color.MAGENTA);
            decode.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    jfc.showDialog(new Label(),"选择加密文件");
                    Decode def = new Decode();
                    def.decode(jfc.getSelectedFile());
                    result.setText("解密成功!");
                }
            });
            add(decode);

            //结果
            result = new JLabel("", JLabel.CENTER);
            result.setFont(new Font("微软雅黑", 1, 30));
            result.setForeground(Color.ORANGE);
            add(result,BorderLayout.SOUTH);

            setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) { //主体背景
            ImageIcon icon = new ImageIcon(Window.class.getResource("/Images/background.jpg"));
            Image image = icon.getImage();
            g.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);
        }
    }
}
