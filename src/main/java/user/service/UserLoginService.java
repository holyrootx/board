package user.service;
import user.dao.UserDAO;
import user.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.io.IOException;

import static commonUtil.DBConnection.*;

public class UserLoginService{

    public boolean authenticateUserService(String user_id,String user_pass){
        // 사용지 권한 확인
        Connection conn = getConnection();
        UserDAO userDAO = new UserDAO(conn);

        boolean isValidUser = false;

        isValidUser = userDAO.authenticateUser(user_id,user_pass);
        close(conn);
        return isValidUser;
    }
    public String getUserNoService(String user_id, String user_pass){
        Connection conn = getConnection();
        UserDAO userDAO = new UserDAO(conn);

        String userNo = String.valueOf(userDAO.getUserNo(user_id,user_pass));

        close(conn);
        return userNo;
    }
}
