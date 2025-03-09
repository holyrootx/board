package user.service;
import user.dao.UserDAO;
import user.dto.UserDTO;

import java.sql.Connection;
import static commonUtil.DBConnection.*;
public class UserRegistService {


    public boolean checkUserBeforeRegist(String user_id){
        // id 를 받아서 DB에 해당하는 ID가 있는지 검사 후 boolean으로 반환
        // id가 있다면 true 없으면 false
        // 비동기 처리 고려중

        Connection conn = getConnection();
        UserDAO userDAO = new UserDAO(conn);
        boolean isRegisted = userDAO.checkUserBeforeRegisted(user_id);

        close(conn);
        return isRegisted;
    }
    public boolean registUserService(UserDTO newUser){

        // 새로운 유저를 폼으로 입력 받아 user를 데이터베이스에 삽입함.
        // 등록이 성공한다면 boolean 반환 등록이 실패하면 false를 반환
        // 등록이 실패하는 케이스를 나누어서 사용자에게 안내할 예정 현재는 케이스가 사실 없음

        Connection conn = getConnection();
        UserDAO userDAO = new UserDAO(conn);


        if(newUser == null) return false;

        int registCount = 0;
        boolean registSuccessed = false;

        registCount = userDAO.registUser(newUser);

        if (registCount > 0){
            registSuccessed = true;
            commit(conn);
            close(conn);
        } else{
            rollback(conn);
        }
        return registSuccessed;

    }
    public Integer getUserNoService(UserDTO newUser){
        Connection conn = getConnection();
        UserDAO userDAO = new UserDAO(conn);

        Integer userNo = userDAO.getUserNo(newUser.getUser_name());

        close(conn);
        return userNo;
    }


}
