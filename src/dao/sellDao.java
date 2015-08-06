package dao;

import java.util.List;

import domain.sell;

public interface sellDao {
	public Boolean addExtra(final sell seller) throws Exception;	// for checkin the seller
	public Boolean updateSeller(sell seller, String oldId) throws Exception;	// for modify seller
	public Boolean delete(long id) throws Exception;	// for checkout the seller
	public sell findBybookID(long id) throws Exception;	// for check the sell 
	public List<Long> findByProGra(String college, String grade, String major) throws Exception; // for recommand
	public Long sellSum(long id) throws Exception;
	public List<sell> findAllByBookID(long id) throws Exception;
	public List<sell> findAllByUserID(long id) throws Exception;
}
