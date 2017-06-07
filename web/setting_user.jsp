<%@	page language = "java"
	import="java.sql.*, java.util.*, Fabre.Bean.*, java.net.URLEncoder, java.net.URLDecoder"
	contentType = "text/html; charset=UTF-8"
	pageEncoding = "UTF-8"
  session = "true"
%>


<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="./css/default.css">
    <title>Fabre - MainPage</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>        
      $(document).ready(function() {
          $(".header-menunav>span").click(function() {
              $(".header-menunav-slidemenu").slideToggle("slow");
          });
      });
    </script>
    
    <script type="text/javascript">

      var msg = <%=request.getAttribute("msg")%>;
      
      if (msg != null) {
        window.alert(msg);
      }

    </script>

    <script type="text/javascript">
    
    function formValidation() {

        var check = false;
        
        var check2;
        var check3;


        var u_password = document.forms["registration"]["u_password"].value;
        var u_passwordCheck = document.forms["registration"]["u_passwordCheck"].value;

        var checkPassword = document.getElementById("checkPassword");

        if (u_password != null ){

             if (u_password.length > 5 | u_password.length <= 12) {

                checkPassword.textContent = "OK!";
                checkPassword.setAttribute("style", "color : rgb(83,140,255); font-weight = bold;");
                check2 = true;

            } else {

                checkPassword.textContent = "Not Valid! 비밀번호는 5자리 이상 12자리 이하 입니다.";
                checkPassword.setAttribute("style", "color : rgb(255,87,83); font-weight = bold;");
                check2 = false;

            }

        }

       var checkPasswordCheck = document.getElementById("checkPasswordCheck");

        if (u_passwordCheck != null) {

            if (u_passwordCheck==u_password) {

                checkPasswordCheck.textContent = "OK!";
                checkPasswordCheck.setAttribute("style", "color : rgb(83,140,255); font-weight = bold;");
                check3 = true;

            } else {

                checkPasswordCheck.textContent = "Not Valid! 비밀번호가 다릅니다.";
                checkPasswordCheck.setAttribute("style", "color : rgb(255,87,83); font-weight = bold;");
                check3 = false;

            }

        }

        if (check2&&check3){
            check = true;
        }else{
            check = false;
        }


        return check;

    }

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
    
    UserBean user = (UserBean) request.getAttribute("user");
    if(user==null){

      user = (UserBean) session.getAttribute("user");
      
    }else{

      session.setAttribute("user", user);

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
          Setting Page
          <img src="./images/menu_down.svg" alt="menu_down" style="height : 13px; width : auto;">
        </span>

        <ul class="header-menunav-slidemenu" id="slidemenu">
          <li><a href="./main.jsp">Main Page</a></li>
          <li><a target="./setting_user.jsp">Setting Page</a></li>
          <li><a href="LogoutServlet">Logout</a></li>
        </ul>
      </div>

    </header>


    <div class="container">

            <nav>
                <div class="nav-header">기본정보수정</div>
                <ul class="nav-list">
                    <li class="nav-thisPage"><a target="_top">기본정보수정</a></li>
                    <li><a href="./setting_uc_setting.jsp">내 크롤러 관리</a></li>
                    <li><a href="./setting_crawler.jsp">크롤러 추가</a></li>
                </ul>
            </nav>

            <article id="first-registration">
                <div class="article-header"> </div>
                <div class="article-main">

                    <form name="usersetting" class="registration-main" action="UpdateUserServlet" method="post" onsubmit="return formValidation();">

                      <%

                        String u_email = user.getU_email();
                        String u_nickname = user.getU_nickname();

                        out.write("<div class=\"registration-main-email\">");
                        out.write("<span>이메일</span>");
                        out.write("<input type=\"text\" readonly = \"readonly\" name=\"u_email\" value=\""+u_email+"\">");
                        out.write("</div>");

                        out.write("<div class=\"registration-main-nickname\">");
                        out.write("<span>닉네임</span>");
                        out.write("<input type=\"text\" readonly = \"readonly\" value=\""+u_nickname+"\" name=\"u_nickname\">");
                        out.write("</div>");


                      %>
                        
                      <div class="registration-main-password">
                          <span>비밀번호수정</span>
                          <input type="password" placeholder="비밀번호" name="u_password">
                      </div>
                      <p>비밀번호 (5~12자리)를 입력해주세요.</p>
                      <div class="registration-main-pwcheck">
                          <span>비밀번호확인</span>
                          <input type="password" placeholder="비밀번호확인" name="u_passwordCheck">
                      </div>
                      <p id="checkPassword"> 비밀번호를 다시 입력해주세요.</p>
                      <button type="submit" name="registration">수정</button>

                    </form>
                    
                </div>
            </article>
        </div>


      <footer>
      <p>
        Fabre
      </p>
    </footer>


    
 </body>
</html>
