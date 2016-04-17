package library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main 
{
	private JFrame frame;

	/**************************
	 **Launch the application**
	 **************************/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
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
		});
	}

	/**************************
	 **Create the application**
	 **************************/
	public Main() 
	{
		initialize();
	}

	/**************************
	 **Initialize Application**
	 **************************/
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		buttonExample ();
	}
	
	/**************************
	 *********Buttons**********
	 **************************/
	private void buttonExample ()
	{
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() 
		{
			/**************************
			 *********On Click*********
			 **************************/
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("hi");
			}
		});
		btnNewButton.setBounds(6, 6, 135, 29);
		frame.getContentPane().add(btnNewButton);
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

	}	



*/