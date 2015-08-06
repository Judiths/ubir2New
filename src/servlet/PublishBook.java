package servlet;

import helper.GetImagePath;
import impl.BookDAOImpl;
import impl.SellDAOImpl;
import impl.UsrDAOImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.solrUtil.SolrUtils;

import dao.bookDao;
import dao.sellDao;
import dao.usrDao;
import domain.bookinfo;
import domain.sell;
import domain.usrinfo;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PublishBook
 */
@WebServlet("/PublishBook")
@MultipartConfig(location = "/home/ubuntu/Pictures/googleIcons/", maxFileSize = 8388608, fileSizeThreshold = 819200)
public class PublishBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServletFileUpload upload;
	private final long MAXSize = 4194304*2L;//4*2MB
	private String filedir=null;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PublishBook() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory for disk-based file items
		this.upload = new ServletFileUpload(factory);// Create a new file upload handler
		this.upload.setSizeMax(this.MAXSize);// Set overall request size constraint 4194304
//		filedir=config.getServletContext().getRealPath("images");
		filedir="/home/ubuntu/Pictures/images/";
		System.out.println("filedir= "+filedir);
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
		// Input from clients
		
		PrintWriter in = response.getWriter();
		String uname = request.getParameter("username");
	   System.out.println(uname+"***");
		// book
		String isbn = request.getParameter("isbn");
		String bname = request.getParameter("bookName");
		String bauthor = request.getParameter("bookAuthor");
		String originalPrice = request.getParameter("bookPrice");
		String bpulish = request.getParameter("bookPublish");
		
		System.out.println(bname);
		/* addExtra infomation */
		int num = Integer.valueOf(request.getParameter("sellNum"));
		String price = request.getParameter("sellPrice");
		String image = request.getParameter("sellGraph");
		System.out.println(image);
		String bookSch = request.getParameter("bookSch");
		String bookGrade = request.getParameter("bookGrade");
		String bookPro = request.getParameter("bookPro");
		String desc = request.getParameter("desc");
		
		String state="false";
		//GetImagePath imagePath = new GetImagePath();
		JSONObject obj00 = new JSONObject();
		bookDao dbb = new BookDAOImpl();
		usrDao dbu = new UsrDAOImpl();
		sellDao dbs = new SellDAOImpl();
		SolrUtils util=new SolrUtils();
		try {
			usrinfo user = dbu.findByName(uname);
			bookinfo book = dbb.findByISBN(isbn);
			// 
			//List<FileItem> items = this.upload.parseRequest(request);
			//String image = imagePath.getImage(filedir, items);
			
			// bookISBN doesn't exist in database
			if (dbb.findByISBN(isbn) == null) {			
				bookinfo newbook = new bookinfo(isbn,bname,originalPrice,bauthor,bpulish);	// insert into database
				dbb.insert(newbook);
				
				bookinfo book00 = dbb.findByISBN(isbn);
				util.addDoc(book00);
				sell sell00 = new sell(user.getUsrID(), book00.getBookID(), num, price, image, desc, bookSch, 
							bookGrade, bookPro);

					Boolean stf = dbs.addExtra(sell00);		// 	insert extraInfo for the sell and save the status of result
					//dbs.addExtra(sell);
					if (!stf) {
						state="success";
						sell sell01=dbs.findBybookID(book00.getBookID());
						util.addDocToSell(sell01);
					}
				} else {
					sell sell=null;
/*					List<sell> slist=dbs.findAllByBookID(book.getBookID());
					for(int i=0;i<slist.size();i++){
						if(slist.get(i).getUsrID()==user.getUsrID()){
							sell=slist.get(i);
							sell.setSellNum(sell.getSellNum()+num);
						}
					}*/
					sell = new sell(user.getUsrID(), book.getBookID(), num, price, image, desc,bookSch,bookGrade, bookPro);
					Boolean stf = dbs.addExtra(sell);		// 	insert extraInfo for the sell and save the status of result
					//dbs.addExtra(sell);
					
					if (!stf) {
						state="success";
						sell sell01=dbs.findBybookID(book.getBookID());
						util.addDocToSell(sell01);
					}
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			state="error";
			e.printStackTrace();
		}
		obj00.put("state", state);	
		in.write(obj00.toString());
		System.out.println(obj00.toString());
		in.flush();
		in.close();
	}
}
