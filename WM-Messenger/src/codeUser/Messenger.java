package codeUser;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Messenger extends JFrame
{
	private Cast cast;
	private ListeContact onglet_liste_contact;
	private Broadcast onglet_broadcast;
	
	private JTabbedPane onglets;
	private String pseudo;
	
	public Messenger ()
	{
		super();

		setTitle("WM-Messenger v0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Création onglets
		onglets = new JTabbedPane(JTabbedPane.TOP);
		onglets.setPreferredSize(new Dimension(600, 400));
		
		setContentPane(onglets);
		// Pack permet d'ajuster la taille de la JFrame en fonction du PreferredSize de son ContentPane
		pack();
		
		// Création cast
		cast = new Cast(this);
		
		// Positionne la fenêtre au milieu de l'écran
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = getSize().width;
		int height = getSize().height;
		int x = (dimension.width - width) / 2;
		int y = (dimension.height - height) / 2;
		setLocation(x, y);
		
		// Création onglet liste de contact
		onglet_liste_contact = new ListeContact();
		onglets.addTab("Liste de contact", null, onglet_liste_contact, null);
		
		// Création onglet broadcast
		onglet_broadcast = new Broadcast(cast);
		onglets.addTab("Broadcast", null, onglet_broadcast, null);
		
		cast.lien_onglets();
	}
	
	public ListeContact getListeContact() 
	{
		return onglet_liste_contact;
	}
	
	public Broadcast getBroadcast() 
	{
		return onglet_broadcast;
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
