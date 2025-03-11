package comment.controller;
import comment.dto.CommentDTO;
import comment.service.WriteCommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/WriteCommentController")
public class WriteCommentController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        System.out.println("WriteCommentController doPost");
        Integer ref = Integer.valueOf(req.getParameter("ref"));
        Integer board_no = Integer.valueOf(req.getParameter("board_no"));
        if(ref == null){
            System.out.println("(WriteCommentController) ref Is Null");
            return;
        }
        if(board_no == null){
            System.out.println("(WriteCommentController) board_no Is Null");
            return;
        }

        HttpSession session = req.getSession();
        Integer user_no = (Integer)session.getAttribute("user_no");

        if(user_no == null){
            System.out.println("(WriteCommentController) 세션 만료 user_id Is Null");
            return;
        }
        String content = req.getParameter("comment_content");
                CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUser_no(user_no);
        commentDTO.setBoard_no(board_no);
        commentDTO.setRef(ref);
        commentDTO.setContent(content);
        WriteCommentService writeCommentService = new WriteCommentService();
        Boolean isWritingSucessed = writeCommentService.writeComment(commentDTO);


        if(isWritingSucessed){
            resp.sendRedirect("/board/ShowBoardDetailController?board_no="+board_no);
        } else{
            System.out.println("피치 못할 사정으로 댓글이 안써짐 ㅋ");
        }
    }

}
