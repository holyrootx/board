package board.controller;

import board.dto.BoardDetailDTO;
import board.service.ShowBoardDetailService;
import comment.dto.CommentDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.dto.BoardDTO;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet("/ShowBoardDetailController")
public class ShowBoardDetailController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        Integer board_no = Integer.valueOf(req.getParameter("board_no"));
        System.out.println(board_no);

        if(board_no == null){
            System.out.println("(ShowBoardDetailController)보드 숫자가 없네?");
            return;
        }

        BoardDTO boardDTO = new BoardDTO();

        ShowBoardDetailService showBoardDetailService = new ShowBoardDetailService();
        boardDTO = showBoardDetailService.getBoardDetail(board_no);
        if(boardDTO == null){
            System.out.println("(ShowBoardDetailController)보드DetailDTO객체가 NULL이네?");
            return;
        }

        ArrayList<CommentDTO> commentDTOList = new ArrayList<>();
        commentDTOList = showBoardDetailService.getCommentDTO(board_no);
        if(commentDTOList.size() == 0){
            System.out.println("당신은 쓴 글이 없습니다.");
        }
        if(commentDTOList == null){
            System.out.println("(ShowBoardDetailController)commentDTOList객체가 NULL이네?");
        }
        // board 번호를 브라우저에서 가져온다.
        // service에서 글 가져오는 것 호출

        // service에서 댓글 가져오는 것 호출

        // 브라우저에 뿌려줌
        // 보드 자세히보기 + 댓글 배열 전체 세팅해서 뿌려주면됨
        req.setAttribute("commentDTOList",commentDTOList);
        req.setAttribute("boardDTO",boardDTO);
        System.out.println("Controller에서 setAttribute 완료!!!!!!!!!!");
        System.out.println(commentDTOList.size());
        getServletContext().getRequestDispatcher("/views/showboarddetail.jsp").forward(req,resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
