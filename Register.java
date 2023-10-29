import com.sun.jdi.connect.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("Registration");
    JLabel email = new JLabel("Email id");
    JLabel password = new JLabel("Password");
    JLabel confPass = new JLabel("Confirm Password");
    JTextField e_text = new JTextField();
    JPasswordField init_pass = new JPasswordField();
    JPasswordField conf_pass = new JPasswordField();
    JButton reg =new JButton("Register");


    Container container = getContentPane();

    Font font1 = new Font("Arial", Font.BOLD, 19);
    Font font2 = new Font("Calibri",Font.TRUETYPE_FONT,15);
    Register(){
        setLayoutManager();
        setSizePosition();
        addComponents();
        setActionEvent();
    }
    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void setSizePosition(){
        l1.setBounds(167,80,300,40);
        l1.setFont(font1);
        email.setBounds(62,154,100,40);
        email.setFont(font2);
        password.setBounds(62,204,100,40);
        password.setFont(font2);
        confPass.setBounds(62,254,120,40);
        confPass.setFont(font2);
        e_text.setBounds(207,154,220,25);
        init_pass.setBounds(207,204,150,25);
        conf_pass.setBounds(207,254,150,25);
        reg.setBounds(167,300,120,25);
    }
    public void addComponents(){
        container.add(l1);
        container.add(email);
        container.add(password);
        container.add(confPass);
        container.add(e_text);
        container.add(init_pass);
        container.add(conf_pass);
        container.add(reg);
    }
    public void setActionEvent(){
        reg.addActionListener(this);
        e_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init_pass.requestFocusInWindow();
            }
        });
        init_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conf_pass.requestFocusInWindow();
            }
        });
        conf_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reg.requestFocusInWindow();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String password = "darshan@2612";
        if (e.getSource()==reg){
            String mail = e_text.getText();
            char[] passChar = init_pass.getPassword();
            char[] confChar = conf_pass.getPassword();

            String p = new String(passChar);
            String cp = new String(confChar);

            if (p.equals(cp)){
                insertData(mail,p);
                JOptionPane.showMessageDialog(this,"Successfully registered");
            }
            else {
                JOptionPane.showMessageDialog(this,"Passwords does not match");
            }
        }
    }

    public void insertData(String email, String pass) {
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String password = "darshan@2612";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String query = "INSERT INTO register VALUES(?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,email);
            pst.setString(2,pass);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        Register obj = new Register();
        obj.setTitle("Hospital Management");
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setResizable(true);
        obj.setSize(500,500);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                obj.setVisible(true);
            }
        });
    }


}
