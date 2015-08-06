package dao;

import java.util.List;

import domain.usrinfo;

public interface usrDao {
	public Boolean insert(usrinfo user) throws Exception;	// for register
	public Boolean Login(String username, String password) throws Exception;	// for login
	public usrinfo findByUID(long id) throws Exception;
	public usrinfo findByName (String username) throws Exception;	// for search
	public List<usrinfo> findAll() throws Exception;	// for GetInfo
	public Boolean update (usrinfo user,  String username00) throws Exception;	// for modify 
	public Boolean updatePw(String username, String password) throws Exception; // for modifyPw
	public Boolean delete (int id) throws Exception;	// 
}
