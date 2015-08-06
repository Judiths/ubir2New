package servlet;

import impl.SellDAOImpl;
import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.sellDao;
import dao.usrDao;
import domain.sell;
import domain.usrinfo;

/**
 * Servlet implementation class RecomOperation01
 */
@WebServlet("/Relate2Seller")
public class Relate2Seller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Relate2Seller() {
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
		
		//客户端输入bookid
		long bookid = 1;
		
		//String bidstr = request.getParameter("bookID");
		//String bidstr = Integer.toString((int) bookid);
				
		PrintWriter in = response.getWriter();
		
		sellDao dbs = new SellDAOImpl();
		usrDao dbu = new UsrDAOImpl();		
		JSONObject obj00 = new JSONObject();
		JSONArray jarray = new JSONArray();
		sell sell = null;
		usrinfo seller = null;
		try {
			JSONObject obj = new JSONObject();
			sell = dbs.findBybookID(bookid);	
			obj00.put("bookID", sell.getBookID());	
			seller = dbu.findByUID(sell.getUsrID());
			obj.put("name", seller.getUsrName());
			obj.put("Tel", seller.getUsrTel());
			obj.put("email", seller.getUsrEmail());
			obj.put("college", seller.getUsrSch());
			obj.put("grade", seller.getUsrGrade());
			obj.put("major", seller.getUsrPro());
			obj.put("addr", seller.getUsrAddr());
			obj.put("signature", seller.getUsrSign());
			jarray.put(obj);
			obj00.put("state", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj00.put("state", "error");
			e.printStackTrace();
		}
		
		in.write(jarray.toString()+obj00.toString());
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
