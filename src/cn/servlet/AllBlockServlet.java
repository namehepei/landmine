package cn.servlet;

import java.io.IOException;

import javax.servlet.http.*;

import cn.entity.AllBlock;
import cn.utils.Utils;

@SuppressWarnings("serial")
public class AllBlockServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		int indexLength = Utils.StringToInt(request.getParameter("block"));
		HttpSession session = request.getSession();//可能会读取到失效后新设置的session
		AllBlock allBlock = (AllBlock)session.getAttribute("allBlock");
		allBlock.click(indexLength);
		allBlock.result();
		
		response.sendRedirect("AllBlock.jsp");
	}

}
