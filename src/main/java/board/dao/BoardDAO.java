package board.dao;

import board.dto.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
public class BoardDAO {
    Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public BoardDAO(Connection conn){
        this.conn = conn;
    }

    public ArrayList<BoardDTO> getBoardList(){
        String sql = "SELECT board_no,user_name,title,create_at "
                + " FROM users u "
                + " JOIN boards b "
                + " ON u.user_no = b.user_no "
                + " WHERE delete_at IS NULL ";

        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();

        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setBoard_no(rs.getInt("board_no"));
                boardDTO.setUser_name(rs.getString("user_name"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setCreate_at(rs.getTimestamp("create_at"));

                boardDTOList.add(boardDTO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return boardDTOList;
    }

}
