package user.controller;
import user.service.UserRegistService;
import user.dto.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

@WebServlet("/UserRegistController")
public class UserRegistController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistService userRegistService = new UserRegistService();

        String user_id = req.getParameter("user_id");
        String user_pass = req.getParameter("user_pass");
        String user_email = req.getParameter("user_email");
        String user_name = req.getParameter("user_name");
        String user_gender = req.getParameter("user_gender");
        Timestamp user_birth = Timestamp.valueOf(req.getParameter("user_birth"));

        userRegistService.checkUserBeforeRegist(user_id);

    }
}
