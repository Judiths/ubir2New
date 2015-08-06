package helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

public class GetImagePath {
	public String getImage(String filedir, List<FileItem> items) {
		System.out.println("Pictures!");
		if(items!=null	&& !items.isEmpty()){
			for (FileItem fileItem : items) {
				
				String filename=fileItem.getName();
				String filepath=filedir+File.separator+filename;
				InputStream inputStream;
				try {
					inputStream = fileItem.getInputStream();
					System.out.println("Pictures' paths:"+filepath);
					System.out.println("filename"+filename+"ok");
					ReadImage(filepath, inputStream);
					System.out.println("Read over.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return filepath;
			}
		}
		return "";
		
	}

	private void ReadImage(String filepath, InputStream inputStream) {
		// TODO Auto-generated method stub
		File file = new File(filepath);
		BufferedInputStream fis=new BufferedInputStream(inputStream);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			int f;
			while((f=fis.read())!=-1) {
				fos.write(f);
			}
			fos.flush();
			fos.close();
			fis.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find the file" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Error!" + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
