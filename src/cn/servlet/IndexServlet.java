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
		//��ȡ��Ϸ�������ݿ�
		GameSet gameSet = GameSet.show();
		int xLength = gameSet.getxLength();
		int yLength = gameSet.getyLength();
		int mineNum = gameSet.getMineNum();
		AllBlock allBlock = new AllBlock(xLength,yLength,mineNum);
		//����Index.jsp�����ļ�
		//����
		int flagRightNum = 0;
		//��ʱ
		//int time = 0;
		//�õ�HttpApplication����
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
