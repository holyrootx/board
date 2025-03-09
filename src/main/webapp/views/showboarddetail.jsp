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
    <link rel="stylesheet" href="/board/static/css/showboarddetail.css">
    <title>showboardDetaillist</title>

</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        Integer user_no = (Integer)session.getAttribute("user_no");

        if(user_no == null){
    %>
        <jsp:include page="/static/html/guest_header.html"/>
        <script type="text/javascript">
            alert("로그인 후 접근가능한 페이지 입니다.");
            window.location.href = "/board/views/userlogin.jsp";
        </script>
    <%

        } else{

    %>

        <jsp:include page="/static/html/auth_header.html"/>
    <%
        }
    %>
    <!-- Main-container Start-->
    <div class="container">
        <div class="board-container">
            <div class="board-wrapper">
                <h2 class="title">아 진짜 개쩌는 일 있었음</h2>
                <div class="board-info-container">
                    <div class="author-container">
                        <span class="author">김용식</span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time">2025-03-09 20:08:34</span>
                    </div>
                </div>

                <div class="content-box">
                    <p class="board-content">
                        zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz<br>

                    </p>
                </div>
            </div>
        </div>
        <div class="comment-container">
            <div class="comment-wrapper">
                <p class="comment-continer-title">댓글을 남겨주세요</p>
                <form action="/comment/WriteCommentController" method="POST">
                    <div class="writing-place-container">
                        <textarea class="writing-place-comment" name="comment_content"></textarea>
                    </div>
                    <div class="btn-container">
                        <input type="submit" class="write-btn" value="댓글 쓰기">
                    </div>
                </form>
            </div>
        </div>
        <div class="comment-container">
            <div class="comment-wrapper">
                <div class="comment-info-container">
                    <div class="author-container">
                        <span class="author">김용식</span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time">2025-03-09 20:08:34</span>
                    </div>
                </div>
                <div class="comment-content-box">
                    <p class="comment-content">
                        zz
                    </p>
                </div>
            </div>
        </div>
        <div class="reply-container">
            <div class="reply-wrapper">
                <div class="reply-info-container">
                    <div class="author-container">
                        <span class="author">김용식</span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time">2025-03-09 20:08:34</span>
                    </div>
                </div>
                <div class="comment-content-box">
                    <p class="comment-content">
                        나가
                    </p>
                </div>
            </div>
        </div>
        <div class="reply-container">
            <div class="reply-wrapper">
                <p class="comment-continer-title">답글을 남겨주세요</p>
                <form action="/comment/WriteCommentController" method="POST">
                    <div class="writing-place-container">
                        <textarea class="writing-place-comment" name="comment_content"></textarea>
                    </div>
                    <div class="btn-container">
                        <input type="submit" class="write-btn" value="답글 쓰기">
                    </div>
                </form>

            </div>
        </div>
    </div>

    <!-- Main-container End-->
    <jsp:include page="/static/html/footer.html"/>
</body>
</html>