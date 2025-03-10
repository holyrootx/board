package board.service;

import board.dto.BoardDTO;

import java.sql.Connection;
import java.util.ArrayList;

import static commonUtil.DBConnection.*;
import board.dao.BoardDAO;
import board.dto.BoardDetailDTO;
import comment.dao.CommentDAO;
import comment.dto.CommentDTO;

public class ShowBoardDetailService {

    public BoardDTO getBoardDetail(Integer board_no){
        Connection conn = getConnection();
        BoardDTO boardDTO = new BoardDTO();

        BoardDAO boardDAO = new BoardDAO(conn);

        boardDTO = boardDAO.showBoardDetail(board_no);



        close(conn);
        return boardDTO;
    }
    public ArrayList<CommentDTO> getCommentDTO(Integer board_no){
        Connection conn = getConnection();
        ArrayList<CommentDTO> commentDTOList = new ArrayList<>();

        CommentDAO commentDAO  = new CommentDAO(conn);
        commentDTOList = commentDAO.showCommentOfBoard(board_no);
        close(conn);
        return commentDTOList;

    }
}
