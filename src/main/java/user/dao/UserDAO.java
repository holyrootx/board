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

        public boolean checkUserBeforeRegisted(String userId){
                String sql = "SELECT * " +
                        " FROM users" +
                        " WHERE user_id = ?";
                boolean isRegisted = false;

                try{
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,userId);

                        rs = pstmt.executeQuery();
                        if(rs.next()){
                                isRegisted = true;
                        }
                } catch (SQLException e){
                        e.printStackTrace();
                }

            return isRegisted;
        }
        public int registUser(UserDTO userDTO){

                String sql = "INSERT INTO users " +
                        " (user_no,user_id,user_pass,user_email,user_name,user_gender,user_birth) " +
                        " VALUES(USER_SEQ.NEXTVAL,?,?,?,?,?,?)";

                int registCount = 0;
                try{
                        System.out.println(userDTO);
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,userDTO.getUser_id());
                        pstmt.setString(2,userDTO.getUser_pass());
                        pstmt.setString(3,userDTO.getUser_email());
                        pstmt.setString(4,userDTO.getUser_name());
                        pstmt.setString(5,userDTO.getUser_gender());
                        pstmt.setDate(6,userDTO.getUser_birth());

                        registCount = pstmt.executeUpdate();

                } catch(Exception e){
                        e.printStackTrace();
                }

                return registCount;
        }

        public int getUserNo(String userName){
                String sql = "SELECT user_no " +
                        " FROM users" +
                        " WHERE user_name = ?";
                int user_no = 0;

                try{
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,userName);

                        rs = pstmt.executeQuery();
                        if(rs.next()){
                                user_no = rs.getInt("user_no");
                        }
                } catch (SQLException e){
                        e.printStackTrace();
                }

                return user_no;
        }
        public int getUserNo(String user_id, String user_pass){
                String sql = "SELECT user_no " +
                        " FROM users" +
                        " WHERE user_id = ? AND user_pass = ?";
                int user_no = 0;

                try{
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,user_id);
                        pstmt.setString(2,user_pass);

                        rs = pstmt.executeQuery();
                        if(rs.next()){
                                user_no = rs.getInt("user_no");
                        }
                } catch (SQLException e){
                        e.printStackTrace();
                }
                System.out.println("user_no DAO에서 체크중 user_no : "+user_no);
                return user_no;
        }


        public boolean authenticateUser(String user_id,String user_pass){
                // 사용자 인증 및 권한 확인 user를 검사하다.
                boolean isValidUser = false;

                String sql = "SELECT user_no " +
                        " FROM users" +
                        " WHERE user_id = ? " +
                        " AND user_pass = ? ";

                try{
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,user_id);
                        pstmt.setString(2,user_pass);

                        rs = pstmt.executeQuery();

                        if(rs.next()){
                                isValidUser = true;
                        }

                } catch (SQLException e){
                        e.printStackTrace();
                }

                return isValidUser;
        }
}
