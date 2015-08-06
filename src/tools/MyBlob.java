package tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;


public class MyBlob implements Blob{ 
private InputStream in; 

public MyBlob(InputStream in){ 
this.in=in; 
} 

public InputStream getBinaryStream() throws SQLException { 
	if (in==null) throw new SQLException("Null Stream"); 
		return in; 
	} 

public byte[] getBytes(long pos, int length) throws SQLException { 
	byte[] buf=null; 
	try { 
		int len = 0; 
		byte[] tbuf=new byte[length]; 
		len=in.read(tbuf,(int)pos,length); 
		//len = in.read(tbuf);
		if(len>0) {
			buf=new byte[len]; 
			System.arraycopy(tbuf,0,buf,0,len); 
		} 
		tbuf=null; 
	} catch(IOException ex) { 
		throw new SQLException(ex.getMessage()); 
	} 
	return buf; 
} 

public long length() throws SQLException { 
	@SuppressWarnings("unused")
	long len=0; 
	try { 
		len=in.available(); 
	} catch(IOException ex) { 
		throw new SQLException(ex.getMessage()); 
	} 
	return 0; 
} 

public long position(Blob pattern, long start) throws SQLException{ 
	throw new SQLException("Not Support"); 
} 

public long position(byte[] pattern, long start) throws SQLException{ 
	throw new SQLException("Not Support"); 
}

@Override
public int setBytes(long pos, byte[] bytes) throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int setBytes(long pos, byte[] bytes, int offset, int len)
		throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public OutputStream setBinaryStream(long pos) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void truncate(long len) throws SQLException {
	// TODO Auto-generated method stub
	
}

@Override
public void free() throws SQLException {
	// TODO Auto-generated method stub
	
}

@Override
public InputStream getBinaryStream(long pos, long length) throws SQLException {
	// TODO Auto-generated method stub
	return null;
} 

} 
