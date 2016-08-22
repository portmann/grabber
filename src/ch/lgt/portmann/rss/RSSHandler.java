package ch.lgt.portmann.rss;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RSSHandler {

	public RSSChannel getCannelInfo(String url) {

		RSSChannel rssChannel = new RSSChannel();

		try {
			URL feedUrl = new URL(url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));

			rssChannel.setTitle(feed.getTitle());
			rssChannel.setDescription("");
			rssChannel.setLanguage(feed.getLanguage());

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rssChannel;
	}

	@SuppressWarnings("unchecked")
	public List<RSSItem> getItems(String url, int chanelID) {

		List<RSSItem> items = new ArrayList<RSSItem>();

		try {
			URL feedUrl = new URL(url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));

			for (SyndEntry e : (List<SyndEntry>) feed.getEntries()) {

				RSSItem newItem = new RSSItem();

				newItem.setIdChannel(chanelID);
				newItem.setTitle(e.getTitle());
				newItem.setDate(e.getPublishedDate());
				newItem.setUrl(e.getLink());
				
				String file = downloadFile(newItem);
				newItem.setFile(file);

				items.add(newItem);

			}

		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return items;

	}

	// Separate function for error handling and strategies
	@SuppressWarnings("resource")
	public String downloadFile(RSSItem rssItem, int tries) {

		String file = "";

		try {

			file = new Scanner(new URL(rssItem.getUrl()).openStream(), "UTF-8").useDelimiter("\\A").next();

		} catch (Exception ex) {

			rssItem.setError(true);
			rssItem.setErrorMessage(ex.getMessage());

			if (ex.getMessage().equals("The remote server returned an error: (429) Unknown Error.")) {

				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (tries < 10) {
					downloadFile(rssItem, tries++);
				} else {
					System.out.println("Could not handle the following error");
				}

			}

			System.out.println("ERROR: " + ex.getMessage());
		}

		return file;
	}

	public String downloadFile(RSSItem rssItem) {

		return downloadFile(rssItem, 0);

	}
}
