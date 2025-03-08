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
    <link rel="stylesheet" href="../static/css/userregist.css">
    <!-- J-query 연결 Embed Code Start-->
    <script src="https://code.jquery.com/jquery-3.3.0.js" integrity="sha256-TFWSuDJt6kS+huV+vVlyV1jM3dwGdeNWqezhTxXB/X8=" crossorigin="anonymous"></script>
    <!-- J-query 연결 Embed Code End-->
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
  <h1>회원가입</h1>

  <div class="form-container">
    <h2 class="form-intro">회원가입</h2>
    <form method="POST" action="/board/UserRegistController" class="form-wrapper">
        <!-- ContextPath/Controller경로 -->
        <div class="form-input-container posi-relative">
            <input type="text" class="input-design id-check" id="user_id_input"name="user_id" placeholder="아이디 입력" required>
            <button class="id-check id-check-btn-design" id="user_id_check" >아이디 중복 검사</button>
        </div>
        <div class="form-input-container">
            <input type="password" class="input-design" id="user_pass" name="user_pass" placeholder="비밀번호 입력" required>
        </div>
        <div class="form-input-container">
            <input type="password" class="input-design" id="user_pass_check" placeholder="비밀번호 확인" required>
        </div>
        <div class="form-input-container">
            <input type="text" class="input-design" name="user_name" placeholder="이름 입력" required>
        </div>
        <div class="form-input-container">
            <input type="email" class="input-design" name="user_email" placeholder="이메일 입력" required>
        </div>
        <div class="form-input-container">
            <input type="text" class="input-design" name="user_birth" placeholder="YYYY-MM-DD" required>

        </div>

        <div>
            <div>
              남자 : <input type="radio" class="input-radio-design" name="user_gender" value="남" checked required>
            </div>
            <div>
              여자 : <input type="radio" class="input-radio-design" name="user_gender" value="여" required>
            </div>
        </div>

        <div>
            <input type="submit" class="btn-design make-pudding">
        </div>
    </form>
  </div>

</div>


     <!-- 여기다가 MAIN 코드 작성-->
    <jsp:include page="/static/html/footer.html"/>
</body>
</html>