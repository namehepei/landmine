<%@page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="java.sql.*" %>
<%@page import="cn.display.*,cn.utils.*,cn.entity.*,cn.dao.*" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>雷区</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">

  </head>
  
  <body>
  	<div class="mines">
  	<form method="post" action="AllBlockServlet">
  		<c:forEach var="i" begin="1" end="${sessionScope.yLength }">
  			<div>
  			<c:forEach var="j" begin="1" end="${sessionScope.xLength }">
  				<c:set var="indexIn" value="${(j - 1) * sessionScope.xLength + i }" scope="page" />
  				<c:set var="index" value="${j * (sessionScope.xLength + 2) + i }" scope="page" />
  				<c:set var="block" value="${sessionScope.allBlock.blocks[index]}" scope="page"/>
  				<c:if test="${block.show == 1 }">
  					<button class="clickUnAble" disabled="disabled">${block.num }</button>
  				</c:if>
  				<c:if test="${block.show == -1 }">
  					<button class="clickRight" disabled="disabled"></button>
  				</c:if>
  				<c:if test="${block.show == 0 }">
  					<button type="submit" class="clickAble" name="block" value="${indexIn }"></button>
  				</c:if>	
  			</c:forEach>
  			</div>
  			<br>
  		</c:forEach>
  	</form>		
  	</div>
  	<c:set var="resultX" value="${sessionScope.allBlock.result}" scope="page" />
  	<c:if test="${resultX == -1 }">
  		<script type="text/javascript">alert("gameover");</script>
  	</c:if>
   	<c:if test="${resultX == 1 }">
  		<script type="text/javascript">alert("good");</script> 	 
  	</c:if>
  </body>
</html>
