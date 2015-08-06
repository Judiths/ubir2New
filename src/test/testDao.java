package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import dao.bookDao;
import dao.sellDao;
import dao.usrDao;
import domain.bookinfo;
import domain.sell;
import domain.usrinfo;
import impl.BookDAOImpl;
import impl.SellDAOImpl;
import impl.UsrDAOImpl;

public class testDao {
	public static void main(String[] args) throws FileNotFoundException {
		String filepath = "/home/ubuntu/Pictures/googleIcons/GP010.png";
		@SuppressWarnings("resource")
		InputStream inputStream = new FileInputStream(new File(filepath ));
		
		usrinfo test00 = new usrinfo("qqq", "1232121212", "judiths10@foxmail.com", 
				"2", "Computer", "CQU Computer", "CQU", "&&&","heheh");

		usrDao dbu = new UsrDAOImpl();
		bookDao dbb = new BookDAOImpl();
		sellDao dbs = new SellDAOImpl();
			
	/*	try {
			usrinfo user = dbu.findByName(name);
			bookinfo book = dbb.findByISBN(isbn);
			if (book == null) {
				book = new bookinfo(isbn, bname,bauthor,originalPrice,bpulish);
				dbb.insert(book);
				
				sell sell = new sell(user.getUsrID(), book.getBookID(), num, price, Image, bookSch, 
				bookGrade, bookPro, desc);
				dbs.addExtra(sell);
				//System.out.println(book+"\n"+dbs.addExtra(sell));
			} else {
				sell sell = new sell(user.getUsrID(), book.getBookID(), num, price, Image, bookSch, 
						bookGrade, bookPro, desc);
				dbs.addExtra(sell);
				//System.out.println("\n"+dbs.addExtra(sell));
			}
			sell sell = new sell(user.getUsrID(), book.getBookID(), num, price, Image, bookSch, 
					bookGrade, bookPro, desc);
			dbs.addExtra(sell);
			System.out.println(book+"\n");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.print("haha");
			e1.printStackTrace();
		}
*/
		//MyBlob blobImage = new MyBlob(inputStream);
		
		
		try {	
		//	dbu.insert(test00);
		//	dbu.update(test00,"ggg");
			List<usrinfo> usrs = dbu.findAll();
			String college="计算机学院";
			String grade="大三下";
			String major="计算机科学与技术";
			List<Long> bookIds = dbs.findByProGra(college, grade, major);
			for (int i =0; i<bookIds.size(); i++) {
				System.out.println(bookIds.get(i));
				bookinfo book = dbb.findById(bookIds.get(i));
				System.out.println(book.getBookName());
			}
			
			// insert into sell and return false
			
			//System.out.println(dbs.sellSum(1));
		//System.out.println("success\n"+usrs.size()+"\n"+dbs.findByProGra(college, grade, major).size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print(0);
			e.printStackTrace();
		}
		
/*		long id = 0000000037;
		
		try {
			sell sell =  dbs.findBybookID(id);
			System.out.print(sell.getBookGrade());
			System.out.print(sell.getSellID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		int usrid = 35;
		
		try {
/*			usrinfo user = dbu.findByUID(usrid);
			System.out.println(user.getUsrAddr());*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
