package board.controller;
import board.dto.BoardDTO;
import board.service.ShowBoardListService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.io.IOException;

@WebServlet("/ShowBoardListController")
public class ShowBoardListController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        ShowBoardListService showBoardListService = new ShowBoardListService();
        ArrayList<BoardDTO> boardDTOArrayList = showBoardListService.getBoardListService();

        req.setAttribute("boardDTOArrayList",boardDTOArrayList);
        System.out.println("Controller에서 setAttribute 완료!!!!!!!!!!");
        getServletContext().getRequestDispatcher("/views/showboardlist.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
