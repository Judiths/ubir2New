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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.bookDao;
import dao.sellDao;
import dao.usrDao;
import domain.bookinfo;
import domain.sell;
import domain.usrinfo;

/**
 * Servlet implementation class UserOperation
 */
@WebServlet("/UserOperation")
public class UserOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//PrintWriter in = response.getWriter();
		String type = request.getParameter("type");
		try {
			switch (type) {
			case "getInfo":
				// path = ~//UserOperation?type=getInfo&&username=
				getInfo(request, response);
				break;
			case "recommd":
				// path = ~/UserOperation?type=recommand&&college= && grade= && major=
				doRecom(request, response);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doRecom(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String college = request.getParameter("college");
		String grade = request.getParameter("grade");
		String major = request.getParameter("major");
		
		JSONArray jarray = new JSONArray();
		JSONObject obj00 = new JSONObject();
		try {
			sellDao dbs = new SellDAOImpl();
			// Find all bookIDs in sell table.
			List<Long> bids = new ArrayList<Long>(); 		
			bids = dbs.findByProGra(college, grade, major);	
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
				obj00.put("state", "success");
			} else {
				obj00.put("state", "fail");		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj00.put("state", "error");
			e.printStackTrace();
		}
		obj00.put("book", jarray);
		// in.write(obj00.toString());
		// response.sendRedirect("UserOperation?type=HostPageYouSet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//PrintWriter in = response.getWriter();
		String type = request.getParameter("type");
		try {
			switch(type) {
			case "register":
				// url = 202.202.5.134/UserOperation?type=register
				doRegister(request, response);
				break;
			case "login":
				// url=~/UserOperation?type=login
				doLogin(request, response);
				break;
			/*case "getInfo":
				// url=~/UserOperation?type=getInfo&&username="
				getInfo(request, response);
				break;*/
			case "modify":
				doModify(request, response);
				break;
			case "modifyPw":
				doModifyPw(request, response);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

	private void doModifyPw(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String newpw = request.getParameter("newPassword");
		usrDao dbu = new UsrDAOImpl();	
		JSONObject obj00 = new JSONObject();
		usrinfo olduser = null;			
		try {
			olduser = dbu.findByName(name);			
			if(olduser != null) {
				if (!dbu.updatePw(name, newpw)) {
					obj00.put("state", "success");
				} else {
					obj00.put("state", "failed");
				}
			}else{
				obj00.put("state", "your username is wrong.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj00.put("state","error");
			e.printStackTrace();
		}			
		response.sendRedirect("UserOperation?type=HostPageYouSet");
	}

	private void doModify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String newname = request.getParameter("newusername");
		String tel =request.getParameter("telephone");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");
		String pro = request.getParameter("major");
		String sch = request.getParameter("academy");
		String addr = request.getParameter("address");
		String pw = request.getParameter("password");
		//String pw="";
		String sign = request.getParameter("signature");
		usrinfo user = new usrinfo(newname, tel, email, grade, pro, sch, addr, pw, sign);
		String oldname = request.getParameter("username");		
		usrDao dbu = new UsrDAOImpl();	
		JSONObject obj00 = new JSONObject();
		usrinfo olduser = null;
		try {
			// 只有已注册的用户可以进行个人信息的修改	
			olduser = dbu.findByName(oldname);		
			if(olduser != null) {
				if (!newname.equals(oldname)&&dbu.findByName(newname) != null) {
					obj00.put("state", "the username you modified has been used, please enter another one");
				} 
				else if(!dbu.update(user, oldname)) {
					obj00.put("state", "success");
				}
			}else{
				obj00.put("state", "username you entered is not exist.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			obj00.put("state","error");
			e.printStackTrace();
		}			
		response.sendRedirect("UserOperation?type=HostPageYouSet");
	}

	private void getInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");	
		//String name = "薛定谔的猫";
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
		// in.write(obj.toString());
		response.sendRedirect("UserOperation?type=HostPageYouSet");
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//System.out.println(username+" "+password);
		usrDao dbu = new UsrDAOImpl();
		JSONObject obj00 = new JSONObject();
		try {				
			if(!dbu.Login(username, password)){
			obj00.put("state", "success");
			}else{
				obj00.put("state", "failed");	
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		//in.write(obj00.toString());
		response.sendRedirect("UserOperation?type=HostPageYouSet");
	}

	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generrated method stub
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
		//in.write(obj00.toString());
		response.sendRedirect("UserOperation?type=HostPageYouset");
	}	
}
