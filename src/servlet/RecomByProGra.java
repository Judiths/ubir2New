package servlet;

import impl.BookDAOImpl;
import impl.SellDAOImpl;
import impl.UsrDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bookDao;
import dao.sellDao;
import dao.usrDao;
import domain.bookinfo;
import domain.sell;
import domain.usrinfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RecomOperation
 * @description: we will have an intelligent recommendation
 * 						by using the username.  
 */
@WebServlet("/RecomByProGra")
public class RecomByProGra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecomByProGra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		 	
		// with usernam
		String username = request.getParameter("username");
//		String username="王元斗";
		byte []bs=username.getBytes("ISO8859-1");
		username=new String(bs,"UTF-8");
		System.out.println("recommand "+username);
		usrDao dbu = new UsrDAOImpl();
		usrinfo user;
		
		PrintWriter in = response.getWriter();			
		JSONArray jarray = new JSONArray();
		JSONObject obj00 = new JSONObject();
		try {
			user = dbu.findByName(username);
//			long userId = user.getUsrID();	// for comparing with the college, grade and major
			
			String college = user.getUsrSch();
			String grade = user.getUsrGrade();
			String major = user.getUsrPro();
		
			System.out.println(college+" "+grade+" "+major);
			
			sellDao dbs = new SellDAOImpl();
			// Find all bookIDs in sell table.
			List<Long> bids = new ArrayList<Long>(); 		
			bids = dbs.findByProGra(college, grade, major);	
			System.out.println(bids.toString());
			bookDao dbb = new BookDAOImpl();
			bookinfo book = null;
			if(!bids.isEmpty()) {
				for (int i = 0; i<bids.size(); i++) {
					JSONObject obj = new JSONObject();
					book = dbb.findById(bids.get(i));	
					obj.put("bookID", bids.get(i));
					obj.put("bookISBN", book.getBookISBN());
					obj.put("bookName", book.getBookName());
					obj.put("bookAuthor", book.getBookAuthor());	
					obj.put("bookPublish", book.getBookPublish());
					obj.put("bookPrice", book.getBookPrice());
					// return the numbers of the Recommanded_books
					obj.put("totalNum", dbs.sellSum(bids.get(i)));
					jarray.put(obj);
				}
				System.out.println();
					obj00.put("state", "success");
				} else {
					obj00.put("state", "fail");		
				}
		
			obj00.put("book", jarray);
			in.write(obj00.toString());
			in.flush();
			in.close();	
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			obj00.put("state", "error");
			e1.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	}

}
