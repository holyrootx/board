package user.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import user.dto.UserDTO;

public class UserDAO {
        Connection conn;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        public UserDAO(Connection conn){
               this.conn = conn;
        }

        public int registUser(UserDTO userDTO ,int UserNo){

                String sql = "INSERT INTO users " +
                        " (user_no,user_id,user_pass,user_email,user_name,user_gender,user_birth) " +
                        " VALUES(?,?,?,?,?,?,?)";

                int registCount = 0;
                try{
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1,UserNo);
                        pstmt.setString(2,userDTO.getUser_id());
                        pstmt.setString(3,userDTO.getUser_pass());
                        pstmt.setString(4,userDTO.getUser_email());
                        pstmt.setString(5,userDTO.getUser_name());
                        pstmt.setString(6,userDTO.getUser_gender());
                        pstmt.setDate(7,userDTO.getUser_birth());

                        registCount = pstmt.executeUpdate();

                } catch(Exception e){
                        e.printStackTrace();
                }

                return registCount;
        }


}
