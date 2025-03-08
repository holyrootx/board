package board.service;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import java.sql.Connection;
import java.util.ArrayList;
import static commonUtil.DBConnection.*;

public class ShowBoardListService {

    public ArrayList<BoardDTO> getBoardListService(){

        Connection conn = getConnection();
        ArrayList<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();

        BoardDAO boardDAO = new BoardDAO(conn);
        boardDTOList = boardDAO.getBoardList();
        if(boardDTOList == null){
            System.out.println("보드서비스에서 확인중 리스트는 널임");
        } else{
            System.out.println("보드서비스에서 확인중 리스트는 널이아님");
        }
        close(conn);

        return boardDTOList;

    }
}
