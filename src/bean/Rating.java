package bean;
class Rating
{
	String source;
	String value;

	public String getSource()
	{
		return source;
	}

	public String getValue()
	{
		return value;
	}

	public void setSource( String source )
	{
		this.source = source;
	}

	public void setValue( String value )
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "Rating [source=" + source + ", value=" + value + "]";
	}

}