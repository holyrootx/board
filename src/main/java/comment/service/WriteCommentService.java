package comment.service;
import comment.dao.CommentDAO;
import comment.dto.CommentDTO;

import java.sql.Connection;
import static commonUtil.DBConnection.*;
public class WriteCommentService {

    public boolean writeComment(CommentDTO newCommentDTO){
        Connection conn = getConnection();
        CommentDAO commentDAO = new CommentDAO(conn);


        boolean isWritingSuccessed = false;

        int insertCount = commentDAO.writeComment(newCommentDTO);
        if(insertCount > 0){
            isWritingSuccessed = true;
            commit(conn);
        } else{
            rollback(conn);
        }

        close(conn);
        return isWritingSuccessed;
    }


}
