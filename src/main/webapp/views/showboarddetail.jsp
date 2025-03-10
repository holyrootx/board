<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "board.dto.BoardDTO" %>
<%@ page import = "comment.dto.CommentDTO" %>
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
        int commentLength = 0;
        Integer user_no = (Integer)session.getAttribute("user_no");
        ArrayList<CommentDTO> commentDTOList = new ArrayList<>();
        BoardDTO boardDTO = null;
        if(user_no == null){
    %>
        <jsp:include page="/static/html/guest_header.html"/>
        <script type="text/javascript">
            alert("로그인 후 접근가능한 페이지 입니다.");
            window.location.href = "/board/views/userlogin.jsp";
        </script>
    <%

        } else{
        // 로그인이 된 사용자면

    %>

        <jsp:include page="/static/html/auth_header.html"/>
    <%
        boardDTO = (BoardDTO)request.getAttribute("boardDTO");
        commentDTOList = (ArrayList<CommentDTO>)request.getAttribute("commentDTOList");

        commentLength = commentDTOList.size();

        }
    %>
    <!-- Main-container Start-->
    <div class="container">
        <div class="board-container">
            <div class="board-wrapper">
                <h2 class="title"><%=boardDTO.getTitle()%></h2>
                <div class="board-info-container">
                    <div class="author-container">
                        <span class="author"><%=boardDTO.getUser_name()%></span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time"><%=boardDTO.getCreate_at()%></span>
                    </div>
                </div>

                <div class="content-box">
                    <p class="board-content"><%=boardDTO.getContent()%></p>
                </div>
            </div>
        </div>
        <div class="comment-form-container">
            <div class="comment-wrapper">
                <p class="comment-container-title">댓글을 남겨주세요</p>
                <form action="/comment/WriteCommentController?ref=0" method="POST">
                    <div class="writing-place-container">
                        <textarea class="writing-place-comment" name="comment_content"></textarea>
                    </div>
                    <div class="btn-container">
                        <input type="submit" class="write-btn" value="댓글 쓰기">
                    </div>
                </form>
            </div>
        </div>
    <%
        if(commentLength == 0){
            System.out.println(commentDTOList.size()+"jsp에서 실행됨");
    %>
        <h2>댓글이 없습니다.</h2>
    <%
        } else {
        for(int i = 0; i <  commentLength; i++){
    %>
    <%
        if (commentDTOList.get(i).getRef() == 0){

    %>
        <div class="comment-container">
            <div class="comment-wrapper">
                <div class="comment-info-container">
                    <div class="author-container">
                        <span class="author"><%=commentDTOList.get(i).getUser_name() %></span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time"><%=commentDTOList.get(i).getCreate_at() %></span>
                    </div>
                </div>
                <div class="comment-content-box">
                    <p class="comment-content"><%=commentDTOList.get(i).getContent() %></p>
                </div>
            </div>
        </div>
    <%
        } else{

    %>
        <div class="reply-container">
            <div class="reply-wrapper">
                <div class="reply-info-container">
                    <div class="author-container">
                        <span class="author"><%=commentDTOList.get(i).getUser_name() %></span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time"><%=commentDTOList.get(i).getCreate_at() %></span>
                    </div>
                </div>
                <div class="comment-content-box">
                    <p class="comment-content"><%=commentDTOList.get(i).getContent() %></p>
                </div>
            </div>
        </div>
        <%
        if(i + 1 == commentLength || commentDTOList.get(i+1).getRef() == 0){
            // 다음이 없거나, 다음 댓글 객체가 답글이아닌 댓글 인 경우
        %>
        <div class="reply-form-container">
            <div class="reply-wrapper">
                <p class="comment-container-title">답글을 남겨주세요</p>
                <form action="/comment/WriteCommentController?ref=<%=commentDTOList.get(i).getRef()%>" method="POST">
                    <div class="writing-place-container">
                        <textarea class="writing-place-comment" name="comment_content"></textarea>
                    </div>
                    <div class="btn-container">
                        <input type="submit" class="write-btn" value="답글 쓰기">
                    </div>
                </form>

            </div>
        </div>
     <%
     //if문
     }
     // else문 닫는 대괄호
     }
     // for문 닫는 대괄호
     }
     // else문 닫는 대괄호
     }
     %>
    </div>

    <!-- Main-container End-->
    <jsp:include page="/static/html/footer.html"/>

    <script>
    </script>

</body>
</html>