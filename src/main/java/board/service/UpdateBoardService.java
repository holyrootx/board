package board.service;
import board.dao.BoardDAO;
import commonUtil.DBConnection;
import board.dto.BoardDTO;
import static commonUtil.DBConnection.*;
import java.sql.Connection;

public class UpdateBoardService {

    public BoardDTO getBoardByBoardNo(String board_no){
        Connection conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);

        BoardDTO boardDTO;
        boardDTO = boardDAO.getBoardByBoardNo(board_no);
        return boardDTO;
    }
    public boolean updateBoard(BoardDTO boardDTO){
        Connection conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);
        BoardDTO newBoardDTO;

        int updateCount = boardDAO.updateBoard(boardDTO);

        boolean isUpdatingSuccessed = false;

        if(updateCount > 0){
            isUpdatingSuccessed = true;
            DBConnection.commit(conn);

        } else{
            DBConnection.rollback(conn);
        }

        return isUpdatingSuccessed;
    }
}
