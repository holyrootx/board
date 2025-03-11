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
        Integer board_no = (Integer)request.getAttribute("board_no");
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
        <div class="board-container" data-user_no="<%= boardDTO.getUser_no()%>">
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
                <div class="hidden-btn-container">
                    <input type="button" class="hidden-btn update-btn" value="수정 하기" onclick="">
                    <input type="button" class="hidden-btn delete-btn" value="삭제 하기" onclick="">
                </div>
            </div>
        </div>
        <div class="comment-form-container">
            <div class="comment-wrapper">
                <p class="comment-container-title">댓글을 남겨주세요</p>
                <form action="/board/WriteCommentController?ref=0&board_no=<%=board_no%>" method="POST">
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
        // 댓글 인경우
    %>
        <div class="comment-container" data-user_no="<%= commentDTOList.get(i).getUser_no() %>">
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
                <div class="hidden-btn-container">
                    <input type="button" class="hidden-btn update-btn" value="수정 하기" onclick="">
                    <input type="button" class="hidden-btn delete-btn" value="삭제 하기" onclick="">
                </div>
            </div>
        </div>
        <%
        if(i + 1 == commentLength
                    || commentDTOList.get(i+1).getRef() != commentDTOList.get(i).getComment_no() ){

        // 다음글이 없는 경우 이글이 마지막인 경우이거나, 다음글이 댓글인 경우
        %>
        <div class="reply-form-container">
            <div class="reply-wrapper">
                <p class="comment-container-title">답글을 남겨주세요</p>
                <form action="/board/WriteCommentController" method="POST">
                    <div class="writing-place-container">
                        <textarea class="writing-place-comment" name="comment_content"></textarea>
                    </div>
                    <div class="btn-container">
                        <input type="text" name="board_no" value="<%=board_no%>" style="display:none;">
                        <input type="text" name="ref" value="<%=commentDTOList.get(i).getComment_no()%>" style="display:none;">
                        <input type="submit" class="write-btn" value="답글 쓰기">
                    </div>
                </form>

            </div>
        </div>
        <%
        }
        %>
    <%
        } else{

    %>
        <div class="reply-container" data-user_no="<%= commentDTOList.get(i).getUser_no() %>">
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
                <div class="hidden-btn-container">
                    <input type="button" class="hidden-btn update-btn" value="수정 하기" onclick="">
                    <input type="button" class="hidden-btn delete-btn" value="삭제 하기" onclick="">
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
                <form action="/board/WriteCommentController" method="POST">
                    <div class="writing-place-container">
                        <textarea class="writing-place-comment" name="comment_content"></textarea>
                    </div>
                    <div class="btn-container">
                        <input type="text" name="board_no" value="<%=board_no%>" style="display:none;">
                        <input type="text" name="ref" value="<%=commentDTOList.get(i).getRef()%>" style="display:none;">
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
    let sessionId = <%=user_no%>;
    sessionId = sessionId.toString();

    const board = document.querySelector(".board-container");

    const commentContainerArr = document.querySelectorAll(".comment-container");
    commentContainerArr.forEach((commentContainer)=>{
        if(commentContainer.getAttribute("data-user_no") !== sessionId){
            const hiddenBtnContainer = commentContainer.querySelector(".hidden-btn-container");
            hiddenBtnContainer.style.display = "none";
        }

    });
    const replyContainerArr = document.querySelectorAll(".reply-container");
    replyContainerArr.forEach((replyContainer) => {
        if(replyContainer.getAttribute("data-user_no") !== sessionId){
            const hiddenBtnContainer = replyContainer.querySelector(".hidden-btn-container");
            hiddenBtnContainer.style.display = "none";
        }
    })
</script>
</body>
</html>