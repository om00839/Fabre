<%@ page language = "java"
	 errorPage="errorpage.jsp" 
     import="java.sql.*, java.util.*, beans.shopping.*"
     contentType = "text/html; charset=UTF-8"
     pageEncoding = "UTF-8"
 %>

<jsp:useBean id="cart" scope="session" class="beans.shopping.ShoppingCart" />

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
       request.getRequestDispatcher("/login.html").forward(request, response);
          
    } catch (Exception e) {
       e.printStackTrace();
    }
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
          <li><a href="#">Main</a></li>
          <li><a href="#">Setting</a></li>
          <li><a href="#">Developers</a></li>
          <li><a href="#">Logout</a></li>
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
            ArrayList list = (ArrayList)request.getAttribute("crawler");
            if (list == null) list = (ArrayList)session.getAttribute("crawler");
            for (int i = 0; i < list.size(); ++i) {
               CrawlertBean cb = (CrawlerBean)list.get(i);

               out.write("<li>");
               out.write("<li> <a href=\"" + cb.c_url + "#\">" + cb.c_title +"</a></li>")




               out.write( "<tr>");
               out.write( "<form action=\"AddToShoppingCart.jsp\" method = \"post\" >");
               out.write( "<td>" + pb.getName() + "</td>");
               out.write( "<td>" + pb.getDescription() + "</td>");
               out.write( "<td>" + pb.getPrice() + "</td>");
               out.write( "<td> <img src=\"" + pb.getImageUrl() + "\"></td>");
               
               out.write( "<input type=\"hidden\" name=\"id\" value=\"" + pb.getId() + "\">");
               out.write( "<input type=\"hidden\" name=\"desc\" value=\"" + pb.getDescription() + "\">");
               out.write( "<input type=\"hidden\" name=\"price\" value=\"" + pb.getPrice() + "\">");
                
               out.write( "<td width=\"1%\" bgcolor=\"#DEB678\">" +    
                      "<INPUT TYPE=\"submit\" VALUE=\"Add To Shopping Cart\"> </td>" );
                      
                          
               out.write( "</form>");
               
               out.write( "<form action=\"UpdateProductServlet\" method = \"post\" >");
                if (pb.getRate().equals("like")) {
                   out.write( "<td>" +  "<select name=\"rates\"><option value=\"like\" selected>like</option><option value=\"dislike\">dislike</option></select>" + "</td>");
               } else {
                out.write( "<td>" +  "<select name=\"rates\"><option value=\"like\">like</option><option value=\"dislike\" selected>dislike</option></select>" + "</td>");
               }
               out.write( "<td width=\"1%\" bgcolor=\"#DEB678\">" +
                    "<INPUT TYPE=\"submit\" VALUE=\"Rate Product\"> </td>");        
                  
               out.write( "<input type=\"hidden\" name=\"id\" value=\"" + pb.getId() + "\">");
               out.write( "<input type=\"hidden\" name=\"desc\" value=\"" + pb.getDescription() + "\">");
               out.write( "<input type=\"hidden\" name=\"price\" value=\"" + pb.getPrice() + "\">");
                
               out.write( "</form> </tr>");
            }
            
            session.setAttribute("products",list);
            
            session.setAttribute("auth","ok");
          %>
  
          <!-- servlet or php로 내 웹페이지들 불러오기 -->
          <li><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>            
        </ul>

      </nav>


      <article>

        <!-- servlet or php로 내 웹페이지 불러오기 -->

        <div class="article-header"></div>


        <div class="article-notice">
          <h4>
            <img src="./images/megaphone.svg" alt="megaphone" style="width:35px;">
            새로운 공지가 올라왔습니다. (or 새로운 공지가 없습니다.) (가장 최근 공지 시간)
          </h4>
          <p>공지 갯수 몇개 이렇게 알려준다.</p>
        </div>

        <div class="article-content">
          <h4>title</h4>
          <p>date : 0000-00-00</p>
          <div class="article-content-urlbox">
            <a href="#" target="_blank">http://yonsei.ac.kr</a>
          </div>
          <p>cralwer : 크롤러 이름</p>
        </div>

        <div class="article-content">
          <h4>신촌(국제) 캠퍼스 2017 여름 계절제 수업 안내</h4>
          <p>date : 2017-03-29</p>
          <div class="article-content-urlbox">
            <a href="#" target="_blank">http://yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=151493&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15</a>
          </div>
          <p>cralwer : www.yonsei.ac.kr </p>
        </div>

      </article>
    </div>


