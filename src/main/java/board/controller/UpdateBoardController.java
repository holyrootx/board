package board.controller;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateBoardController")
public class UpdateBoardController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String user_no = req.getParameter("user_no");
        HttpSession session = req.getSession();

        Integer session_user_no = (Integer) session.getAttribute("user_no");

        if( !(session_user_no.equals(user_no)) ){
            System.out.println("잘못된 접근입니다.");
            return;
        }

        String board_no = req.getParameter("board_no");
        if(board_no != null){
            System.out.println(board_no);
        } else{
            System.out.println("(UpdateBoardController) board_no가 Null인 현상 발생 데이터 전달 현황 체크 바람.");
        }
        resp.setContentType("text/plain; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String title = "게시글 제목";
        String content = "게시글 내용";

        // `|` 같은 구분자로 문자열 연결
        resp.getWriter().write(title + "|" + content);
    }
}
