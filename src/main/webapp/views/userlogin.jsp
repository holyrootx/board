<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Fontawesome 연결 Embed Code Start-->
    <script src="https://kit.fontawesome.com/0f39c44f6d.js" crossorigin="anonymous"></script> <!-- Fontawesome 연결 -->
    <!-- Fontawesome 연결 Embed Code End-->
    <link rel="stylesheet" href="../static/css/default.css">
    <link rel="stylesheet" href="../static/css/footer.css">
    <link rel="stylesheet" href="../static/css/header.css">
    <title>Main Page</title>

</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
    %>
    <jsp:include page="/static/html/guest_header.html"/>
    <!-- 여기다가 MAIN 코드 작성-->
    <!-- 전체 배경색 var(--black-bg-container);-->
    <!-- Content 배경색 var(--black-5);-->
    <!-- Content 글자색 var(--white-5; 추천-->


    <div class="container">
        <h1 >로그인</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <div>
            <!-- ContextPath/Controller경로 -->
            <form method="POST" action="/board/UserLoginController">
            <div>
                <input type="text" name="user_id" placeholder="아이디 입력">
            </div>
            <div>
                <input type="pass" name="user_pass" placeholder="비밀번호 입력">
            </div>
            <div>
                <input type="submit">
            </div>
            </form>
        </div>

        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
        <h1 >자자</h1>
    </div>


     <!-- 여기다가 MAIN 코드 작성-->
    <jsp:include page="/static/html/footer.html"/>
</body>
</html>