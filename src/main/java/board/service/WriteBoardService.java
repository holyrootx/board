package board.service;
import java.sql.Connection;
import static commonUtil.DBConnection.*;
import board.dao.BoardDAO;
import board.dto.BoardDTO;
public class WriteBoardService {

    public boolean writeBoardSerivce(BoardDTO boardDTO){
        Connection conn = getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);

        boolean isWritingSuccessed = false;

        int insertCount = boardDAO.writeBoard(boardDTO);

        if(insertCount > 0 ){
            commit(conn);
            isWritingSuccessed = true;
        } else{
            rollback(conn);
        }

        close(conn);
        return isWritingSuccessed;

    }

}
