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
        <div class="board-container" data-user_no="<%= boardDTO.getUser_no()%>" data-board_no="<%= boardDTO.getBoard_no()%>">
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
                    <input type="button" class="btn-design" value="수정 하기" onclick="showBoardForm(this)">
                    <input type="button" class="btn-design" value="삭제 하기" onclick="">
                </div>
            </div>
            <div class="board-update-wrapper">
                <div class="comment-info-container">
                    <div class="author-container">
                        <span class="author"><%=boardDTO.getUser_name() %></span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time"><%=boardDTO.getCreate_at() %></span>
                    </div>
                </div>
                <form method="post" action="/board/UpdateBoardController" >
                    <div class="board-update-content-box">
                        <input type="text" class="title board-update-title" value="<%= boardDTO.getTitle() %>"></h2>
                    </div>
                    <div class="board-update-content-box">
                        <textarea class="writing-place-board board-update-content" name="comment_content"><%= boardDTO.getContent() %></textarea>
                    </div>
                    <div class="hidden-btn-container">
                        <input type="button" class="btn-design update-board-btn" value="수정 하기" onclick="">
                        <input type="reset" class="btn-design" value="취소" onclick="cancleBoardForm(this)">
                    </div>
                </form>
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
                        <input type="submit" class="btn-design" value="댓글 쓰기">
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
        <div class="comment-container" data-user_no="<%= commentDTOList.get(i).getUser_no() %>" data-comment_no="<%= commentDTOList.get(i).getComment_no() %>">
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
                    <input type="button" class="btn-design" value="수정 하기" onclick="showCommentForm(this)">
                    <input type="button" class="btn-design" value="삭제 하기" onclick="">
                </div>
            </div>
            <div class="comment-update-wrapper">
                <div class="comment-info-container">
                    <div class="author-container">
                        <span class="author"><%=commentDTOList.get(i).getUser_name() %></span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time"><%=commentDTOList.get(i).getCreate_at() %></span>
                    </div>
                </div>
                <form method="post" action="/board/UpdateCommentController" >
                    <div class="comment-update-content-box">
                        <textarea class="writing-place-comment" name="comment_content"><%= commentDTOList.get(i).getContent() %></textarea>
                    </div>
                    <div class="hidden-btn-container">
                        <input type="submit" class="btn-design update-comment-btn" value="수정 하기" onclick="">
                        <input type="reset" class="btn-design" value="취소" onclick="cancleCommentForm(this)">
                    </div>
                </form>
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
                        <input type="submit" class="btn-design" value="답글 쓰기">
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
        <div class="reply-container" data-user_no="<%= commentDTOList.get(i).getUser_no() %>" data-comment_no="<%= commentDTOList.get(i).getComment_no() %>">
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
                    <input type="button" class="btn-design" value="수정 하기" onclick="showReplyForm(this)">
                    <input type="button" class="btn-design" value="삭제 하기" onclick="">
                </div>
            </div>
            <div class="reply-update-wrapper">
                <div class="comment-info-container">
                    <div class="author-container">
                        <span class="author"><%=commentDTOList.get(i).getUser_name() %></span>
                    </div>
                    <div class="create-time-container">
                        <span class="create-time"><%=commentDTOList.get(i).getCreate_at() %></span>
                    </div>
                </div>
                <form method="post" action="/board/UpdateCommentController" >
                    <div class="comment-update-content-box">
                        <textarea class="writing-place-comment" name="comment_content"><%= commentDTOList.get(i).getContent() %></textarea>
                    </div>
                    <div class="hidden-btn-container">
                        <input type="submit" class="btn-design update-comment-btn" value="수정 하기" onclick="">
                        <input type="reset" class="btn-design" value="취소" onclick="cancleReplyForm(this)">
                    </div>
                </form>
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
                        <input type="submit" class="btn-design" value="답글 쓰기">
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
    commentContainerArr.forEach( (commentContainer) => {
        if(commentContainer.getAttribute("data-user_no") !== sessionId){
            const hiddenBtnContainer = commentContainer.querySelector(".hidden-btn-container");
            hiddenBtnContainer.classList.add('invalid');
        }

    });
    const replyContainerArr = document.querySelectorAll(".reply-container");
    replyContainerArr.forEach( (replyContainer) => {
        if(replyContainer.getAttribute("data-user_no") !== sessionId){
            //userid와 세션아이디가 다른경우 유효하지않은 버튼으로처리
            const hiddenBtnContainer = replyContainer.querySelector(".hidden-btn-container");
            hiddenBtnContainer.classList.add('invalid');
        }
    })
