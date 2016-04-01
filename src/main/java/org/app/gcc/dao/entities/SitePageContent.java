package org.app.gcc.dao.entities;

//@DatabaseTable(tableName="SITE_PAGE_CONTENT")
public class SitePageContent {
	
	//@DatabaseField(columnName="CONTENT_KEY",id=true)
	private String contentKey;
	//@DatabaseField
	private String data;
	
	public SitePageContent(){}
	public SitePageContent(String contentKey, String data) {
		super();
		this.contentKey = contentKey;
		this.data = data;
	}
	public String getContentKey() {
		return contentKey;
	}
	public void setContentKey(String contentKey) {
		this.contentKey = contentKey;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	

}
