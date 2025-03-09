package board.controller;
import board.dto.BoardDTO;
import board.service.WriteBoardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
@WebServlet("/WriteBoardController")
public class WriteBoardController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();


        String boardTitle = req.getParameter("board_title");
        String boardContent = req.getParameter("board_content");
        Integer userNo = (Integer)session.getAttribute("user_no");

        System.out.println("WriteBoardController에서 boardTitle 값 확인 : " + boardTitle);
        System.out.println("WriteBoardController에서 boardContent 값 확인 : " + boardContent);
        System.out.println("WriteBoardController에서 userNo 값 확인 : " + userNo);

        if(userNo == null){

            // 세션이 만료되었습니다.
            //
            resp.sendRedirect("/board/views/userlogin.jsp");
            System.out.println("세션 만료 ");
        }

        WriteBoardService writeBoardService = new WriteBoardService();
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(boardTitle);
        boardDTO.setContent(boardContent);
        boardDTO.setUser_no(userNo.intValue());

        boolean isWritingSuccessed = writeBoardService.writeBoardSerivce(boardDTO);
        if (!isWritingSuccessed){
            System.out.println("모종의 이유로 실패");
        }

        resp.sendRedirect("/board//ShowBoardListController");

    }
}
