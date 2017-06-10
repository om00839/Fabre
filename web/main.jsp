<%@	page language = "java"
	import="java.sql.*, java.util.*, Fabre.Bean.*, java.net.URLEncoder"
	contentType = "text/html; charset=UTF-8"
	pageEncoding = "UTF-8"
  session = "true"
%>


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
    }else{

    session.setAttribute("auth", auth);

    }
    
    if (auth==null) {
      try {
        response.sendRedirect("./login.html");
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

      ArrayList cList = (ArrayList) request.getAttribute("cList");
      if(cList==null){ 
        cList = (ArrayList) session.getAttribute("cList");
      }else{
      
        session.setAttribute("cList", cList);

      }

      ArrayList aList = (ArrayList) request.getAttribute("aList");
      if(aList==null){ 

        aList = (ArrayList) session.getAttribute("aList");

      }else{
      
        session.setAttribute("aList", aList);

      }
    
    

    
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
          <li><a href="./main.jsp">Main Page</a></li>
          <li><a href="./setting_user.jsp">Setting Page</a></li>
          <li><a href="./LogoutServlet">Logout</a></li>
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

            for (int i = 0; i<cList.size(); i++){
          	  
          	  CrawlerBean crawler = (CrawlerBean) cList.get(i);
              String c_url = crawler.getC_url();
              String c_name = crawler.getC_name();

          	  out.write("<li><a target=\"_blank\" href=\"" + c_url + "\">" + c_name +"</a></li>");

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

            for (int i = 0; i<aList.size(); i++){
          	  
          	  ArticleBean article = (ArticleBean) aList.get(i);

              String a_title = article.getA_title();
              String a_date = article.getA_date();
              String a_url = article.getA_url();
          	  
          	  out.write("<div class=\"article-content\">");
          	  out.write("<h4>"+ a_title +"</h4>");
          	  out.write("<p> date : "+a_date+"</p>");
          	  out.write("<div class=\'article-content-urlbox\'>");
          	  out.write("<a href=\'"+a_url+"\' target=\'_blank\'>"+a_url+"</a></div></div>");
          	  
            }

          %>

      </article>
    </div>

      <footer>
      <p>
        Fabre
      </p>
    </footer>


    
 </body>
</html>
