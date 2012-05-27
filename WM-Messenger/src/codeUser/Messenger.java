package codeUser;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Messenger extends JFrame
{
	private JTabbedPane onglets;
	private String pseudo;
	
	public Messenger ()
	{
		super();

		setTitle("WM-Messenger v0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Cr�ation onglets
		onglets = new JTabbedPane(JTabbedPane.TOP);
		onglets.setPreferredSize(new Dimension(600, 400));
		
		setContentPane(onglets);
		// Pack permet d'ajuster la taille de la JFrame en fonction du PreferredSize de son ContentPane
		pack();
		
		// Positionne la fen�tre au milieu de l'�cran
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = getSize().width;
		int height = getSize().height;
		int x = (dimension.width - width) / 2;
		int y = (dimension.height - height) / 2;
		setLocation(x, y);
		
	}
	
	public static void main (String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Messenger mes = new Messenger();
					mes.setVisible(true);

					// Popup de connexion
					//Connexion popup = new Connexion(null, "Connexion", true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}