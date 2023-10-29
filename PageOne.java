import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageOne extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel label = new JLabel("Malusare Hospital");
    JButton btn1 = new JButton("Doctor Records");
    JButton btn2 = new JButton("Patient Records");
    JButton btn3 = new JButton("Logout");
    Font font1 = new Font("Arial", Font.BOLD, 19);

    PageOne(){
        setLayoutManager();
        setSizePosition();
        addComponents();
        setActionEvent();
    }
    public void setLayoutManager(){
        container.setLayout(null);
    }
    public void addComponents(){
        container.add(label);
        container.add(btn1);
        container.add(btn2);
        container.add(btn3);
    }
    public void setSizePosition(){
        label.setBounds(150,80,300,40);
        label.setFont(font1);
        btn1.setBounds(142,170,170,45);
        btn2.setBounds(142,250,170,45);
        btn3.setBounds(142,330,170,45);
    }
    public void setActionEvent(){
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btn3){
            dispose();
            System.exit(0);
        }
        else if (e.getSource()==btn1){
            DoctorDetails docdetails = new DoctorDetails();
            docdetails.setVisible(true);
            this.setVisible(false);
        }
        else if (e.getSource()==btn2){
            PatientDetails patDet = new PatientDetails();
            patDet.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String args[]){
        PageOne obj = new PageOne();
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
