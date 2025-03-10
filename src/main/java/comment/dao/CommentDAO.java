package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import comment.dto.CommentDTO;
import java.util.ArrayList;
import java.sql.SQLException;
public class CommentDAO {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public CommentDAO(Connection conn){
        this.conn = conn;
    }

    public ArrayList<CommentDTO> showCommentOfBoard(Integer board_no){
        ArrayList<CommentDTO> commentDTOList = new ArrayList<>();

        String sql= " SELECT comment_no,u.user_no,board_no,user_name,content,create_at,ref "
                + " FROM comments c "
                + " JOIN users u "
                + " ON u.user_no = c.user_no "
                + " WHERE board_no = ? "
                + " AND delete_at is null "
                + " AND ref "
                + " NOT IN ("
                + "    SELECT comment_no "
                + "    FROM comments WHERE ref = 0 "
                + "    AND delete_at IS NOT NULL "
                + "    AND board_no = ?) "
                + " ORDER BY ref , create_at";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,board_no);
            pstmt.setInt(2,board_no);
            rs = pstmt.executeQuery();
            while(rs.next()){
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setComment_no(rs.getInt("comment_no"));
                commentDTO.setUser_no(rs.getInt("user_no"));
                commentDTO.setBoard_no(rs.getInt("board_no"));
                commentDTO.setUser_name(rs.getString("user_name"));
                commentDTO.setContent(rs.getString("content"));
                commentDTO.setCreate_at(rs.getTimestamp("create_at"));
                commentDTO.setRef(rs.getInt("ref"));

                commentDTOList.add(commentDTO);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return commentDTOList;
    }


}
