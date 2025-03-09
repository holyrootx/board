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
    <link rel="stylesheet" href="/board/static/css/default.css">
    <link rel="stylesheet" href="/board/static/css/header.css">
    <link rel="stylesheet" href="/board/static/css/footer.css">

    <title>Main Page</title>

</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");

        Integer user_no = (Integer)session.getAttribute("user_no");
        System.out.println((Integer)session.getAttribute("user_no"));
        if(user_no == null){
    %>
        <jsp:include page="/static/html/guest_header.html"/>
    <%
        } else{
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
        <h1 >메인</h1>
        <h1> 자자</h1>
        <h1> 자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
        <h1>자자</h1>
    </div>


     <!-- 여기다가 MAIN 코드 작성-->
    <jsp:include page="/static/html/footer.html"/>
</body>
</html>