package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.dto.BoardDTO;
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
                + " ORDER  BY comment_no ,ref";
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

    public int writeComment(CommentDTO newCommentDTO){
        String sql="INSERT INTO comments (comment_no,user_no,board_no,content,create_at,ref)"
                + " VALUES(COMMENT_SEQ.NEXTVAL,?,?,?,CURRENT_TIMESTAMP,?)";
        int insertCount = 0;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,newCommentDTO.getUser_no());
            pstmt.setInt(2,newCommentDTO.getBoard_no());
            pstmt.setString(3,newCommentDTO.getContent());
            pstmt.setInt(4,newCommentDTO.getRef());
            insertCount = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return insertCount;
    }
    public CommentDTO getCommentByCommentNo(String comment_no){
        String sql = "SELECT comment_no,u.user_no,c.board_no,user_name,content,create_at,update_at "
                + " FROM users u "
                + " JOIN comments c "
                + " ON u.user_no = c.user_no "
                + " WHERE comment_no = ?";
        CommentDTO commentDTO = new CommentDTO();


        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(comment_no));
            rs = pstmt.executeQuery();
            if(rs.next()){
                commentDTO.setComment_no(rs.getInt("comment_no"));
                commentDTO.setUser_no(rs.getInt("user_no"));
                commentDTO.setBoard_no(rs.getInt("board_no"));
                commentDTO.setUser_name(rs.getString("user_name"));
                commentDTO.setContent(rs.getString("content"));
                commentDTO.setCreate_at(rs.getTimestamp("create_at"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return commentDTO;
    }
    public int updateComment(CommentDTO commentDTO){
        int updateCount = 0;
        // UPDATE [테이블] SET [열] = '변경할값' WHERE [조건]
        // 오타 , comma,
        String sql = "UPDATE comments "
                + " SET content = ?, "
                + " update_at = sysdate "
                + " WHERE comment_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,commentDTO.getContent());
            pstmt.setInt(2,Integer.valueOf(commentDTO.getComment_no()));

            updateCount = pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return updateCount;
    }
}
