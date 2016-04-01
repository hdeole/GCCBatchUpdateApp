package org.app.gcc.dao.entities;

//@DatabaseTable(tableName="SITE_PAGE_CONTENT_KEYS")
public class SitePageContentKeys {

	//@DatabaseField(id=true ,columnName="PAGE_ID")
	private Long pageId;
	
	//@DatabaseField(columnName="CONTENT_KEY")
	private String contentKey; 
	
	public SitePageContentKeys(){}

	public SitePageContentKeys(long pageId, String contentKey) {
		super();
		this.pageId = pageId;
		this.contentKey = contentKey;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public String getContentKey() {
		return contentKey;
	}

	public void setContentKey(String contentKey) {
		this.contentKey = contentKey;
	}
	
	
	
}
