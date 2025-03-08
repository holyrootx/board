package user.controller;
import user.service.UserRegistService;
import user.dto.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/UserRegistController")
public class UserRegistController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        UserRegistService userRegistService = new UserRegistService();

        String user_id = req.getParameter("user_id");
        String user_pass = req.getParameter("user_pass");
        String user_email = req.getParameter("user_email");
        String user_name = req.getParameter("user_name");
        String user_gender = req.getParameter("user_gender");
        Date user_birth = Date.valueOf(req.getParameter("user_birth"));

        boolean isRegisted = userRegistService.checkUserBeforeRegist(user_id);
        // 등록이 되어 있다면 사용자에게 알려줘야함
        if(isRegisted){
            // do Something 알림?
            System.out.println("등록되어있는 사람입니다.");

            return;
        }

        UserDTO userDTO = new UserDTO(user_id,user_pass,user_email,user_name,user_gender,user_birth);

        boolean registSuccessed = userRegistService.registUserService(userDTO);

        if(registSuccessed){
            System.out.println("등록되었습니다.");
            HttpSession session = req.getSession();
            String userNo = userRegistService.getUserNoService(userDTO);
            session.setAttribute("user_no",userNo);
            // 등록 성공
            resp.sendRedirect("/board/views/main.jsp");

        } else{
            // 등록 실패시
            System.out.println("등록실패하였습니다.");
        }

    }
}
