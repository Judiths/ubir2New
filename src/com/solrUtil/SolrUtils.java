package com.solrUtil;


import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONException;

import domain.bookinfo;
import domain.sell;
import domain.usrinfo;

/**
 * @author Sergio_Aguero 2015��3��7������10:49:03
 */
public class SolrUtils {
	private final static String url = "http://202.202.5.134:10000/solr";
	private static HttpSolrServer server = new HttpSolrServer(url);
	private static Log logger=LogFactory.getLog(SolrUtils.class);
	
	public static SolrDocumentList query(String key,String keyWord)
			throws SolrServerException, JSONException {
		SolrQuery query = new SolrQuery();
		query.setQuery(key+":" + keyWord);
		query.setSort("id", SolrQuery.ORDER.asc);

		QueryResponse rep = server.query(query);
		SolrDocumentList list = rep.getResults();

		return list;
	}
	
	public SolrDocumentList recommend(usrinfo usr) throws SolrServerException{
		String keyWord=usr.getUsrPro();
		SolrQuery query = new SolrQuery();
		query.setQuery("bookPro:" + keyWord);
		query.setSort("id", SolrQuery.ORDER.asc);

		QueryResponse rep = server.query(query);
		SolrDocumentList list = rep.getResults();

		return list;
		
	}
	
	public void addDoc(bookinfo info){
		SolrInputDocument doc=new SolrInputDocument();
		
		doc.addField("bookISBN", info.getBookISBN());
		doc.addField("bookPublish", info.getBookPublish());
		doc.addField("bookPrice", info.getBookPrice());
		doc.addField("bookAuthor", info.getBookAuthor());
		doc.addField("bookName", info.getBookName());
		doc.addField("bookID", info.getBookID());
		
		System.out.println(doc.toString());
		try{
			server.add(doc);
			server.commit();
		}catch(SolrServerException e){
			logger.error("", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDocToSell(sell sell){
SolrInputDocument doc=new SolrInputDocument();
		doc.addField("bookID", sell.getBookID());
		doc.addField("sellID", sell.getSellID());
		doc.addField("usrID", sell.getUsrID());
		doc.addField("sellNum", sell.getSellNum());
		doc.addField("bookPro", sell.getBookPro());
		doc.addField("bookGrade", sell.getBookGrade());
	
		
		System.out.println(doc.toString());
		try{
			server.add(doc);
			server.commit();
		}catch(SolrServerException e){
			logger.error("", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteByQuery(String query) throws SolrServerException, IOException{
		server.deleteByQuery(query);
		server.commit();
	}

}
