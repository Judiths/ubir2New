package servlet;

import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import dao.usrDao;
import domain.usrinfo;

/**
 * Servlet implementation class HtmlServlet
 */
@WebServlet("/Link2find")
public class Link2find extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private String target = "/WEB-INF/forgetPW.jsp";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Link2find() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String new_psw = request.getParameter("new_psw");
		request.setAttribute("USER", username);	
		request.setAttribute("New_PSW", new_psw);	
		RequestDispatcher rd = request
				.getRequestDispatcher("/WEB-INF/forgetPW.jsp");		//在网页中 查看
		rd.forward(request, response);
		System.out.println("111");
		
		System.out.println(username + " " + new_psw);
		PrintWriter in =  response.getWriter();
		usrDao dbu = new UsrDAOImpl();
		JSONObject obj00 = new JSONObject();
		usrinfo olduser = null;
		try {
			olduser = dbu.findByName(username);
			if (olduser!=null&&!olduser.getUsrName().isEmpty()) {
				if (!dbu.updatePw(username, new_psw)) {
					obj00.put("state", "success");
				} else {
					obj00.put("state", "failed");
				}
			} else {
                    obj00.put("state", "failed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj00.put("state", "error");
			e.printStackTrace();
		}
		System.out.println(obj00.toString());
		in.write(obj00.toString());
		in.flush();
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doGet(request, response);	
	}
}
