package commonUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection con = null;

        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String id = "boardPR";
            String pass = "boardPR";
            con = DriverManager.getConnection(url,id,pass);
            con.setAutoCommit(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs) {
        try {
            if(rs != null) rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null) stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection con) {
        try {
            if(con != null) con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection con) {
        try {
            if(con != null) con.commit();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection con) {
        try {
            if(con != null) con.rollback();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
