package servlet;

import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solrUtil.SolrUtils;

import dao.usrDao;
import domain.usrinfo;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class serOperation01
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		//客户端数据输入
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+" "+password);
		usrDao dbu = new UsrDAOImpl();
		JSONObject obj00 = new JSONObject();
		SolrUtils util=new SolrUtils();
		try {				
			if(!dbu.Login(username, password)){
			obj00.put("state", "success");
			usrinfo usrinfo=dbu.findByName(username);
			System.out.println(util.recommend(usrinfo));
			
			}else{
				obj00.put("state", "failed");	
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
