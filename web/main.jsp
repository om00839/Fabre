<%@	page language = "java"
	errorPage="errorpage.jsp" 
	import="java.sql.*, java.util.*, Fabre.Bean.*, java.net.URLEncoder"
	contentType = "text/html; charset=UTF-8"
	pageEncoding = "UTF-8"
  session = "true"
%>

<jsp:useBean id="user" scope="session" class="Fabre.Bean.UserBean" />
<jsp:useBean id="crawler" scope="session" class="Fabre.Bean.CrawlerBean" />
<jsp:useBean id="article" scope="session" class="Fabre.Bean.ArticleBean" />

<html>

  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="./css/default.css">
    <title>Fabre - MainPage</title>

  

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript">
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

    session.setAttribute("user", user);
    session.setAttribute("auth", auth);

    
  %>

  <div class="wrapper">
    <header>
      <!--헤더 왼쪽 로고-->

      <div class="header-logo">
        <img src="./images/logo.png" alt="logo" style="height:60px; width: auto;">
      </div>

      <!-- 헤더 오른쪽 로그아웃/내정보페이지 -->
      <div class="header-menunav" id="nav">
        <span>
          Main Page
          <img src="./images/menu_down.svg" alt="menu_down" style="height : 13px; width : auto;">
        </span>

        <ul class="header-menunav-slidemenu" id="slidemenu">
          <li><a target="_top">Main Page</a></li>
          <li><a href="./setting_user.jsp">Setting Page</a></li>
          <li><a href="LogoutServlet">Logout</a></li>
        </ul>

      </div>

    </header>


    <div class="container">
      <!-- 크롤러 서블릿들 -->

      <nav>
        <!-- 상단에 크롤러 서블릿 추가하기 -->

        <div class="nav-header">내 웹페이지 리스트</div>
        <ul class="nav-list">

          <%

          ArrayList cList = (ArrayList) request.getAttribute("cList");

          for (int i = 0; i<cList.size(); i++){
        	  
        	  crawler = (CrawlerBean) cList.get(i);
        	  out.write("<li><a target=\"_blank\" href=\"" + crawler.getC_url() + "\">" + crawler.getC_name() +"</a></li>");

          }

          %>      

        </ul>

      </nav>


      <article>

        <!-- servlet or php로 내 웹페이지 불러오기 -->

        <div class="article-header"></div>


        <div class="article-notice">
          <h4>
            <img src="./images/megaphone.svg" alt="megaphone" style="width:35px;">
            새로운 공지가 올라왔습니다.
          </h4>
          <p>반갑습니다.</p>
        </div>

        
          <%
          ArrayList aList = (ArrayList) request.getAttribute("aList");

          for (int i = 0; i<aList.size(); i++){
        	  
        	  article = (ArticleBean) aList.get(i);
        	  
        	  out.write("<div class=\"article-content\">");
        	  out.write("<h4>"+article.getA_title()+"</h4>");
        	  out.write("<p> date : "+article.getA_date()+"</p>");
        	  out.write("<div class=\'article-content-urlbox\'>");
        	  out.write("<a href=\'"+article.getA_url()+"\' target=\'_blank\'>"+article.getA_url()+"</a></div></div>");
        	  
          }

          %>

      </article>
    </div>

      <footer>
      <p>
        Fabre 웹시스템 개발 프로젝트
      </p>
    </footer>


    
 </body>
</html>
