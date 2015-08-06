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
 * Servlet implementation class UserOperation100
 */
@WebServlet("/ModifyPw")
public class ModifyPw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyPw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		PrintWriter in = response.getWriter();

		String name = request.getParameter("username");
		String oldpw = request.getParameter("old_psw");
		String newpw = request.getParameter("new_psw");
		/*
		 * String name = "999"; String newpw = "&&&";
		 */
		System.out.println(name + " " + oldpw + " " + newpw);
		usrDao dbu = new UsrDAOImpl();
		JSONObject obj00 = new JSONObject();
		usrinfo olduser = null;
		try {
			olduser = dbu.findByName(name);
			if (olduser.getUsrPw().equals(oldpw)) {
				if (!dbu.updatePw(name, newpw)) {
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
		in.write(obj00.toString());
		in.flush();
		in.close();
	}

}
