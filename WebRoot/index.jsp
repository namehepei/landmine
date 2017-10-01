<%@page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@page import="cn.display.*,cn.utils.*,cn.entity.*,cn.dao.*" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>index.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	
  </head>
  
  <body>
  <div>
  	<div id="restart">
		<button onclick="JavaScript:window.open('IndexServlet')">restart</button>
  	</div>
  	<div id="num">
  		<button disabled="disabled">${sessionScope.flagRightNum}</button>
  	</div>
  	<div id="set">
  		<form method="post" action="setInput.jsp">
  			<button type="submit">menu</button>
  		</form>
  		
  	</div>
  </div>
  <div>
  	<iframe src="AllBlock.jsp"></iframe>
  	
  </div>
  </body>
</html>
