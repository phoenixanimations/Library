package xml;

public class IllegalXMLCharacters 
{
	
	private String[] illigalCharacters = {"~","`","!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","{","]","}","\\","|","'",";",":",",","<",">",".","/"," ","≥","≤","•","1","2","3","4","5","6","7","8","9","0"};
	private String[] replace = {"CHARONE","CHARTWO","CHARTHREE","CHARFOUR","CHARFIVE","CHARSIX","CHARSEVEN","CHAREIGHT","CHARNINE","CHARTEN","CHARELEVEN","CHARTWELVE","CHARTHIRTEEN","CHARFOURTEEN","CHARFIFTEEN","CHARSIXTEEN","CHARSEVENTEEN","CHAREIGHTEEN","CHARNINETEEN","CHARTWENTY","CHARTWENTYONE","CHARTWENTYTWO","CHARTWENTYTHREE","CHARTWENTYFOUR","CHARTWENTYFIVE","CHARTWENTYSIX","CHARTWENTYSEVEN","CHARTWENTYEIGHT","CHARTWENTYNINE","CHARTHIRTY","CHARTHIRTYONE","CHARTHIRTYTWO","CHARTHIRTYTHREE","CHARTHIRTYFOUR","CHARTHIRTYFIVE","CHARTHIRTYSIX","CHARTHIRTYSEVEN","CHARTHIRTYEIGHT","CHARTHIRTYNINE","CHARFOURTY","CHARFOURTYONE","CHARFOURTYTWO","CHARFOURTYTHREE","CHARFOURTYFOUR"};
	
	public String stringToXML (String string)
	{
		for (int i = 0; i < replace.length; i++)
		{
			if (string.contains(illigalCharacters[i]))
			{
				string = string.replace(illigalCharacters[i], replace[i]);
			}
		}
		return string;
	}
	
	public String xmlToString (String string)
	{
		for (int i = 0; i < replace.length; i++)
		{
			if (string.contains(replace[i]))
			{
				string = string.replace(replace[i], illigalCharacters[i]);
			}
		}
		return string;
	}
}