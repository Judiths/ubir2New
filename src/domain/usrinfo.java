package domain;

/**
 * @author ubuntu
 * @descriptions usrInfo table with database
 * MariaDB [ubir2]> describe usrinfo;
+----------+------------------+------+-----+---------+----------------+
| Field    | Type             | Null | Key | Default | Extra          |
+----------+------------------+------+-----+---------+----------------+
| usrID    | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| usrName  | varchar(20)      | NO   | UNI | NULL    |                |
| usrTel   | char(11)         | YES  |     | NULL    |                |
| usrEmail | varchar(50)      | YES  |     | NULL    |                |
| usrGrade | varchar(50)      | YES  |     | NULL    |                |
| usrPro   | varchar(50)      | YES  |     | NULL    |                |
| usrSch   | varchar(50)      | YES  |     | NULL    |                |
| usrAddr  | varchar(255)     | YES  |     | NULL    |                |
| usrPw    | varchar(20)      | YES  |     | NULL    |                |
| usrSign  | varchar(255)     | YES  |     | NULL    |                |
+----------+------------------+------+-----+---------+----------------+
10 rows in set (0.01 sec)
 */
public class usrinfo {
	private int usrID;
	private String usrName;
	private String usrTel;
	private String usrEmail;
	private String usrGrade;
	private String usrPro;
	private String usrSch;
	private String  usrAddr;
	private String usrPw;
	private String usrSign;
	public usrinfo(/*int usrID, */String usrName, String usrTel,
			String usrEmail, String usrGrade, String usrPro, String usrSch,
			String usrAddr, String usrPw, String usrSign) {
		super();
		//this.usrID = usrID;
		this.usrName = usrName;
		this.usrTel = usrTel;
		this.usrEmail = usrEmail;
		this.usrGrade = usrGrade;
		this.usrPro = usrPro;
		this.usrSch = usrSch;
		this.usrAddr = usrAddr;
		this.usrPw = usrPw;
		this.usrSign = usrSign;
	}

	public usrinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getUsrID() {
		return usrID;
	}
	public void setUsrID(int usrID) {
		this.usrID = usrID;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getUsrTel() {
		return usrTel;
	}
	public void setUsrTel(String usrTel) {
		this.usrTel = usrTel;
	}
	public String getUsrEmail() {
		return usrEmail;
	}
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}
	public String getUsrGrade() {
		return usrGrade;
	}
	public void setUsrGrade(String usrGrade) {
		this.usrGrade = usrGrade;
	}
	public String getUsrPro() {
		return usrPro;
	}
	public void setUsrPro(String usrPro) {
		this.usrPro = usrPro;
	}
	public String getUsrSch() {
		return usrSch;
	}
	public void setUsrSch(String usrSch) {
		this.usrSch = usrSch;
	}
	public String getUsrAddr() {
		return usrAddr;
	}
	public void setUsrAddr(String usrAddr) {
		this.usrAddr = usrAddr;
	}
	public String getUsrPw() {
		return usrPw;
	}
	public void setUsrPw(String usrPw) {
		this.usrPw = usrPw;
	}
	public String getUsrSign() {
		return usrSign;
	}
	public void setUsrSign(String usrSign) {
		this.usrSign = usrSign;
	}
}