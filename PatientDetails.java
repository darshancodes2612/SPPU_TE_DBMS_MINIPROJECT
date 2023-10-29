import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class PatientDetails extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel l1 = new JLabel("Doctors Records");
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable dataTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane();
    Font font1 = new Font("Arial", Font.BOLD, 19);

    PatientDetails(){
        setLayoutManager();
        setSizePosition();
        addComponents();
    }

    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void addComponents(){
        container.add(l1);
        container.add(scrollPane);
        tableModel.addColumn("Doctor id");
        tableModel.addColumn("Doctor Name");
        tableModel.addColumn("Specialization");
        showData();
        scrollPane.setViewportView(dataTable);
    }

    public void setSizePosition(){
        l1.setBounds(150,40,300,40);
        l1.setFont(font1);
        scrollPane.setBounds(30,100,400,300);
    }

    public void showData(){
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String pass = "darshan@2612";
        Connection connection = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);
            String query = "SELECT * FROM patient";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                int docid = resultSet.getInt("id");
                String docname = resultSet.getString("patientname");
                String speci = resultSet.getString("specli");
                Object[] row = {docid, docname, speci};
                tableModel.addRow(row);


            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String args[]){
        PatientDetails obj = new PatientDetails();
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
