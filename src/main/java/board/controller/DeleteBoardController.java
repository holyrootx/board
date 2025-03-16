package board.controller;

import board.dto.BoardDTO;
import board.service.DeleteBoardService;
import board.service.UpdateBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class DeleteBoardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String user_no = req.getParameter("user_no");
        HttpSession session = req.getSession();

        String session_user_no = ((Integer)session.getAttribute("user_no")).toString();

        System.out.println(user_no+"<- get User_no : -> session"+session_user_no);
        System.out.println(user_no.getClass().getName());
        System.out.println(session_user_no.getClass().getName());
        // 세션 아이디 체크
        if( !(session_user_no.equals(user_no)) ){
            System.out.println("잘못된 접근입니다.");
            return;
        }

        // board_no 체크
        String board_no = req.getParameter("board_no");
        if(board_no == null){
            System.out.println("(UpdateBoardController) board_no가 Null인 현상 발생 데이터 전달 현황 체크 바람.");
            return;
        }


        // 여기서 board값 세팅 하고나서 update 하면 될듯

        resp.setContentType("text/plain; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        DeleteBoardService deleteBoardService = new DeleteBoardService();
        boolean isUpdatingSuccessed = false;

        isUpdatingSuccessed = deleteBoardService.deleteBoard(board_no);

        if(!isUpdatingSuccessed){
            System.out.println("(UpdateBoardController) UpdateBoard메서드에 이상 발생");
            return;
        }


        // `|` 같은 구분자로 문자열 연결
        // resp.getWriter().write(title + "|" + content);
    }
}
