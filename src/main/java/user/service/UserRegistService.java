package user.service;
import user.dao.UserDAO;
import user.dto.UserDTO;

import java.sql.Connection;
import static commonUtil.DBConnection.*;
public class UserRegistService {
    private static int count = 90000;


    public boolean checkUserBeforeRegist(String user_id){
    // 비동기 처리 고려중
        return false;
    }
    public boolean registUserService(UserDTO newUser){

        Connection conn = getConnection();
        UserDAO userDAO = new UserDAO(conn);

        UserDTO userDTO = newUser;        // userDAO

        int user_no = ++count;
        int registCount = 0;
        boolean registSuccessed = false;
        registCount = userDAO.registUser(userDTO,user_no);
        if (registCount > 0){
            registSuccessed = true;
            close(conn);
        } else{
            count--;
        }
        return registSuccessed;

    }



}
