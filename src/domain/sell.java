package domain;

/**
 * @author ubuntu
 * @descriptions the relation between bookInfo and usrInfo
 * MariaDB [ubir2]> describe sell;
+-----------+----------------------+------+-----+---------+----------------+
| Field     | Type                 | Null | Key | Default | Extra          |
+-----------+----------------------+------+-----+---------+----------------+
| sellID    | int(10) unsigned     | NO   | PRI | NULL    | auto_increment |
| usrID     | int(10) unsigned     | NO   | MUL | NULL    |                |
| bookID    | int(10) unsigned     | NO   | MUL | NULL    |                |
| sellNum   | smallint(5) unsigned | NO   |     | NULL    |                |
| sellPrice | varchar(20)          | YES  |     | NULL    |                |
| sellGraph | mediumblob           | YES  |     | NULL    |                |
| sellDesc  | varchar(255)         | YES  |     | NULL    |                |
| bookPro   | varchar(50)          | NO   |     | NULL    |                |
| bookGrade | varchar(8)           | NO   |     | NULL    |                |
| bookSch   | varchar(50)          | NO   |     | NULL    |                |
+-----------+----------------------+------+-----+---------+----------------+
10 rows in set (0.00 sec)
 */
public class sell{
	private int sellID;
	private int usrID;
	private long bookID;
    private int sellNum;
	private String sellPrice;
	private String sellGraph;
	private String sellDesc;
	private String bookSch;
	private String bookGrade;
	private String bookPro;
	public sell() {
		super();
		// TODO Auto-generated constructor stub
	}
	public sell(/*int sellID,*/ int usrID, long bookID, int sellNum, String sellPrice, 
			String sellGraph, String sellDesc, String bookSch, String bookGrade, String bookPro) {
		super();
		//this.sellID = sellID;
		this.usrID = usrID;
		this.bookID = bookID;
		this.sellNum = sellNum;
		this.sellPrice = sellPrice;
		this.sellGraph = sellGraph;
		this.sellDesc = sellDesc;
		this.bookSch = bookSch;
		this.bookGrade = bookGrade;
		this.bookPro = bookPro;
	}
	public int getSellID() {
		return sellID;
	}
	public void setSellID(int sellID) {
		this.sellID = sellID;
	}
	public int getUsrID() {
		return usrID;
	}
	public void setUsrID(int usrID) {
		this.usrID = usrID;
	}
	public long getBookID() {
		return bookID;
	}
	public void setBookID(long bookID) {
		this.bookID = bookID;
	}
	public int getSellNum() {
		return sellNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getSellGraph() {
		return sellGraph;
	}
	public void setSellGraph(String sellGraph) {
		this.sellGraph = sellGraph;
	}
	public String getSellDesc() {
		return sellDesc;
	}
	public void setSellDesc(String sellDesc) {
		this.sellDesc = sellDesc;
	}
	public String getBookSch() {
		return bookSch;
	}
	public void setBookSch(String bookSch) {
		this.bookSch = bookSch;
	}
	public String getBookGrade() {
		return bookGrade;
	}
	public void setBookGrade(String bookGrade) {
		this.bookGrade = bookGrade;
	}
	public String getBookPro() {
		return bookPro;
	}
	public void setBookPro(String bookPro) {
		this.bookPro = bookPro;
	}
}