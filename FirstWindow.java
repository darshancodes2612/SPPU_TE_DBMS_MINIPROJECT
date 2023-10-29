import com.sun.jdi.connect.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FirstWindow extends JFrame implements ActionListener {

    JLabel l1 = new JLabel("Hospital management system");
    JButton l_button = new JButton("Login");
    JButton r_button = new JButton("Register");
    Container container = getContentPane();
    Font font1 = new Font("Arial", Font.BOLD, 17);

    FirstWindow(){
        setLayoutManager();
        setSizePosition();
        addComponents();
        setActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setSizePosition(){
        l1.setBounds(124,80,300,40);
        l1.setFont(font1);
        l_button.setBounds(196,180,90,35);
        r_button.setBounds(196,235,90,35);

    }
    public void addComponents(){
        container.add(l1);
        container.add(l_button);
        container.add(r_button);
    }
    public void setActionEvent(){
        l_button.addActionListener(this);
        r_button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == l_button) {
            Login login = new Login();
            login.setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == r_button) {
            Register register = new Register();
            register.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String args[]){
        FirstWindow obj = new FirstWindow();
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
