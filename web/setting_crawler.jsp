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

    ArrayList cList = (ArrayList) request.getAttribute("cList");
    if(cList==null){ 
      cList = (ArrayList) session.getAttribute("cList");
    }

    ArrayList aList = (ArrayList) request.getAttribute("aList");
    if(aList == null){
      aList = (ArrayList) session.getAttribute("aList");
    }

    session.setAttribute("user", user);
    session.setAttribute("auth", auth);
    session.setAttribute("cList", cList);
    session.setAttribute("aList", aList);
    
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
          <li><a target="_top">Main Page</a></li>
          <li><a href="./setting_user.jsp">Setting Page</a></li>
          <li><a href="LogoutServlet">Logout</a></li>
        </ul>
      </div>

    </header>


    <div class="container">
            <nav>
                <div class="nav-header">크롤러 추가</div>
                <ul class="nav-list">
                    <li><a href="/setting_user.jsp">기본정보수정</a></li>
                    <li><a href="./setting_uc_setting.jsp">내 크롤러 관리</a></li>
                    <li class="nav-thisPage">크롤러 추가</li>
                </ul>
            </nav>
            <article id="first-registration">
                <div class="article-header"> </div>
                <div class="article-main">
                    <div class="crawlersetting_search">
                        <form name="retrieveCrawler" action="RetrieveCrawlerServlet" method="post">
                            <div class="crawlersetting_search_box">
                                <h3>크롤러 검색</h3>
                                <div class="search_box">
                                    <input type="text" name="query">
                                    <button type="submit"></button>
                                </div>
                            </div>
                        </form> 

                        <form name = "InsertUC_SettingServlet" action="InsertUC_SettingServlet" method="post">

                        <div class="crawlersetting_search_result">
                                
                                <h3>검색 결과</h3>

                                <!-- 주의 rList는 crawler 테이블에서 가져왔다. cList와는 다른 내용을 가지고 있음 --> 

                                <%

                                ArrayList rList = (ArrayList) request.getAttribute("rList");

                                if (rList != null) {

                                  for(int i = 0; i<rList.size(); i++){

                                    CrawlerBean res = (CrawlerBean) rList.get(i);

                                    out.write("<div class=\"result_box\">");
                                    out.write("<h4>"+res.getC_name()+"</h4>");
                                    out.write("<p>"+res.getC_url()+"</p>");
                                    out.write("<input type=\"text\" hidden = \"hidden\" name=\"c_id\" value=\""+res.getC_id()+">");
                                    out.write("<button id=\"addCrawler\" type=\"submit\">");
                                    out.write("</button>");
                                    out.write("</div>");

                                  }
                                  
                                }

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
