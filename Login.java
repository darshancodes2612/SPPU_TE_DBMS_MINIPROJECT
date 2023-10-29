import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel label = new JLabel("Login Page");
    JLabel email = new JLabel("Email");
    JLabel password = new JLabel("Password");
    JLabel forget_pass = new JLabel("Forgot password");
    JTextField email_id = new JTextField();
    JPasswordField pass = new JPasswordField();
    JButton log_button = new JButton("Login");
    Font font1 = new Font("Arial", Font.BOLD, 19);
    Font font2 = new Font("Calibri",Font.TRUETYPE_FONT,15);

    Login(){
        setLayoutManager();
        setSizePosition();
        addComponents();
        setActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }
    public void addComponents(){
        container.add(label);
        container.add(email);
        container.add(password);
        container.add(forget_pass);
        container.add(email_id);
        container.add(pass);
        container.add(log_button);
    }
    public void setSizePosition(){
        label.setBounds(167,80,150,40);
        label.setFont(font1);
        email.setBounds(62,154,100,40);
        email.setFont(font2);
        password.setBounds(62,204,100,40);
        password.setFont(font2);
        email_id.setBounds(207,154,220,25);
        pass.setBounds(207,204,150,25);
        log_button.setBounds(167,300,120,25);
    }
    public void  setActionEvent(){
        log_button.addActionListener(this);
        email_id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pass.requestFocusInWindow();
            }
        });
        pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log_button.requestFocusInWindow();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == log_button){
            String email = email_id.getText();
            char[] passw = pass.getPassword();

            String p = new String(passw);

            if (checkUserExists(email,p)){
                JOptionPane.showMessageDialog(null,"Login Successful");
            }else {
                JOptionPane.showMessageDialog(null,"Record Does not exist! register");
            }
        }
    }

    public boolean checkUserExists(String email, String pass){
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String password = "darshan@2612";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            String query = "SELECT * FROM register WHERE email = '" + email + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                return true;
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String args[]){
        Login obj = new Login();
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
