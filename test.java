import java.sql.*;

public class test {
    public static void main(String args[]){
        String url = "jdbc:mysql://localhost:3306/dbms";
        String user = "root";
        String pass = "darshan@2612";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from testdata");
            while (rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
