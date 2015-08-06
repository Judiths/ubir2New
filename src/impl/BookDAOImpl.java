package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import advance.JDBCTemplate;
import advance.Query;
import advance.Transaction;
import dao.bookDao;
import domain.bookinfo;

public class BookDAOImpl implements bookDao {

	@Override
	public Boolean insert(bookinfo book) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<Boolean> t = new Transaction<Boolean> () {

			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				PreparedStatement ps=conn.prepareStatement("insert into bookinfo(bookISBN, bookName,"
						+ "bookPrice, bookAuthor, bookPublish) values(?,?,?,?,?)");
				//  bookinfo
				//ps.setLong(1, usr.getUsrID());
				ps.setString(1, book.getBookISBN());
				ps.setString(2, book.getBookName());
				ps.setString(3, book.getBookPrice());
				ps.setString(4, book.getBookAuthor());
				ps.setString(5, book.getBookPublish());

				return ps.execute();
			}			 
		 };
		return t.execute(); 		 
	}

	@Override
	public bookinfo findById(long id) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<bookinfo> q = new Query<bookinfo> () {

			@Override
			protected bookinfo doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from bookinfo where bookID=? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
				ps.execute();
				bookinfo book = null;
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					book = new bookinfo();
					//book.setBookID(rs.getLong("bookID"));
					book.setBookISBN(rs.getString("bookISBN"));
					book.setBookName(rs.getString("bookName"));
					book.setBookPrice(rs.getString("bookPrice"));
					book.setBookAuthor(rs.getString("bookAuthor"));
					book.setBookPublish(rs.getString("bookPublish"));
				}
				return book;
			}
			
		};
		return q.execute();
	}

	@Override
	public List<bookinfo> findAll() throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<List<bookinfo>> q = new Query<List<bookinfo>> () {

			@Override
			protected List<bookinfo> doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				List<bookinfo> books = new ArrayList<bookinfo>();
				String sql = "select * from usrinfo";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					bookinfo book = new bookinfo();
					//book.setBookID(rs.getLong("bookID"));
					book.setBookISBN(rs.getString("bookISBN"));
					book.setBookName(rs.getString("bookName"));
					book.setBookPrice(rs.getString("bookPrice"));
					book.setBookAuthor(rs.getString("bookAuthor"));
					book.setBookPublish(rs.getString("bookPublish"));
					books.add(book);
				}
				return books;
			}
			
		};
		return q.execute();
	}
	
	@Override
	public Boolean update(bookinfo book, long oldid) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<Boolean> q = new Query<Boolean> () {
			
			@Override
			protected Boolean doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				
				String sql = "update bookinfo set bookISBN=?, bookName=?, bookPrice=?,"
						+ "bookAuthor=?, bookPublish=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, book.getBookISBN());
				ps.setString(2, book.getBookName());
				ps.setString(3, book.getBookPrice());
				ps.setString(4, book.getBookAuthor());
				ps.setString(5, book.getBookPublish());
				return ps.execute();
			}
		};
		return q.execute();
	}

	@Override
	public Boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<Boolean> t=new Transaction<Boolean> () {

			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				PreparedStatement ps = conn.prepareStatement("delete from bookinfo where id=?");
				ps.setInt(1, id);
				return ps.execute();
			}
			 
		 };
		 return t.execute();	
	}

	@Override
	public bookinfo findByISBN(String isbn) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<bookinfo> q = new Query<bookinfo> () {
			@Override
			protected bookinfo doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from bookinfo where bookISBN=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, isbn);
				ps.execute();
				bookinfo book = null; 
				ResultSet rs = ps.getResultSet();
				if(rs.next()) {
					book = new bookinfo();
					book.setBookID(rs.getLong("bookID"));
					book.setBookISBN(rs.getString("bookISBN"));
					book.setBookName(rs.getString("bookName"));
					book.setBookPrice(rs.getString("bookPrice"));
					book.setBookAuthor(rs.getString("bookAuthor"));
					book.setBookPublish(rs.getString("bookPublish"));
					//books.add(book);
				}
				return book;
			}
		};
		return q.execute();
	}
}
