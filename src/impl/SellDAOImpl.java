package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import advance.JDBCTemplate;
import advance.Query;
import advance.Transaction;
import dao.sellDao;
import domain.sell;

/**
 * @author ubuntu
 * @date 2015-3-10
 * @descriptions The user can upload some extra and factual information to
 * have a good description for his Used  books
 */
public class SellDAOImpl implements sellDao {
	// the "addExtra" operation
	public Boolean addExtra(final sell sell) throws Exception {
		Transaction<Boolean> t = new Transaction<Boolean>() {

			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				
				String sql = "insert into sell(usrID, bookID,sellNum, sellPrice, sellGraph, sellDesc,"
						+ " bookPro, bookGrade,bookSch) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(sql);
				//ps.setInt(1,seller.getSellID());
				ps.setInt(1,sell.getUsrID());
				ps.setLong(2,sell.getBookID());
				ps.setInt(3,sell.getSellNum());
				ps.setString(4,sell.getSellPrice());
				// path of image
				//ps.setBlob(5,sell.getSellGraph().getBinaryStream());
				ps.setString(5, sell.getSellGraph());
				ps.setString(6,sell.getSellDesc());
				ps.setString(7, sell.getBookPro());
				ps.setString(8, sell.getBookGrade());
				ps.setString(9, sell.getBookSch());		
				return ps.execute();
			}
			
		};
		return t.execute();
		
	}
	
	public Boolean updateSeller(sell sell, String oldId) throws Exception {
		 JDBCTemplate<Boolean> t = new Transaction<Boolean> () {

			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				PreparedStatement ps = conn.prepareStatement("update sell set sellID=?,"
						+ " usrID=?, bookID=?, sellNum=?, sellPrice=?,"
						+ " sellGraph=?, sellDesc=? ,bookPro=?, bookGrade=?,"
						+ "  bookSch=? where sellID=?");
				ps.setInt(1,sell.getSellID());
				ps.setInt(2,sell.getUsrID());
				ps.setLong(3,sell.getBookID());
				ps.setInt(4,sell.getSellNum());
				ps.setString(5,sell.getSellPrice());
				ps.setString(6,sell.getSellGraph());
				ps.setString(7,sell.getSellDesc());
				ps.setString(8, sell.getBookPro());
				ps.setString(9, sell.getBookGrade());
				ps.setString(10, sell.getBookSch());
				ps.setString(11, oldId);
				return ps.execute();
			}
			 
		 };
		return t.execute();		
	}
	
	public Boolean delete(long id) throws Exception {
   	 JDBCTemplate<Boolean> t=new Transaction<Boolean> () {

			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				PreparedStatement ps = conn.prepareStatement("delete from sell where sellID=?");
				ps.setLong(1, id);
				return ps.execute();
			}
   		 
   	 };
   	return t.execute();	
   }


	@Override
	public sell findBybookID(long id) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<sell> q = new Query<sell> () {

			@Override
			protected sell doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from sell where bookID=? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
				ps.execute();
				sell sell = null;
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					sell = new sell();
					sell.setSellID(rs.getInt("sellID"));
					sell.setBookID(rs.getLong("bookID"));
					sell.setUsrID(rs.getInt("usrID"));
					sell.setSellNum(rs.getInt("sellNum"));
					sell.setSellPrice(rs.getString("sellPrice"));
					sell.setSellGraph((rs.getString("sellGraph")));
					sell.setSellDesc(rs.getString("sellDesc"));
					sell.setBookSch(rs.getString("bookSch"));
					sell.setBookGrade(rs.getString("bookGrade"));
					sell.setBookPro(rs.getString("bookPro"));
				}
				return sell;
			}
	    		
		};
		return q.execute();
	}@Override
	public List<sell> findAllByUserID(long id) throws Exception {
		// TODO Auto-generated method stub
		List<sell> res=new ArrayList<sell>();
		JDBCTemplate<List<sell>> q = new Query<List<sell>> () {
			@Override
			protected List<sell> doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from sell where usrID=? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
				ps.execute();
				sell sell=null;
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					sell = new sell();
					sell.setBookID(rs.getLong("bookID"));
					sell.setSellNum(rs.getInt("sellNum"));
					sell.setSellPrice(rs.getString("sellPrice"));
					//sell.setSellGraph((rs.getString("sellGraph")));
					sell.setSellDesc(rs.getString("sellDesc"));
					res.add(sell);
				}
				return res;
			}
	    		
		};
		return q.execute();
	}
	@Override
	public List<sell> findAllByBookID(long id) throws Exception {
		// TODO Auto-generated method stub
		List<sell> res=new ArrayList<sell>();
		JDBCTemplate<List<sell>> q = new Query<List<sell>> () {
			@Override
			protected List<sell> doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from sell where bookID=? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
				ps.execute();
				sell sell=null;
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					sell = new sell();
					sell.setSellID(rs.getInt("sellID"));
					sell.setBookID(rs.getLong("bookID"));
					sell.setUsrID(rs.getInt("usrID"));
					sell.setSellNum(rs.getInt("sellNum"));
					sell.setSellPrice(rs.getString("sellPrice"));
					sell.setSellGraph((rs.getString("sellGraph")));
					sell.setSellDesc(rs.getString("sellDesc"));
					sell.setBookSch(rs.getString("bookSch"));
					sell.setBookGrade(rs.getString("bookGrade"));
					sell.setBookPro(rs.getString("bookPro"));
					res.add(sell);
				}
				return res;
			}
	    		
		};
		return q.execute();
	}
	@Override
	public List<Long> findByProGra(String college, String grade, String major) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<List<Long>> q = new Query<List<Long>> () {

			@Override
			protected List<Long> doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				List<Long> bookIDs = new ArrayList<Long>();
				String sql = "select distinct bookID from sell natural join bookinfo where bookSch=? and bookGrade=? and bookPro=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, college);
				ps.setString(2, grade);
				ps.setString(3, major);
				ps.execute();
				ResultSet rs = ps.executeQuery();
				Long bookID = null;;
				while (rs.next()) {
					bookID = rs.getLong("bookID");
					/*sell.setUsrID(rs.getInt("usrID"));
					sell.setSellNum(rs.getInt("sellNum"));
					sell.setSellPrice(rs.getString("sellPrice"));
					sell.setSellGraph(rs.getString("sellGraph"));
					sell.setSellDesc(rs.getString("sellDesc"));
					sell.setBookSch(rs.getString("bookSch"));
					sell.setBookGrade(rs.getString("bookGrade"));
					sell.setBookPro(rs.getString("bookPro"));*/
					bookIDs.add(bookID);
				}
				return bookIDs;
			}
			
		};
		return q.execute();
	}

	@Override
	public Long sellSum(long id) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<Long> q = new Query<Long> () {

			@Override
			protected Long doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select sum(sellNum) as sellNum from sell where bookID=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, id);			
				ps.execute();
				ResultSet rs = ps.executeQuery();
				sell sell = null;
				if(rs.next()) {
					sell = new sell();
					sell.setSellNum(rs.getInt("sellNum"));
				}
				return (long) sell.getSellNum();
			}
			
		};
		return q.execute();
	}
	
}
