package comment.service;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import comment.dao.CommentDAO;
import comment.dto.CommentDTO;
import commonUtil.DBConnection;

import java.sql.Connection;

public class UpdateCommentService {
    public CommentDTO getCommentByCommentNo(String comment_no){
        Connection conn = DBConnection.getConnection();
        CommentDAO commentDAO = new  CommentDAO(conn);

        CommentDTO commentDTO;
        commentDTO = commentDAO.getCommentByCommentNo(comment_no);

        return commentDTO;
    }

    public boolean updateComment(CommentDTO commentDTO){
        Connection conn = DBConnection.getConnection();
        CommentDAO commentDAO = new  CommentDAO(conn);

        int updateCount = commentDAO.updateComment(commentDTO);

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
