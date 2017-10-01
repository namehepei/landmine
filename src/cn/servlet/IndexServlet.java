package cn.servlet;

import java.io.IOException;
import javax.servlet.http.*;

import cn.entity.*;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//读取游戏设置数据库
		GameSet gameSet = GameSet.show();
		int xLength = gameSet.getxLength();
		int yLength = gameSet.getyLength();
		int mineNum = gameSet.getMineNum();
		AllBlock allBlock = new AllBlock(xLength,yLength,mineNum);
		//设置Index.jsp控制文件
		//雷数
		int flagRightNum = 0;
		//用时
		//int time = 0;
		//得到HttpApplication对象
		HttpSession session = request.getSession();
		session.setAttribute("xLength",xLength);
		session.setAttribute("yLength",yLength);
		session.setAttribute("mineNum", mineNum);
		session.setAttribute("allBlock",allBlock);
		session.setAttribute("flagRightNum",flagRightNum);
		//
		allBlock.layMines();
		//
		response.sendRedirect("index.jsp");
	}
}
