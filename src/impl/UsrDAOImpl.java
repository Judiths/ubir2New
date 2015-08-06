package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import advance.JDBCTemplate;
import advance.Query;
import advance.Transaction;
import dao.usrDao;
import domain.usrinfo;
/**
 * @author ubuntu
 * @date 2015-3-10
 * @descriptions We will gain the UsrInfo. And the main 
 * features in our system are the "add", "update" and the "delete"
 * 
 */
public class UsrDAOImpl implements usrDao {

	@Override
	public Boolean insert(usrinfo user) throws Exception {
		// TODO Auto-generated method stub
		 JDBCTemplate<Boolean> t = new Transaction<Boolean> () {

			@Override
			protected Boolean doTransaction(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				PreparedStatement ps=conn.prepareStatement("insert into usrinfo(usrName, usrTel, usrEmail, "
						+ "usrGrade, usrPro, usrSch, usrAddr, usrPw, usrSign) values(?,?,?,?,?,?,?,?,?)");
				//  usrInfo
				//ps.setLong(1, usr.getUsrID());
				ps.setString(1, user.getUsrName());
				ps.setString(2, user.getUsrTel());
				ps.setString(3, user.getUsrEmail());
				ps.setString(4, user.getUsrGrade());
				ps.setString(5, user.getUsrPro());
				ps.setString(6, user.getUsrSch());
				ps.setString(7, user.getUsrAddr());
				ps.setString(8, user.getUsrPw());
				ps.setString(9, user.getUsrSign());
				return ps.execute();
			}			 
		 };
		System.out.print(true);
		return t.execute(); 		 
	}

	@Override
	public Boolean Login(String usrName, String usrPw) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<Boolean> q = new Query<Boolean> () {

			@Override
			protected Boolean doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select usrName, usrPw from usrinfo where usrName=? and usrPw=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, usrName);
				ps.setString(2, usrPw);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					/*rs.getString("usrName");
					rs.getString("usrPw");*/
					return false;
							
				}
				return true;
			}			
		};
		return q.execute();
	}

	@Override
	public usrinfo findByName(String username) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<usrinfo> q = new Query<usrinfo> () {

			@Override
			protected usrinfo doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from usrinfo where usrName=? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				//ps.setString(2, password);
				usrinfo user = null;
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					user = new usrinfo();
					user.setUsrID(rs.getInt("usrID"));
					user.setUsrName(rs.getString("usrName"));
					user.setUsrTel(rs.getString("usrTel"));
					user.setUsrEmail(rs.getString("usrEmail"));
					user.setUsrGrade(rs.getString("usrGrade"));
					user.setUsrPro(rs.getString("usrPro"));
					user.setUsrSch(rs.getString("usrSch"));
					user.setUsrAddr(rs.getString("usrAddr"));
					user.setUsrPw(rs.getString("usrPw"));
					user.setUsrSign(rs.getString("usrSign"));
				}
				return user;
			}
			
		};
		return q.execute();
	}

	@Override
	public List<usrinfo> findAll() throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<List<usrinfo>> q = new Query<List<usrinfo>> () {

			@Override
			protected List<usrinfo> doQuery(Connection conn)
					throws Exception {
				// TODO Auto-generated method stub
				List<usrinfo> users = new ArrayList<usrinfo> ();
				String sql = "select * from usrinfo";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					usrinfo user = new usrinfo();
					user.setUsrID(rs.getInt("usrID"));
					user.setUsrName(rs.getString("usrName"));
					user.setUsrTel(rs.getString("usrTel"));
					user.setUsrEmail(rs.getString("usrEmail"));
					user.setUsrGrade(rs.getString("usrGrade"));
					user.setUsrPro(rs.getString("usrPro"));
					user.setUsrSch(rs.getString("usrSch"));
					user.setUsrAddr(rs.getString("usrAddr"));
					user.setUsrPw(rs.getString("usrPw"));
					user.setUsrSign(rs.getString("usrSign"));
					users.add(user);
				}
				return users;
			}
			
		};
		return q.execute();
	}

	@Override
	public Boolean update(usrinfo user, String username00)
			throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<Boolean> q = new Query<Boolean> () {
			
			@Override
			protected Boolean doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				
				String sql = "update usrinfo set usrName=?, usrTel=?, usrEmail=?, usrGrade=?, "
						+ "usrPro=?, usrSch=?, usrAddr=?, usrSign=? where usrName=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				//ps.setInt(1, user.getUid());
				ps.setString(1, user.getUsrName());
				ps.setString(2, user.getUsrTel());
				ps.setString(3, user.getUsrEmail());
				ps.setString(4, user.getUsrGrade());
				ps.setString(5, user.getUsrPro());
				ps.setString(6, user.getUsrSch());
				ps.setString(7, user.getUsrAddr());
				ps.setString(8, user.getUsrSign());
				ps.setString(9, username00);
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
				PreparedStatement ps = conn.prepareStatement("delete from usrinfo where id=?");
				ps.setInt(1, id);
				return ps.execute();
			}
			 
		 };
		 return t.execute();	
	}

	@Override
	public Boolean updatePw(String username, String password) throws Exception {
		// TODO Auto-generated method stub
JDBCTemplate<Boolean> q = new Query<Boolean> () {
			
			@Override
			protected Boolean doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "update usrinfo set usrPw=? where usrName=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				//ps.setInt(1, user.getUid());
				ps.setString(1, password);
				ps.setString(2, username);	
				return ps.execute();
			}
			
		};
		return q.execute();
	}

	@Override
	public usrinfo findByUID(long id) throws Exception {
		// TODO Auto-generated method stub
		JDBCTemplate<usrinfo> q = new Query<usrinfo> () {

			@Override
			protected usrinfo doQuery(Connection conn) throws Exception {
				// TODO Auto-generated method stub
				String sql = "select * from usrinfo where usrID=? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
				//ps.setString(2, password);
				usrinfo user = null;
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					user = new usrinfo();
					user.setUsrID(rs.getInt("usrID"));
					user.setUsrName(rs.getString("usrName"));
					user.setUsrTel(rs.getString("usrTel"));
					user.setUsrEmail(rs.getString("usrEmail"));
					user.setUsrGrade(rs.getString("usrGrade"));
					user.setUsrPro(rs.getString("usrPro"));
					user.setUsrSch(rs.getString("usrSch"));
					user.setUsrAddr(rs.getString("usrAddr"));
					user.setUsrPw(rs.getString("usrPw"));
					user.setUsrSign(rs.getString("usrSign"));
				}
				return user;
			}
			
		};
		return q.execute();
	}
}
