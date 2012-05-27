package codeUser;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Connexion extends JDialog
{
	
	public Connexion (JFrame parent, String title, boolean modal) 
	{
		super(parent, title, modal);

		setSize(200, 80);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
}
