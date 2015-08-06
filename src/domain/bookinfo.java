package domain;

/**
 * @author ubuntu
 * @descriptions bookInfo table in database
 * MariaDB [ubir2]> describe bookinfo;
+-------------+---------------------------+------+-----+---------+----------------+
| Field       | Type                      | Null | Key | Default | Extra          |
+-------------+---------------------------+------+-----+---------+----------------+
| bookID      | int(10) unsigned zerofill | NO   | PRI | NULL    | auto_increment |
| bookISBN    | varchar(13)               | NO   | UNI | NULL    |                |
| bookName    | varchar(255)              | NO   |     | NULL    |                |
| bookPrice   | varchar(20)               | YES  |     | NULL    |                |
| bookAuthor  | varchar(255)              | NO   |     | NULL    |                |
| bookPublish | varchar(255)              | NO   |     | NULL    |                |
+-------------+---------------------------+------+-----+---------+----------------+

 */
public class bookinfo {

	private long bookID;
	private String bookISBN;
	private String bookName;
	private String bookPrice;		// attention: we just have a data import from Douban, and we have no need to calculate the price,
	private String bookAuthor;
	private String bookPublish;
	public bookinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public bookinfo(/*int bookID*/ String bookISBN, String bookName,
			String bookPrice, String bookAuthor, String bookPublish) {
		super();
		//this.bookID = bookID;
		this.bookISBN = bookISBN;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookAuthor = bookAuthor;
		this.bookPublish = bookPublish;
	}
	public long getBookID() {
		return bookID;
	}
	public void setBookID(long bookID) {
		this.bookID = bookID;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPublish() {
		return bookPublish;
	}
	public void setBookPublish(String bookPublish) {
		this.bookPublish = bookPublish;
	}
}