// 여기서부터 addtoshoppingcart 내용

  
    <h3>Search Products</h3>
  <%
  
    String auth = (String) request.getAttribute("auth");
    if (auth == null) {
      auth = (String)session.getAttribute("auth");
    }
    
    if (!auth.equals("ok")) {
    	try {
			 request.getRequestDispatcher("/login.html").forward(request, response);
					
		} catch (Exception e) {
			 e.printStackTrace();
		}
    }
    
  %>
  
  <%
  
     auth = (String)session.getAttribute("auth");
  	 //if (auth != null) {
    	out.write( "<table>");
    	out.write( "<tr>");
    	out.write( "<td>");
   	 	out.write( "<form action=\"SearchServlet\" method=\"get\"> ");
   	 	out.write( "</td>");
    	out.write( "<input type=\"text\" name=\"query\" size=\"80\" >"); 
    	out.write( "<td><input type=\"submit\" name=\"Submit\" value=\"Search\"></td>");  
    	out.write( "</form> </tr> </table>");
    	
    	out.write( "<table>");
    	out.write( "<tr>");
    	out.write( "<td>");
   	 	out.write( "<form action=\"NaverSearchServlet\" method=\"post\" accept-charset=\"UTF-8\"> ");
   	 	out.write( "</td>");
    	out.write( "<input type=\"text\" name=\"query\" size=\"80\" >"); 
    	out.write( "<td><input type=\"submit\" name=\"Submit\" value=\"NaverSearch\"></td>");  
    	out.write( "</form> </tr> </table>");
    	
    	
  		request.setCharacterEncoding("UTF-8");
    	
     //}
  %>
  
    <%
        String user_id = (String)request.getAttribute("userId");
        if (user_id == null) {
            user_id = (String)session.getAttribute("userId");
        }
        
        if (user_id != null && user_id.equals("om00839")){
        
	        out.write( "<table>");
	    	out.write( "<tr>");
	        out.write( "<form action=\"UserServlet\" method=\"get\"> ");
	        out.write( "<input type=\"hidden\" name=\"userId\" value=\"" + user_id + "\">");
	    	out.write( "<td><input type=\"submit\" name=\"Submit\" value=\"Get All Users\"></td>");  
	   		out.write( "</form> </tr> </table>");
        	
        }
        
        
    
        session.setAttribute("userId",user_id);
  %>
  
  
  <%
    String id = request.getParameter("id");
    String update = (String)request.getAttribute("update");
    if ( id != null && update == null ) {
    
      String desc = request.getParameter("desc");
      Float price = new Float(request.getParameter("price"));
      
      cart.addItem(id, desc, price.floatValue(), 1);
    }
           
  %>
    
  <p>
  
  <a href="ShoppingCart.jsp">Shopping Cart Quantity:</a>
    <%=cart.getNumOfItems() %>
  <hr>
  
  <center><h3>DVD Catalog</h3></center>
  <table border="1" width="300" cellspacing="0" cellpadding="2" align="center">
    <tr><th>Name</th><th>Description</th><th>Price</th><th>Image</th><th>Rate</th></tr>
   <%
        ArrayList list = (ArrayList)request.getAttribute("products");
        if (list == null) list = (ArrayList)session.getAttribute("products");
        for (int i = 0; i < list.size(); ++i) {
           ProductBean pb = (ProductBean)list.get(i);
           out.write( "<tr>");
           out.write( "<form action=\"AddToShoppingCart.jsp\" method = \"post\" >");
           out.write( "<td>" + pb.getName() + "</td>");
           out.write( "<td>" + pb.getDescription() + "</td>");
           out.write( "<td>" + pb.getPrice() + "</td>");
           out.write( "<td> <img src=\"" + pb.getImageUrl() + "\"></td>");
           
           out.write( "<input type=\"hidden\" name=\"id\" value=\"" + pb.getId() + "\">");
           out.write( "<input type=\"hidden\" name=\"desc\" value=\"" + pb.getDescription() + "\">");
           out.write( "<input type=\"hidden\" name=\"price\" value=\"" + pb.getPrice() + "\">");
            
           out.write( "<td width=\"1%\" bgcolor=\"#DEB678\">" +    
                  "<INPUT TYPE=\"submit\" VALUE=\"Add To Shopping Cart\"> </td>" );
                  
                      
           out.write( "</form>");
           
           out.write( "<form action=\"UpdateProductServlet\" method = \"post\" >");
            if (pb.getRate().equals("like")) {
               out.write( "<td>" +  "<select name=\"rates\"><option value=\"like\" selected>like</option><option value=\"dislike\">dislike</option></select>" + "</td>");
           } else {
            out.write( "<td>" +  "<select name=\"rates\"><option value=\"like\">like</option><option value=\"dislike\" selected>dislike</option></select>" + "</td>");
           }
           out.write( "<td width=\"1%\" bgcolor=\"#DEB678\">" +
                "<INPUT TYPE=\"submit\" VALUE=\"Rate Product\"> </td>");        
              
           out.write( "<input type=\"hidden\" name=\"id\" value=\"" + pb.getId() + "\">");
           out.write( "<input type=\"hidden\" name=\"desc\" value=\"" + pb.getDescription() + "\">");
           out.write( "<input type=\"hidden\" name=\"price\" value=\"" + pb.getPrice() + "\">");
            
           out.write( "</form> </tr>");
        }
        
        session.setAttribute("products",list);
        
        session.setAttribute("auth","ok");
    %>
  
 </table>
 
 
<form action="LogoutServlet" method="get">
    <table>
         <tr><td>  <input type="submit" name="logout" value="Logout">  </td></tr>
    </table>
</form>  
 
    
    
 </body>
</html>
