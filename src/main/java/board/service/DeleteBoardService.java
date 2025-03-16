package board.service;

import board.dao.BoardDAO;
import static commonUtil.DBConnection.*;

import java.sql.Connection;

public class DeleteBoardService {

    public boolean deleteBoard(String board_no){
        Connection conn = getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);

        boolean isDeletingSuccessed = false;

        int result = boardDAO.deleteBoard(board_no);

        if(result <= 0){
            isDeletingSuccessed = true;
            commit(conn);
        } else{
            rollback(conn);
        }
        return isDeletingSuccessed;
    }

}
