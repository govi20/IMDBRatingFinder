package bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IMDbBean
{
	String imdbid;
	String title;
	String genre;
	String director;
	String writer;
	String actors;
	String year;
	String type;

	String imdbrating;
	List<Rating> ratings;

	String numberofvotes;
	String runtime;
	String plot;
	String language;
	String awards;
	public String getImdbid()
	{
		return imdbid;
	}
	public String getTitle()
	{
		return title;
	}
	public String getGenre()
	{
		return genre;
	}
	public String getDirector()
	{
		return director;
	}
	public String getWriter()
	{
		return writer;
	}
	public String getActors()
	{
		return actors;
	}
	public String getYear()
	{
		return year;
	}
	public String getType()
	{
		return type;
	}
	public String getImdbrating()
	{
		return imdbrating;
	}
	public List<Rating> getRatings()
	{
		return ratings;
	}
	public String getNumberofvotes()
	{
		return numberofvotes;
	}
	public String getRuntime()
	{
		return runtime;
	}
	public String getPlot()
	{
		return plot;
	}
	public String getLanguage()
	{
		return language;
	}
	public String getAwards()
	{
		return awards;
	}
	public void setImdbid( String imdbid )
	{
		this.imdbid = imdbid;
	}
	public void setTitle( String title )
	{
		this.title = title;
	}
	public void setGenre( String genre )
	{
		this.genre = genre;
	}
	public void setDirector( String director )
	{
		this.director = director;
	}
	public void setWriter( String writer )
	{
		this.writer = writer;
	}
	public void setActors( String actors )
	{
		this.actors = actors;
	}
	public void setYear( String year )
	{
		this.year = year;
	}
	public void setType( String type )
	{
		this.type = type;
	}
	public void setImdbrating( String imdbrating )
	{
		this.imdbrating = imdbrating;
	}
	public void setRatings( List<Rating> ratings )
	{
		this.ratings = ratings;
	}
	public void setNumberofvotes( String numberofvotes )
	{
		this.numberofvotes = numberofvotes;
	}
	public void setRuntime( String runtime )
	{
		this.runtime = runtime;
	}
	public void setPlot( String plot )
	{
		this.plot = plot;
	}
	public void setLanguage( String language )
	{
		this.language = language;
	}
	public void setAwards( String awards )
	{
		this.awards = awards;
	}

	

}