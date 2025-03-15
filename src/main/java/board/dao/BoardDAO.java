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

    public BoardDTO getBoardByBoardNo(String board_no){
        String sql = "SELECT board_no,u.user_no,user_name,title,content,create_at "
                + " FROM users u "
                + " JOIN boards b "
                + " ON u.user_no = b.user_no "
                + " WHERE board_no = ?";
        BoardDTO boardDTO = new BoardDTO();


        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,Integer.parseInt(board_no));
            rs = pstmt.executeQuery();
            if(rs.next()){
                boardDTO.setBoard_no(rs.getInt("board_no"));
                boardDTO.setUser_name(rs.getString("user_name"));
                boardDTO.setUser_no(rs.getInt("user_no"));
                boardDTO.setContent(rs.getString("content"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setCreate_at(rs.getTimestamp("create_at"));
                boardDTO.setUser_no(rs.getInt("user_no"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return boardDTO;
    }

    public ArrayList<BoardDTO> getBoardList(){
        String sql = "SELECT board_no,u.user_no,user_name,title,create_at "
                + " FROM users u "
                + " JOIN boards b "
                + " ON u.user_no = b.user_no "
                + " WHERE delete_at IS NULL "
                + " ORDER BY create_at DESC";

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

    public BoardDTO showBoardDetail(Integer board_no){
        BoardDTO boardDTO = new BoardDTO();
        String sql = "SELECT * "
                + " FROM  users u "
                + " JOIN boards b "
                + " ON u.user_no = b.user_no"
                + " WHERE b.board_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,board_no);
            rs= pstmt.executeQuery();
            if(rs.next()){
                boardDTO.setBoard_no(rs.getInt("board_no"));
                boardDTO.setUser_no(rs.getInt("user_no"));
                boardDTO.setUser_name(rs.getString("user_name"));
                boardDTO.setTitle(rs.getString("title"));
                boardDTO.setContent(rs.getString("content"));
                boardDTO.setCreate_at(rs.getTimestamp("create_at"));
                boardDTO.setUpdate_at(rs.getTimestamp("update_at"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return boardDTO;

    }
    public int updateBoard(BoardDTO boardDTO){
        int updateCount = 0;
        // UPDATE [테이블] SET [열] = '변경할값' WHERE [조건]
        String sql = "UPDATE boards "
                + " SET title = ?, "
                + " content = ?, "
                + " update_at = sysdate "
                + " WHERE board_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,boardDTO.getTitle());
            pstmt.setString(2,boardDTO.getContent());
            pstmt.setInt(3,Integer.valueOf(boardDTO.getBoard_no()));
            updateCount = pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return updateCount;
    }

}
