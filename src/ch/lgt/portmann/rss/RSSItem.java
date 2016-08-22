package ch.lgt.portmann.rss;

import java.util.Date;

public class RSSItem {
	
	  private int idItem;
	  private int idChannel;
	  private String title;
	  private String url;
	  private Date date;
	  private String mD5;
	  private String file;
	  private Boolean error;
	  private String errorMessage;
	  
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getIdChannel() {
		return idChannel;
	}
	public void setIdChannel(int idChannel) {
		this.idChannel = idChannel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMD5() {
		return mD5;
	}
	public void setMD5(String mD5) {
		this.mD5 = mD5;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
      
}