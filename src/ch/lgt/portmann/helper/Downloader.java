package ch.lgt.portmann.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Downloader {
	
	public Downloader() {
		
	}
	
	public String downloadToString(String url) throws IOException{
		
		String returnString = "";
		
		URL rUrl = new URL(url);
		
        InputStream is = rUrl.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
         
        String line;
        String newline = System.getProperty("line.separator");
        
        while ( (line = br.readLine()) != null)
        {
            System.out.println(line);
            returnString = returnString + line + newline;
        }
        
        br.close();
        is.close();
		
		return returnString;
		
	}
	
}
