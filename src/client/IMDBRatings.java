package client;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.IMDbBean;
import util.RatingsComparator;

/**
 * @author:Govinda<govinda@cdac.in> Date:11-May-2017
 */
public class IMDBRatings
{
	/**
		 * @author:Govinda<govinda@cdac.in>
		 * Date:11-May-2017
		 */
	

	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main( String[] args ) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String moviesRootDirectory = sc.next();

		List<File> fileList = new ArrayList<>();
		IMDBRatings imdb = new IMDBRatings();
		imdb.listAllFiles(moviesRootDirectory, fileList);

		Scanner scanner = new Scanner(System.in);
	
		int num = 20;
		scanner.close();
		
		List<String> imdbId = new ArrayList<String>();
		for (File file : fileList)
		{
			String searchTerm = file.getName();

			String searchURL = GOOGLE_SEARCH_URL + "?q=imdb:" + searchTerm + "&num=" + num;

			Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
			
			Elements results = doc.select("h3.r > a");

			for (Element result : results)
			{
				String linkHref = result.attr("href");
				String linkText = result.text();

				if (linkText.contains("IMDb"))
				{
					if (linkHref.contains("imdb"))
					{
						String linkTokens[] = linkHref.substring(6, linkHref.indexOf("&")).split("/");
						System.out.println(linkTokens[linkTokens.length - 1]);
						imdbId.add(linkTokens[linkTokens.length - 1]);
						break;
					}
				}
			}
		}
		
		List<IMDbBean> imdbBeans= getRatings(imdbId);
		Collections.sort(imdbBeans,new RatingsComparator());
		
		for(IMDbBean imdbBean: imdbBeans) {
			System.out.println(imdbBean.getTitle()+"::"+imdbBean.getImdbrating());
		}
	}

	public void listAllFiles( String rootDirectoryName, List<File> files ) throws IOException
	{
		File directory = new File(rootDirectoryName);
		File[] fList = directory.listFiles();
		for (File file : fList)
		{
			if (file.isFile())
			{
				String contentType = Files.probeContentType(file.toPath());
			//	if(contentType.contains("video"))
					files.add(file);
			}
			else if (file.isDirectory())
			{
				listAllFiles(file.getAbsolutePath(), files);
			}
		}
	}
	
	public static List<IMDbBean> getRatings(List<String> imdbIds) throws ClientProtocolException, IOException{
		final String omdbiURL = "http://www.omdbapi.com/?i="; boolean print = true;
		List<IMDbBean> imdbParsedList = new ArrayList<>();
		for(String id: imdbIds) {
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(omdbiURL+id);
			HttpResponse httpResponse = httpClient.execute(getRequest);
			HttpEntity entity = httpResponse.getEntity();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			
			while ((line = rd.readLine()) != null) {
			    result.append(line);
			}

			JSONObject o = new JSONObject(result.toString());
			String jsonString = o.toString().toLowerCase();
			
			if(print){
				System.out.println(jsonString);
				print=false;
			}
			ObjectMapper objMapper = new ObjectMapper(); 
			IMDbBean imdb = objMapper.readValue(jsonString,IMDbBean.class );
			imdbParsedList.add(imdb);
			System.out.println(imdb);
		}
		return imdbParsedList;
	}
	
}