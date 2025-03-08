package user.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserLogOutController")
public class UserLogOutController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user_no = (String)session.getAttribute("user_no");
        if(user_no == null){
            resp.sendRedirect("/board/views/main.jsp");
            // 너는 잘못된 접근을 하였다.
            return;
        }

        session.invalidate();
        resp.sendRedirect("/board/views/main.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
