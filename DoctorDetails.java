import com.sun.jdi.request.ClassPrepareRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.sql.*;

public class DoctorDetails extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel l1 = new JLabel("Add Doctor Details");
    //doctor id doctor name doctor specialization
    JLabel did = new JLabel("Doctor id");
    JLabel dname = new JLabel("Doctor Name");
    JLabel spec = new JLabel("Specialization");
    JButton b1 = new JButton("Add");
    JButton b2 = new JButton("Back");
    JButton b3 = new JButton("Logout");
    JTextField idtext = new JTextField();
    JTextField nametext = new JTextField();
    JTextField spectext = new JTextField();
    Font font1 = new Font("Arial", Font.BOLD, 19);
    Font font2 = new Font("Arial", Font.BOLD, 15);
    DoctorDetails(){
        setLayoutManager();
        setSizePosition();
        addComponents();
        setActionEvent();
    }
    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void setSizePosition(){
        l1.setBounds(150,80,300,40);
        l1.setFont(font1);
        did.setBounds(70,160,100,25);
        did.setFont(font2);
        idtext.setBounds(240,160,130,25);
        dname.setBounds(70,210,100,25);
        dname.setFont(font2);
        nametext.setBounds(240,210,130,25);
        spec.setBounds(70,260,130,25);
        spec.setFont(font2);
        spectext.setBounds(240,260,130,25);
        b1.setBounds(160,310,120,25);
        b1.setFont(font2);
        b2.setBounds(7,20,75,25);
        b2.setFont(font2);
        b3.setBounds(370,20,100,25);
        b3.setFont(font2);
    }
    public void addComponents(){
        container.add(l1);
        container.add(did);
        container.add(dname);
        container.add(spec);
        container.add(b1);
        container.add(b2);
        container.add(b3);
        container.add(idtext);
        container.add(nametext);
        container.add(spectext);
    }
    public void setActionEvent(){
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        idtext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nametext.requestFocusInWindow();
            }
        });
        nametext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spectext.requestFocusInWindow();
            }
        });
        spectext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b1.requestFocusInWindow();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b3){
            dispose();
            System.exit(0);
        }
        else if (e.getSource()==b1){
            try {
                String t1 = idtext.getText();
                String t2 = nametext.getText();
                String t3 = spectext.getText();

                int idval = Integer.parseInt(t1);
                if (isIdExists(idval)){
                    JOptionPane.showMessageDialog(this,"ID already exists");
                }else {
                    insertData(idval,t2,t3);
                    JOptionPane.showMessageDialog(this,"Doctar data uplocaded");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public boolean isIdExists(int id) {
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String pass = "darshan@2612";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            String query = "SELECT doc_id FROM doctordata WHERE doc_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet resultSet = pstm.executeQuery();
            return resultSet.next(); // If a record with the specified ID exists, return true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void insertData(int id,String doc_name, String specialisation){
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String pass = "darshan@2612";
        Connection connection = null;
        boolean resultid = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);
            String query = "INSERT INTO  doctordata VALUES (?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1,id);
            pstm.setString(2,doc_name);
            pstm.setString(3,specialisation);
            pstm.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
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
        DoctorDetails obj = new DoctorDetails();
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
