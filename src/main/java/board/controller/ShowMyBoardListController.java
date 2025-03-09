package board.controller;
import board.dto.BoardDTO;
import board.service.ShowMyBoardListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowMyBoardListController")
public class ShowMyBoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Integer user_no = (Integer)session.getAttribute("user_no");
        if(user_no == null){
            System.out.println("(ShowMyBoard)로그인 되어 있지 않습니다.");
            return;
        }

        ArrayList<BoardDTO> myBoardList = new ArrayList<>();
        ShowMyBoardListService showMyBoardListService = new ShowMyBoardListService();
        myBoardList = showMyBoardListService.showMyBoardList(user_no);

        req.setAttribute("myBoardList", myBoardList);
        System.out.println("request에 등록");
        getServletContext().getRequestDispatcher("/views/showmyboardlist.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
