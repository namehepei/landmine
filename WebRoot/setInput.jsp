<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'setInput.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="setInput.css">
	

  </head>
  
  <body>
     <div>
    	<form name="difficult" method="post" action="GameSetServlet">
    		<table title="难度">
    			<tr>
    				<td>x:</td>
    				<td><input type="text" name="x"/></td>
    			</tr>
    			<tr>
    				<td>y:</td>
    				<td><input type="text" name="y" /></td>
    			</tr>
    			<tr>
    				<td>mines-num:</td>
    				<td><input type="text" name="mineNum" /></td>
    			</tr>
    			<tr>
    				<td><input type="submit" name="submit" /></td>
    			</tr> 		
    			
    		</table>
    	</form>
    </div>
  </body>
</html>
