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
                <div class="nav-header">내 크롤러 관리</div>
                <ul class="nav-list">
                    <li>기본정보수정</li>
                    <li class="nav-thisPage">내 크롤러 관리</li>
                    <li>크롤러 추가</li>
                </ul>
            </nav>
            <article id="first-registration">
                <div class="article-header"> </div>
                <div class="article-main">
                    <div class="crawlersetting_search">
                        <form name="uc_setting" class="crawlerSetting-main" action="UC_SettingServlet" method="post">
                            <div class="crawlersetting_search_result">
                                <!-- 여기에는 검색 결과들이 표시 -->
                                <!-- 네모박스가 하나씩 나오면서 추가하기 버튼과 자세히 보기 a 이미지 -->
                                <!-- h4 c_title / p c_url / button / img  -->
                                <h3>내 크롤러 목록</h3>
                                <%

                                  c_id = crawler.getC_id();
                                  c_name = crawler.getC_name();
                                  c_url = crawler.getC_url();

                                  out.write("<div class=\"myCrawler\">");
                                  out.write("<button class=\"favorite\" type=\"submit\", name=\"favorite\">");
                                  out.write("<img src=\"./images/favorite.svg\" alt=\"favorite\" width=\"25px\" height=\"auto\">");
                                  out.write("</button>");
                                  out.write("<h4>"+c_name+"</h4>");
                                  out.write("<p class=\"myCrawler_url\">"+c_url+"</p>");
                                  out.write("<button class=\"deleteCrawler\" type=\"submit\" name=\"addCrawler\">");
                                  out.write("<img src=\"./images/deletecrawler.svg\" alt=\"addCrawler\" width=\"25px\" height=\"auto\">");
                                  out.write("</button>");
                                  out.write("</div>");

                                %>

                                
                            </div>
                        </form>
                    </div>
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
