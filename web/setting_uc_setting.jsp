<%@	page language = "java"
	import="java.sql.*, java.util.*, Fabre.Bean.*, java.net.URLEncoder, java.net.URLDecoder"
	contentType = "text/html; charset=UTF-8"
	pageEncoding = "UTF-8"
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

  </head>
  <body>

  <%

    String auth = (String) request.getAttribute("auth");
    if (auth == null) {
      auth = (String)session.getAttribute("auth");
    }
    
    if (!auth.equals("ok")) {
      try {

        response.sendRedirect("./login.html");

      }catch (Exception e) {
        e.printStackTrace();
      }
    }

    UserBean user = (UserBean) request.getAttribute("user");
    if(user==null){
      user = (UserBean) session.getAttribute("user");
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
      <div class="header-menunav">
        <span>
          Setting Page
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
            <nav>
                <div class="nav-header">내 크롤러 관리</div>
                <ul class="nav-list">
                    <li><a href="./setting_user.jsp">기본정보수정</a></li>
                    <li class="nav-thisPage">내 크롤러 관리</li>
                    <li><a href="./setting_crawler.jsp">크롤러 추가</a></li>
                </ul>
            </nav>
            <article id="first-registration">
                <div class="article-header"> </div>
                <div class="article-main">
                    <div class="crawlersetting_search">
                        
                            <div class="crawlersetting_search_result">
                                <!-- 여기에는 검색 결과들이 표시 -->
                                <!-- 네모박스가 하나씩 나오면서 추가하기 버튼과 자세히 보기 a 이미지 -->
                                <!-- h4 c_title / p c_url / button / img  -->
                                <h3>내 크롤러 목록</h3>
                                <%

                                String u_email = user.getU_email();

                                for (int i = 0; i<cList.size(); i++) {

                                  CrawlerBean crawler = (CrawlerBean) cList.get(i);

                                  String c_id = crawler.getC_id();
                                  String c_name = crawler.getC_name();
                                  String c_url = crawler.getC_url();

                                  out.write("<div class=\"myCrawler\">");

                                  out.write("<form name=\"updateUC_Setting\" action=\"UpdateUC_SettingServlet\" method=\"post\">");
                                  out.write("<button type=\"submit\" class=\"favorite\" name=\"u_favorite\" value=\"favorite\"></button>");
                                  out.write("<input type=\"hidden\" name=\"u_email\" value=\""+u_email+"\">");
                                  out.write("<input type=\"hidden\" name=\"c_id\" value=\""+c_id+"\">");
                                  out.write("</form>");

                                  out.write("<h4>"+c_name+"</h4>");
                                  out.write("<p class=\"myCrawler_url\">"+c_url+"</p>");
                                  out.write("<form name=\"deleteUC_Setting\" action=\"DeleteUC_SettingServlet\" method=\"post\">");
                                  out.write("<button class = \"deleteCrawler\" type=\"submit\"></button>");
                                  out.write("<input type=\"hidden\" name=\"u_email\" value=\""+u_email+"\">");
                                  out.write("<input type=\"hidden\" name=\"c_id\" value=\""+c_id+"\">");
                                  out.write("</form>");
                                  
                                  out.write("</div>");

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
        Fabre
      </p>
    </footer>


    
 </body>
</html>
