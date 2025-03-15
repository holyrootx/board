package comment.controller;
import board.dto.BoardDTO;
import board.service.UpdateBoardService;
import comment.dto.CommentDTO;
import comment.service.UpdateCommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateCommentController")
public class UpdateCommentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        String user_no = req.getParameter("user_no");
        String comment_no = req.getParameter("comment_no");
        String updatingContent = req.getParameter("updatingContent");

        HttpSession session = req.getSession();

        String session_user_no = ((Integer)session.getAttribute("user_no")).toString();

            try{
                System.out.println(user_no);
            } catch (NullPointerException e){return;}
            try{
                System.out.println(comment_no);
            } catch (NullPointerException e){return;}
            try{
                System.out.println(updatingContent);
            } catch (NullPointerException e){return;}




        // 세션 아이디 체크
        if( !(session_user_no.equals(user_no)) ){
            System.out.println("잘못된 접근입니다.");
            return;
        }

        // comment_no 체크
        if(comment_no == null){
            System.out.println("(UpdateCommentController) comment_no가 Null인 현상 발생 데이터 전달 현황 체크 바람.");
            return;
        }

        // 값 제대로 받아오는지 체크
        UpdateCommentService updateCommentService = new UpdateCommentService();
        CommentDTO commentDTO;
        commentDTO = updateCommentService.getCommentByCommentNo(comment_no);
        if (commentDTO == null){
            System.out.println("(UpdateCommentController) CommentDTO가 Null인 현상 발생 데이터 전달 현황 체크 바람.");
            return;
        }

        commentDTO.setContent(updatingContent);

        System.out.println(commentDTO.getContent());
        // 여기서 board값 세팅 하고나서 update 하면 될듯

        resp.setContentType("text/plain; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        boolean isUpdatingSuccessed = false;
        isUpdatingSuccessed = updateCommentService.updateComment(commentDTO);

        if(!isUpdatingSuccessed){
            System.out.println("(UpdateBoardController) UpdateBoard메서드에 이상 발생");
            return;
        }

        String newContent = commentDTO.getContent();

        // `|` 같은 구분자로 문자열 연결
        resp.getWriter().write(newContent);

    }
}
