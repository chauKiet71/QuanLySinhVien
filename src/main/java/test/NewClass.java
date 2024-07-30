/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.sql.*;
/**
 *
 * @author PC
 */
public class NewClass {

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionsql = "jdbc:sqlserver://localhost:1433;databaseName=STUDENT";
            System.out.println("helo");
            String user = "sa";
            String pass = "123";
            Connection con = DriverManager.getConnection(connectionsql, user, pass);
            System.out.println("ii");
            Statement st = con.createStatement();
            String sql = "select * from STUDENT";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                String masv = rs.getString("MASV");
                String hoten = rs.getString("HOTEN");
                
                System.out.println("masv: "+ masv);
                System.out.println("hoten: "+hoten);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
