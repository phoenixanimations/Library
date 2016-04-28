package library.TextField;

public class LibrarySearch extends LibraryTextField
{
	private static final long serialVersionUID = -481221882331525417L;
	private String lastString = new String("");
	
	public LibrarySearch ()
	{
		super();
		setBounds(4, 6, 592, 28);
		setColumns(10);
	}
	
	@Override
	public void onActionListener() 
	{
		super.onActionListener();
		lastString = getText();
		setText("");
	}
	
	public String getLastString ()
	{
		return lastString;
	}
}