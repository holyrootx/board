package board.service;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

import java.sql.Connection;
import java.util.ArrayList;

import static commonUtil.DBConnection.getConnection;
public class ShowMyBoardListService {
    //
    public ArrayList<BoardDTO> showMyBoardList(Integer user_no){
        Connection conn = getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);

        ArrayList<BoardDTO> myBoardList = new ArrayList<>();
        myBoardList = boardDAO.showMyBoardList(user_no);

        return myBoardList;
    }
}
