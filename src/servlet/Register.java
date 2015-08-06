package servlet;

import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.usrDao;
import domain.usrinfo;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserOperation00
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * Default constructor. 
     */
    public Register() {
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
		String name = request.getParameter("username");
		String tel =request.getParameter("telephone");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");
		String pro = request.getParameter("major");
		String sch = request.getParameter("academy");
		String addr = request.getParameter("address");
		String pw = request.getParameter("password");
		String sign  = request.getParameter("signature");
		usrinfo currentuser = new usrinfo(name, tel, email, grade,
				pro, sch, addr, pw, sign);	
		JSONObject obj00 = new JSONObject();			
		
		usrDao dbu = new UsrDAOImpl();
		usrinfo olduser = null;			
		try {
			olduser = dbu.findByName(name);			
			if(olduser != null) {
				obj00.put("state", "existed");
			}else{
				if(!dbu.insert(currentuser)) {
					obj00.put("state", "success");
				}else{
					obj00.put("state", "fail");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.write(obj00.toString());
		in.flush();
		in.close();	
	}		
}

