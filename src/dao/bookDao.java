package dao;

import java.util.List;

import domain.bookinfo;

public interface bookDao {
	public Boolean insert(bookinfo book) throws Exception;
	public bookinfo findById(long id) throws Exception;
	public bookinfo findByISBN(String isbn) throws Exception;
	public List<bookinfo> findAll() throws Exception;
	public Boolean update(bookinfo book, long oldid) throws Exception;
	public Boolean delete(int id) throws Exception;
}
