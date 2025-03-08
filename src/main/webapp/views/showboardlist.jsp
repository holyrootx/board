<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "board.dto.BoardDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Fontawesome 연결 Embed Code Start-->
    <script src="https://kit.fontawesome.com/0f39c44f6d.js" crossorigin="anonymous"></script> <!-- Fontawesome 연결 -->
    <!-- Fontawesome 연결 Embed Code End-->
    <link rel="stylesheet" href="/board/static/css/default.css">
    <link rel="stylesheet" href="/board/static/css/header.css">
    <link rel="stylesheet" href="/board/static/css/footer.css">
    <link rel="stylesheet" href="/board/static/css/showboardlist.css">
    <title>showboardlist</title>

</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        String user_no = (String)session.getAttribute("user_no");

        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();

        if(user_no == null){
    %>
        <jsp:include page="/static/html/guest_header.html"/>
        <script type="text/javascript">
            alert("로그인 후 접근가능한 페이지 입니다.");
            window.location.href = "userlogin.jsp";
        </script>
    <%

        } else{
        boardDTOList = (ArrayList<BoardDTO>)request.getAttribute("boardDTOArrayList");
    %>

        <jsp:include page="/static/html/auth_header.html"/>
    <%
        }
    %>
    <!-- 여기다가 MAIN 코드 작성-->
    <!-- 전체 배경색 var(--black-bg-container);-->
    <!-- Content 배경색 var(--black-5); 추천-->
    <!-- Content 글자색 var(--white-5; 추천-->
    <div class="container">
    <%
    for(BoardDTO boardDTO : boardDTOList){


    %>


        <div class="board-container">
            <div class="board-wrapper">
              <a href="#" class="board-item board_no"><%=boardDTO.getBoard_no()%></a>
              <a href="#" class="board-item author"><%=boardDTO.getUser_name()%></a>
              <a href="#" class="board-item title"><%=boardDTO.getTitle()%></a>
              <a href="#" class="board-item create_at"><%=boardDTO.getCreate_at()%></a>
            </div>
        </div>
    <%
    }
    %>
    </div>
     <!-- 여기다가 MAIN 코드 작성-->
    <jsp:include page="/static/html/footer.html"/>
</body>
</html>