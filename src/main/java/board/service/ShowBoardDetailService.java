package board.service;

import board.dto.BoardDTO;

import java.sql.Connection;
import java.util.ArrayList;

import static commonUtil.DBConnection.*;
import board.dao.BoardDAO;
import board.dto.BoardDetailDTO;

public class ShowBoardDetailService {

    public BoardDetailDTO getBoardDetail(Integer board_no){
        Connection conn = getConnection();
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();

        BoardDAO boardDAO = new BoardDAO(conn);
        boardDetailDTO = boardDAO.showBoardDetail(board_no);




        return boardDetailDTO;

    }
}
