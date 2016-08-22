package ch.lgt.portmann.rss;

import java.util.ArrayList;
import java.util.List;
import ch.lgt.portmann.helper.FileHandler;

public class Source {

	List<RSSChannel> channels = new ArrayList<RSSChannel>();
	FileHandler fileHandler = new FileHandler();

	RSSHandler rssHandler = new RSSHandler();

	public Source(String path) {

		try {
			loadSource(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadSource(String path) throws Exception {

		for (String s : fileHandler.loadFileToList(path, false)) {
			
			System.out.println(s);

			RSSChannel entry = new RSSChannel();

			entry = rssHandler.getCannelInfo(s);
			entry.setUrl(s);

			this.channels.add(entry);

		}
	}

	public List<RSSChannel> getSources() {
		return channels;
	}

	public void setSources(List<RSSChannel> sources) {
		this.channels = sources;
	}

}