package system;

public class IllegalXMLCharacters 
{
	private String[] illigalCharacters = {"~","`","!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","{","]","}","|","'",";",":",",","<",">",".","/"," ","≥","≤","•","1","2","3","4","5","6","7","8","9","0","\\"};
	private String[] numbers = {"Zero","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE"};
	
	public String stringToXML (String string)
	{
		for (Double i = 0d; i < illigalCharacters.length; i++)
		{
			Double first = Math.floor(i * .1);
			Double second = ((i * .1) - first) * 10;
			String xml = numbers[first.intValue()] + numbers[second.intValue()];
			if (string.contains(illigalCharacters[i.intValue()]))
			{
				string = string.replace(illigalCharacters[i.intValue()], xml);
			}
		}
		return string;
	}
	
	public String xmlToString (String string)
	{
		for (Double i = 0d; i < illigalCharacters.length; i++)
		{
			Double first = Math.floor(i * .1);
			Double second = ((i * .1) - first) * 10;
			String xml = numbers[first.intValue()] + numbers[second.intValue()];
			if (string.contains(xml))
			{
				string = string.replace(xml, illigalCharacters[i.intValue()]);
			}
		}
		return string;
	}
}