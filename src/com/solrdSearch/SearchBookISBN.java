package com.solrdSearch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONException;
import org.json.JSONObject;

import com.solrUtil.SolrUtils;

/**
 * Servlet implementation class SearchAuthor
 */
@WebServlet("/SearchBookISBN")
public class SearchBookISBN extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SolrDocumentList list = new SolrDocumentList();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookISBN() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String key="bookISBN";
		
		String keyWord = request.getParameter("bookISBN");
		//String keyWord = "9787302138037";
		System.out.println(keyWord);
		String state="";
		 try {
			list = SolrUtils.query(key,keyWord);
		} catch (SolrServerException | JSONException e) {
			e.printStackTrace();
		}
	    state=list.size()==0?"fail":"success";
       
		JSONObject json = null;
        if(state.equals("success")) {
        	json=new JSONObject(list.get(0));
        }else{
        	json=new JSONObject();
        }
		try {
			json.put("state",state);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json.toString());
        out.write(json.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
