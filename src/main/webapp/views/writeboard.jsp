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
    <link rel="stylesheet" href="/board/static/css/writeboard.css">
    <title>showboardlist</title>

</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        Integer user_no = (Integer)session.getAttribute("user_no");

        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();

        if(user_no == null){
    %>
        <jsp:include page="/static/html/guest_header.html"/>
        <script type="text/javascript">
            alert("로그인 후 접근가능한 페이지 입니다.");
            window.location.href = "/board/views/userlogin.jsp";
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
    <!-- Main-container Start-->
    <div class="container">
        <div class="board-container">
          <h2 class="form-intro">글 쓰기 </h2>
            <form method="POST" action="/board/WriteBoardController">
              <div class="input-wrapper">
                <input type="text" name="board_title" class="input-title" placeholder="제목을 입력하세요">
              </div>
              <div class="input-wrapper">
                <textarea type="text" name="board_content" class="input-content"></textarea>
              </div>
              <div class="input-wrapper">
                  <input type="submit" class="input-submit" value="글쓰기">
              </div>
            </form>
        </div>

    </div>

    <!-- Main-container End-->
    <jsp:include page="/static/html/footer.html"/>
</body>
</html>