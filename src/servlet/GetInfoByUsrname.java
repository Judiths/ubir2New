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
 * Servlet implementation class UserOperation10
 */
@WebServlet("/GetInfoByUsrname")
public class GetInfoByUsrname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoByUsrname() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 客户端输入用户名
		String name = request.getParameter("username");	
		byte []bs=name.getBytes("ISO8859-1");
		name=new String(bs,"UTF-8");
		//String name = "薛定谔的猫";
        System.out.println(name+"*");
		PrintWriter in = response.getWriter();
		usrDao dbu = new UsrDAOImpl();

		JSONObject obj = new JSONObject();
		usrinfo olduser = null;			
		try {
			olduser = dbu.findByName(name);			
			if(olduser != null) {
				usrinfo user = dbu.findByName(name);
				if (user.getUsrName() != name){
					obj.put("state", "success");		
			    }else{
				 obj.put("state", "the username you entered is not exist");
		     	}			
				obj.put("username", user.getUsrName() );
				obj.put("telephone", user.getUsrTel());
				obj.put("email", user.getUsrEmail());
				obj.put("grade", user.getUsrGrade());
				obj.put("major", user.getUsrPro());
				obj.put("academy", user.getUsrSch());
				obj.put("address", user.getUsrAddr());
				obj.put("password", user.getUsrPw());		
				obj.put("signature",user.getUsrSign());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj.put("state", "error");
			e.printStackTrace();
		}
		in.write(obj.toString());
		in.flush();
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
