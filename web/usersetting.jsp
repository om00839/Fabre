<%@	page language = "java"
	errorPage="errorpage.jsp" 
	import="java.sql.*, java.util.*, Fabre.Bean.*, java.net.URLEncoder, java.net.URLDecoder"
	contentType = "text/html; charset=UTF-8"
	pageEncoding = "UTF-8"
%>

<jsp:useBean id="user" scope="session" class="Fabre.Bean.UserBean" />
<jsp:useBean id="crawler" scope="session" class="Fabre.Bean.CrawlerBean" />
<jsp:useBean id="article" scope="session" class="Fabre.Bean.ArticleBean" />

<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="./css/default.css">
    <title>Fabre - MainPage</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
        $(document).ready(function() {
        $(".header-menunav>span").click(function() {
            $(".header-menunav-slidemenu").slideToggle("slow");
        });
    });
    </script>


  </head>
  <body>

  <%
    String auth = (String) request.getAttribute("auth");
    if (auth == null) {
      auth = (String)session.getAttribute("auth");
    }
    
    if (!auth.equals("ok")) {
      try {
        request.getRequestDispatcher("/login.html").forward(request, response);
      }catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    user = (UserBean) request.getAttribute("user");
    if(user==null){
      user = (UserBean) session.getAttribute("user");
    }
    
  %>

  <div class="wrapper">
    <header>
      <!--헤더 왼쪽 로고-->

      <div class="header-logo">
        <img src="./images/logo.png" alt="logo" style="height:60px; width: auto;">
      </div>

      <!-- 헤더 오른쪽 로그아웃/내정보페이지 -->
      <div class="header-menunav">
        <span>
          Main Page
          <img src="./images/menu_down.svg" alt="menu_down" style="height : 13px; width : auto;">
        </span>

        <ul class="header-menunav-slidemenu" id="slidemenu">
          <li><a href="#">Main Page</a></li>
          <li><a href="#">Setting Page</a></li>
          <li><a href="#">Developers Page</a></li>
          <li><a href="#">Logout</a></li>
        </ul>
      </div>

    </header>


    <div class="container">

            <nav>
                <div class="nav-header">기본정보수정</div>
                <ul class="nav-list">
                    <li class="nav-list-thisPage">기본정보수정</li>
                    <li>내 크롤러 관리</li>
                    <li>크롤러 추가</li>
                </ul>
            </nav>

            <article id="first-registration">
                <div class="article-header"> </div>
                <div class="article-main">
                    <form name="usersetting" class="registration-main" action="UserSettingServlet" method="post" onsubmit="return formValidation();">

                      <%

                        u_email = user.getU_email();
                        u_nickname = user.getU_nickname();

                        out.write("<div class=\"registration-main-email\">");
                        out.write("<span>이메일</span>");
                        out.write("<input type=\"text\" readonly = \"readonly\" placeholder=\""+u_email+"\" name=\"u_email\">");
                        out.write("</div>");

                        out.write("<div class=\"registration-main-nickname\">");
                        out.write("<span>닉네임</span>");
                        out.write("<input type=\"text\" readonly = \"readonly\" placeholder=\""+u_nickname+"\" name=\"u_nickname\">");
                        out.write("</div>");


                      %>
                        
                        <div class="registration-main-password">
                            <span>비밀번호수정</span>
                            <input type="password" placeholder="비밀번호" name="u_password">
                        </div>
                        <p>비밀번호를 입력해주세요 / ok/error</p>
                        <div class="registration-main-pwcheck">
                            <span>비밀번호확인</span>
                            <input type="password" placeholder="비밀번호확인" name="u_passwordCheck">
                        </div>
                        <p id="checkPassword"> 비밀번호를 다시 입력해주세요 / ok / error</p>
                        <button type="submit" name="registration">수정</button>
                    </form>
                </div>
            </article>
        </div>


      <footer>
      <p>
        Fabre 웹시스템 개발 프로젝트
      </p>
    </footer>


    
 </body>
</html>
