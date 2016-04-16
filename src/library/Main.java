package library;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater
		(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Main window = new Main();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	/**
	 * Create the application.
	 */
	public Main() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/*
 	public void onClick() throws IOException  
	{
		testingButton = "yay";
		lblHi.setText(testingButton);
		List<File> fileExtensions = new ArrayList<File>();
		try 
		{
			 Files.walk(Paths.get("./img"))
			 	  .filter(Files::isRegularFile)
				  .filter(e -> e.toString().toLowerCase().contains(".jpeg") || 
						  	   e.toString().toLowerCase().contains(".jpg"))
				  .forEach(x -> fileExtensions.add(x.toFile()));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		fileExtensions.forEach(x -> System.out.println(x.toString()));	
		Desktop.getDesktop().open(fileExtensions.get(0));
		Desktop.getDesktop().open(fileExtensions.get(1));
 */
