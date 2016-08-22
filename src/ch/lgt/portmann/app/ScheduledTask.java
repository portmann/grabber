package ch.lgt.portmann.app;

import java.io.IOException;
import java.sql.SQLException;
import ch.lgt.portmann.helper.FileHandler;
import ch.lgt.portmann.rss.RSSChannel;
import ch.lgt.portmann.rss.RSSHandler;
import ch.lgt.portmann.rss.RSSItem;
import ch.lgt.portmann.rss.Source;
import ch.lgt.portmann.sqllayer.SQLayer;

public class ScheduledTask {

	public static void main(String args[]) throws IOException, SQLException {

		Source source = new Source("data/project.csv");
		SQLayer sqlayer = new SQLayer("jdbc:sqlite:data/rssFeeds.db");
		RSSHandler rssHandler = new RSSHandler();
		FileHandler fileHandler = new FileHandler();

		for (RSSChannel rssC : source.getSources())

		{
			
			int channelId = sqlayer.getChannelId(rssC.getUrl());

	
				

				if (channelId <= 0) {

					if (rssC != null)
						channelId = sqlayer.setChannel(rssC);

				}


			for (RSSItem rssI : rssHandler.getItems(rssC.getUrl(), channelId)) {

				try {

					int itemId = sqlayer.getItemId(rssI.getUrl(), rssI.getTitle(), rssI.getDate());

					if (itemId <= 0) {

						rssI.setIdItem(sqlayer.setItem(rssI));

						fileHandler.saveStringAsFile(
								"data/downloads/" + rssI.getIdItem() / 500 + "/" + rssI.getIdItem() + ".html",
								rssI.getFile());

					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

}