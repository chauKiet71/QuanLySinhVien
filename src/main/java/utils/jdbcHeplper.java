/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author PC
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcHeplper {

    static String driver = "com.microsoft.sqlserver.SQLServerDriver";
    static String url = "jdbc:sqlserver://localhost;database=Polypro;encrypt=true;";
    static String user = "sa";
    static String pass = "123";
    private static int o;

    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = con.prepareCall(sql);
        }else{
            stmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    public static ResultSet query(String sql, Object...args) throws SQLException{
        PreparedStatement stmt = jdbcHeplper.getStmt(sql, args);
        return stmt.executeQuery();
    }
    public static Object value(String sql, Object...args){
        try {
            ResultSet rs = jdbcHeplper.query(sql, args);
            if (rs.next()) {
                return rs.getObject(o);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int update(String sql, Object...args){
        try {
            PreparedStatement stmt = jdbcHeplper.getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally{
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
