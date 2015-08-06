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

import com.solrUtil.SolrUtils;

import net.sf.json.JSONObject;
import dao.bookDao;
import dao.sellDao;
import dao.usrDao;
import domain.bookinfo;
import domain.sell;

/**
 * Servlet implementation class ModifyBookInfor
 */
@WebServlet("/ModifyBookInfor")
public class ModifyBookInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyBookInfor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		JSONObject json=new JSONObject();
		PrintWriter in = response.getWriter();
		// 客户端输入数据
		String username=request.getParameter("username");
		String isbn = request.getParameter("isbn");
		String sellPrice=request.getParameter("sellPrice");
		String sellNum=request.getParameter("sellNum");
		String desc=request.getParameter("desc");
		
		System.out.println(username+"****");
		//String picture;
		/*String username="ding";
		String isbn="9787111107231";
		String sellPrice="100";
		String sellNum= "900";
		String desc="100";*/

		
		bookDao bdao=new BookDAOImpl();
		sellDao sdao= new SellDAOImpl();
		usrDao dbu = new UsrDAOImpl();
		SolrUtils util=new SolrUtils();
		String state="fail";
		try {
			bookinfo book=bdao.findByISBN(isbn);
			long usrId=dbu.findByName(username).getUsrID();
			if(book!=null) {
				long bookId=book.getBookID();
		        sell s=null;
		        List<sell>	slist=sdao.findAllByBookID(bookId);
		        for(int i=0;i<slist.size();i++){
		        	if(slist.get(i).getUsrID()==usrId){
		        		s=slist.get(i);
		        		break;
		        	}
		        }
		        if(s!=null) {
		        	s.setSellDesc(desc);
		        	s.setSellNum(Integer.valueOf(sellNum));
		        	s.setSellPrice(sellPrice);
		        	if(s.getSellNum()>0){
		        	    if(!sdao.updateSeller(s,String.valueOf(s.getSellID()))){
		        		state="success";
		        	     }
		        	}else{//delete
		        	    if(!sdao.delete(s.getSellID())){
		        	    	state="success";
		        	    	String query="bookID:"+s.getBookID();
		        	    	util.deleteByQuery(query);
		        	    }
		        	}
		        }
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("state",state);
		in.write(json.toString());
		System.out.println(json.toString());
		in.flush();
		in.close();
	}
}
