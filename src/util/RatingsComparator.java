package util;

import java.util.Comparator;

import bean.IMDbBean;

public class RatingsComparator implements Comparator<IMDbBean>
{
	@Override
	public int compare( IMDbBean bean1, IMDbBean bean2 )
	{
		System.out.println(bean1.getImdbrating());
		Double rating1=Double.parseDouble(bean1.getImdbrating().split("/")[0]);
		Double rating2=Double.parseDouble(bean2.getImdbrating().split("/")[0]);
		return rating2.compareTo(rating1);
	}

}