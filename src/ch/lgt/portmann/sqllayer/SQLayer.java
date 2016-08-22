package ch.lgt.portmann.sqllayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import ch.lgt.portmann.rss.RSSChannel;
import ch.lgt.portmann.rss.RSSItem;

public class SQLayer {

	SQLiteHandler sqlHandler;

	public SQLayer(String connectionString) {

		sqlHandler = new SQLiteHandler(connectionString);
	}
	
	public int getItemId(String url, String title, Date date) throws SQLException{
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, -3);
		 
		 Date rangeDate = cal.getTime();

         String sqlString = "SELECT idItem FROM item WHERE title = '" + title.replace("'", "") + "' AND date(date) > date('"
                   + rangeDate + "');";
         
         ResultSet rs = this.sqlHandler.runQuery(sqlString);
         
         if (rs.isClosed())
 			return 0;
 		else
 			return rs.getInt("idItem");
	}
	
	public int setItem(RSSItem rssItem){
		
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
		
		String sqlString = "INSERT INTO item (idChannel, title, url, date) values ('" + rssItem.getIdChannel() 
				+ "', '" + rssItem.getTitle().replace("'", "") + "','" 
				+ rssItem.getUrl().replace("'", "") + "','" 
				+ dt1.format(rssItem.getDate()) + "')";
		return sqlHandler.runUpdate(sqlString);
	
	}

	public int getChannelId(String url) throws SQLException {

		ResultSet rs = sqlHandler.runQuery("SELECT idChannel From channel WHERE url = '" + url.replace("'", "") + "'");

		if (rs.isClosed())
			return 0;
		else
			return rs.getInt("idChannel");

	}

	public int setChannel(RSSChannel rssChannel) {
		
		try {

		String insertString = "INSERT INTO channel (title, description, url) VALUES ('" + rssChannel.getTitle().replace("'", "") 
				+ "', ''" + rssChannel.getDescription().replace("'", "") + ",'" + rssChannel.getUrl().replace("'", "") + "')";

		
		sqlHandler.runUpdate(insertString);

		// get new Id

		String sql = "SELECT idChannel From channel WHERE url = '" + rssChannel.getUrl().replace("'", "") + "'";
		ResultSet rs;
		
			rs = sqlHandler.runQuery(sql);
			return rs.getInt("idChannel");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

}