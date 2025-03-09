package board.dao;

import board.dto.BoardDTO;
import board.dto.BoardDetailDTO;

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
        String sql = "SELECT board_no,u.user_no,user_name,title,create_at "
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
                boardDTO.setUser_no(rs.getInt("user_no"));

                boardDTOList.add(boardDTO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return boardDTOList;
    }

    public int writeBoard(BoardDTO boardDTO){
        String sql = "INSERT INTO boards(board_no,user_no,title,content,create_at)" +
                " VALUES(BOARD_SEQ.NEXTVAL,?,?,?,CURRENT_TIMESTAMP)";

        int insertCount = 0;

        try{

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,boardDTO.getUser_no());
            pstmt.setString(2, boardDTO.getTitle());
            pstmt.setString(3, boardDTO.getContent());
            insertCount = pstmt.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();
        }
        return insertCount;
    }

    public ArrayList<BoardDTO> showMyBoardList(Integer user_no){
        String sql = "SELECT board_no,u.user_no,user_name,title,create_at "
                + " FROM users u "
                + " JOIN boards b "
                + " ON u.user_no = b.user_no "
                + " WHERE delete_at IS NULL AND u.user_no = ?";

        BoardDTO boardDTO = null;
        ArrayList<BoardDTO> myBoardList = new ArrayList<>();
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user_no);
            rs = pstmt.executeQuery();
            while(rs.next()){
                boardDTO = new BoardDTO();
                boardDTO.setBoard_no(rs.getInt("board_no"));
                boardDTO.setUser_name(rs.getString("user_name"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setCreate_at(rs.getTimestamp("create_at"));
                boardDTO.setUser_no(rs.getInt("user_no"));
                myBoardList.add(boardDTO);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return myBoardList;
    }

    public BoardDetailDTO showBoardDetail(Integer board_no){
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        String sql = "SELECT ";


        return boardDetailDTO;

    }

}
