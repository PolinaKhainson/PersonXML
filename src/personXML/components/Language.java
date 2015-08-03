package personXML.components;

public enum Language 
{
	EN,
	RU,
	UA;
	
	public static Language stringToLanguage( String line )
	{
		Language lg = null;
		
		if ( line.equals( "EN" ) )
			lg = Language.EN;
		else	
			if ( line.equals( "RU" ) )
				lg = Language.RU;
			else
				if ( line.equals( "UA" ) )
					lg = Language.UA;
		return lg;
	}
}
