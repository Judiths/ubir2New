package servlet;


import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.usrDao;
import domain.usrinfo;

/**
 * Servlet implementation class UserOperation11
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter in = response.getWriter();
		//int oldid = 12;
		// 客户端输入数据
		String newname = request.getParameter("newusername");
		String tel =request.getParameter("telephone");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");
		String pro = request.getParameter("major");
		String sch = request.getParameter("academy");
		String addr = request.getParameter("address");
		//String pw = request.getParameter("password");
		String pw="";
		String sign = request.getParameter("signature");
		usrinfo user = new usrinfo(newname, tel, email, grade, pro, sch, addr, pw, sign);
		String oldname = request.getParameter("username");		
/*		
		String newname = "Judith";
		String tel = "1532-xxxxx";
		String email = "test@gmail.com";
		String grade = "2年级";
		String pro = "计算机科学与技术";
		String sch = "计算机学院";
		String addr = "重庆大学A校区";
		String pw = "###";	
		String sign = "do your best";
		usrinfo user = new usrinfo(newname, tel, email, grade, pro, sch, addr, pw, sign);
		String oldname = "Judiths";*/
		usrDao dbu = new UsrDAOImpl();	
		JSONObject obj00 = new JSONObject();
		usrinfo olduser = null;
		try {
			// 只有已注册的用户可以进行个人信息的修改	
			olduser = dbu.findByName(oldname);		
			if(olduser != null) {
				if (!newname.equals(oldname)&&dbu.findByName(newname) != null) {
					obj00.put("state", "the username you modified has been used, please enter another one");
				} 
				else if(!dbu.update(user, oldname)) {
					obj00.put("state", "success");
				}
			}else{
				obj00.put("state", "username you entered is not exist.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj00.put("state","error");
			e.printStackTrace();
		}			
		
		in.write(obj00.toString());
		System.out.println(obj00.toString());
		in.flush();
		in.close();
	}
}

