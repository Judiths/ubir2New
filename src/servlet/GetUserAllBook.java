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
import domain.bookinfo;
import domain.sell;

/**
 * Servlet implementation class GetUserAllBook
 */
@WebServlet("/GetUserAllBook")
public class GetUserAllBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserAllBook() {
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
		//String name = "Robort";
		byte []bs=name.getBytes("ISO8859-1");
		name=new String(bs,"UTF-8");
		System.out.println(name+"**");
		JSONArray array=new JSONArray();
	    JSONObject json=new JSONObject();
	    PrintWriter in = response.getWriter();
		bookDao bdao=new BookDAOImpl();
		usrDao dbu = new UsrDAOImpl();
		sellDao sdao= new SellDAOImpl();
		long usrId;
		String state="fail";
		List<sell> sellList;
		JSONObject tmp=null;
		bookinfo book;
		try {
			usrId=dbu.findByName(name).getUsrID();
		    sellList=sdao.findAllByUserID(usrId);
		    int sz=sellList.size();
		    if(sz>0){
		    	state="success";
		    }
		    sell s=null;
		    for(int i=0;i<sz;i++){
		    	s=sellList.get(i);
	            tmp=new JSONObject();
	            book=bdao.findById(s.getBookID());
		    	tmp.put("bookname",book.getBookName());
		    	tmp.put("isbn", book.getBookISBN());
		    	tmp.put("author", book.getBookAuthor());
		    	tmp.put("publish", book.getBookPublish());
				tmp.put("sellNum",s.getSellNum());
				tmp.put("sellPrice", s.getSellPrice());
				tmp.put("desc", s.getSellDesc());
				//sell.setSellGraph((rs.getString("sellGraph")));
				array.put(tmp);
		    }
		    json.put("book",array);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("state",state);
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
