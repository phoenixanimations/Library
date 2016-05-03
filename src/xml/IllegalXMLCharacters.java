package xml;

public class IllegalXMLCharacters 
{
	
	private String[] illigalCharacters = {"~","`","!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","{","]","}","\\","|","'",";",":",",","<",">",".","/"," ","≥","≤","•"};
	private String[] replace = {"CHARonE","CHARtwO","CHARthreE","CHARfouR","CHARFivE","CHARSiX","CHARSeveN","CHAREighT","CHARNinE","CHARTeN","CHAREleveN","CHARTwelvE","CHARThirteeN","CHARFourteeN","CHARFifteeN","CHARSixteeN","CHARSeventeeN","CHAREightteeN","CHARNineteeN","CHARTwentY","CHARTwentyonE","CHARTwentytwO","CHARTwentythreE","CHARTwentyfouR","CHARTwentyfivE","CHARTwentysiX","CHARTwentyseveN","CHARTwentyeighT","CHARTwentyninE","CHARThirtY","CHARThirtyonE","CHARThirtytwO","CHARThirtythreE","CHARThirtyfouR"};
	
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