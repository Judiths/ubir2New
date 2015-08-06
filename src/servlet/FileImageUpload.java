package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/FileImageUpload")
public class FileImageUpload extends HttpServlet {
	private ServletFileUpload upload;
	private final long MAXSize = 4194304*2L;//4*2MB
	private String filedir=null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FileImageUpload() {
	        super();
	        // TODO Auto-generated constructor stub
	}
	
	public void init(ServletConfig config) throws ServletException {
		FileItemFactory factory = new DiskFileItemFactory();// Create a factory for disk-based file items
		this.upload = new ServletFileUpload(factory);// Create a new file upload handler
		this.upload.setSizeMax(this.MAXSize);// Set overall request size constraint 4194304
//		filedir=config.getServletContext().getRealPath("images");
		filedir="/home/ubuntu/Pictures/images/";
		System.out.println("filedir= "+filedir);
	}
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			List<FileItem> items = this.upload.parseRequest(request);
			if(items!=null	&& !items.isEmpty()){
				for (FileItem fileItem : items) {
					String filename=fileItem.getName();
					String filepath=filedir+File.separator+filename;
					System.out.println("Pictures' paths:"+filepath);
					File file=new File(filepath);
					InputStream inputSteam=fileItem.getInputStream();
					BufferedInputStream fis=new BufferedInputStream(inputSteam);
				    FileOutputStream fos=new FileOutputStream(file);
				    int f;
				    while((f=fis.read())!=-1)
				    {
				       fos.write(f);
				    }
				    fos.flush();
				    fos.close();
				    fis.close();
					inputSteam.close();
					System.out.println("filename"+filename+"ok");
				}
			}
			System.out.println("Upload success!");
			out.write("Upload success!");
		} catch (FileUploadException e) {
			e.printStackTrace();
			out.write("Upload failed"+e.getMessage());
		}
	}
}
