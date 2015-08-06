package servlet;

import impl.BookDAOImpl;
import impl.SellDAOImpl;
import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.bookDao;
import dao.sellDao;
import dao.usrDao;
import domain.sell;
import domain.usrinfo;

/**
 * Servlet implementation class GetUserInforByBookName
 */
@WebServlet("/GetUserInforByBookIsbn")
public class GetInforByBookIsbn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInforByBookIsbn() {
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
	     
		JSONArray array=new JSONArray();
	    JSONObject json=new JSONObject();
		// 客户端输入isbn
		String isbn = request.getParameter("isbn");	
	    PrintWriter in = response.getWriter();
		bookDao bdao=new BookDAOImpl();
		usrDao dbu = new UsrDAOImpl();
		sellDao sdao= new SellDAOImpl();
		String state="false";
		long bookId;
		List<sell> sellList;
		try {
			bookId=bdao.findByISBN(isbn).getBookID();
			sellList=sdao.findAllByBookID(bookId);
			usrinfo user;
			JSONObject tmp=null;
			sell s=null;
			int sz=sellList.size();
			if(sz>0){
				state="success";
			}
			for(int i=0;i<sz;i++){
            tmp=new JSONObject();
			s=sellList.get(i);
			user=dbu.findByUID(s.getUsrID());
			tmp.put("username",user.getUsrName());
			tmp.put("sellprice", s .getSellPrice());
			tmp.put("sellNum", s.getSellNum());
			tmp.put("desc", s.getSellDesc());
			array.put(tmp);
			}
			json.put("seller",array);
			json.put("state", state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.write(json.toString());
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
