package cn.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.GameSet;
import cn.utils.Utils;

@SuppressWarnings("serial")
public class GameSetServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		int xLength = Utils.StringToInt(request.getParameter("x"));
		int yLength = Utils.StringToInt(request.getParameter("y"));
		int mineNum = Utils.StringToInt(request.getParameter("mineNum"));
		/* 数据库修改 */
		GameSet gameSetN = new GameSet(xLength,yLength,mineNum);
		GameSet.charge(gameSetN);
		/* 页面跳转 */
		response.sendRedirect("IndexServlet");
	}

}
