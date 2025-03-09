package user.controller;
import user.dto.UserDTO;
import user.service.UserLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/UserLoginController")
public class UserLoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("UserLoginController GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        out.println("UserLoginController POST");

        String userId = req.getParameter("user_id");
        String userPass = req.getParameter("user_pass");
        System.out.println("userID : "+userId+"\n userPass : "+ userPass);
        UserLoginService userLoginService = new UserLoginService();

        boolean isValidUser = userLoginService.authenticateUserService(userId,userPass);

        if(!isValidUser){
            // 유저가 유효하지 않으면
            // 아이디 혹은 비밀번호가 유효하지 않습니다.
            return;
        }



        Integer userNo = userLoginService.getUserNoService(userId,userPass);
        System.out.println("DAO 과정 끝난 후 userNo 검사 : "+userNo);
        HttpSession session = req.getSession();
        session.setAttribute("user_no",userNo);
        resp.sendRedirect("/board/views/main.jsp");

        // 진행
        // 세션 박고 redirect


    }
}
