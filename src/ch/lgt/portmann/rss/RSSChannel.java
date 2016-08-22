package ch.lgt.portmann.rss;

public class RSSChannel {
	
    public int idChannel;
    public String title;
    public String description;
    public String language;
    public String url;
    
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
}