</script>
<script>
function showReplyForm(elem){
    const replyUpdateBtn = elem;
    let replyWrapper = replyUpdateBtn.closest(".reply-wrapper");
    replyWrapper.style.display = "none";
    let replyUpdateWrapper = replyWrapper.closest(".reply-container").querySelector(".reply-update-wrapper");
    replyUpdateWrapper.style.display = "block";

}
function showCommentForm(elem){
    const commentUpdateBtn = elem;
    let commentWrapper = commentUpdateBtn.closest(".comment-wrapper");
    commentWrapper.style.display = "none";
    let commentUpdateWrapper = commentWrapper.closest(".comment-container").querySelector(".comment-update-wrapper");
    commentUpdateWrapper.style.display = "block";
}

function showBoardForm(elem){
    const boardBtn = elem;
    let boardWrapper = boardBtn.closest(".board-wrapper");
    boardWrapper.style.display = "none";
    let boardUpdateWrapper = boardWrapper.closest(".board-container").querySelector(".board-update-wrapper");
    boardUpdateWrapper.style.display = "block";


}
function cancleReplyForm(elem){
    const replyCancleBtn = elem;
    let replyUpdateWrapper = replyCancleBtn.closest(".reply-update-wrapper");
    replyUpdateWrapper.style.display = "none";
    let replyWrapper = replyUpdateWrapper.closest(".reply-container").querySelector(".reply-wrapper");
    replyWrapper.style.display ="block";

}
function cancleCommentForm(elem){
    const commentCancleBtn = elem;
    let commentUpdateWrapper = commentCancleBtn.closest(".comment-update-wrapper");
    commentUpdateWrapper.style.display = "none";
    let commentWrapper = commentUpdateWrapper.closest(".comment-container").querySelector(".comment-wrapper");
    commentWrapper.style.display ="block";

}
function cancleBoardForm(elem){
    const cancleBtn = elem;
    let boardUpdateWrapper = cancleBtn.closest(".board-update-wrapper");
    boardUpdateWrapper.style.display = "none";
    let boardWrapper = boardUpdateWrapper.closest(".board-container").querySelector(".board-wrapper");
    boardWrapper.style.display ="block";

}

const updateCommentBtnArr = document.querySelectorAll(".update-comment-btn");
replyContainerArr.forEach( (updateCommentBtn) => {
        updateCommentBtn.addEventListener("click",
            (e) => {
            if(!confirm("수정하시겠습니까?")){
                console.log("아니요");


            } else{

            }
        }
}

board.querySelector(".update-board-btn").addEventListener("click",
    (e) => {
    if(!confirm("수정하시겠습니까?")){
        console.log("아니요");


    } else{
        console.log("예");
        console.log(e.target);
        let board_no = e.target.closest(".board-container").getAttribute("data-board_no");
        let user_no = e.target.closest(".board-container").getAttribute("data-user_no");

        let updateTitle = e.target.closest(".board-container").querySelector(".board-update-title").value;
        let updateContent = e.target.closest(".board-container").querySelector(".board-update-content").value;
        let updateBtn = e.target;

        const xhttp = new XMLHttpRequest();
        console.log(updateTitle);
        console.log(updateContent);
        xhttp.open("POST", "/board/UpdateBoardController", true);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); // 보낼 데이터 헤더를 지정

        let postParameter = "board_no=" + board_no + "&user_no=" + user_no + "&updateTitle=" + updateTitle + "&updateContent=" + updateContent;
        console.log("변수를 따로 만들고 출력");
        console.log(postParameter);
        xhttp.send(postParameter);

        xhttp.onload = function() {
            if (xhttp.status === 200) {
                const responseText = xhttp.responseText;
                const [title, content] = responseText.split("|"); // 구분자로 나눔

                console.log("제목:", title);
                console.log("내용:", content);
                updateBtn.closest(".board-container").querySelector(".title").innerText = title;
                updateBtn.closest(".board-container").querySelector(".board-content").innerText = content;



                let boardUpdateWrapper = updateBtn.closest(".board-update-wrapper");
                boardUpdateWrapper.style.display = "none";
                let boardWrapper = boardUpdateWrapper.closest(".board-container").querySelector(".board-wrapper");
                boardWrapper.style.display ="block";
            }
        };
    }

    });

</script>
</body>
</html>