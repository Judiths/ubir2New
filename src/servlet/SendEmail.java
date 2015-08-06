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
import forgetPwHelper.EmailUtils;

/**
 * Servlet implementation class SendEmail
 */
@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmail() {
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
		
		PrintWriter out = response.getWriter();
		//客户端获取用户名和邮箱
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		System.out.println(username+" "+email);
		JSONObject obj00 = new JSONObject();
		usrDao dbu = new UsrDAOImpl();
    	usrinfo user = null;
		try {
			user = dbu.findByName(username);
			if (user != null) {
				System.out.println(user.getUsrEmail());
				if (user.getUsrEmail().equals(email)) {
					EmailUtils.sendResetPasswordEmail(user);
					obj00.put("state", "success");
					System.out.println("haha");
				} else { obj00.put("state", "check your email again!");}
				
			} else if (user == null){
				obj00.put("state", "check your username");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}
		out.write(obj00.toString());
		System.out.println(obj00.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
