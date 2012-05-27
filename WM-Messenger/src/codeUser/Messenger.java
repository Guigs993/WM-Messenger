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
	private Conversation[] conversations;
	
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
		onglet_liste_contact = new ListeContact(cast, this);
		onglets.addTab("Liste de contact", null, onglet_liste_contact, null);
		
		// Création onglet broadcast
		onglet_broadcast = new Broadcast(cast);
		onglets.addTab("Broadcast", null, onglet_broadcast, null);
		
		cast.lien_onglets();
		
		// On initialise le tableau des conversations
		conversations = new Conversation[0];

	}
	
	public void setPseudo (String ps)
	{
		pseudo = ps;
	}
	
	public void ajouterConversation (Conversation conversation)
	{
		// On créer un tableau temporaire
		Conversation[] conversations_tmp = new Conversation[conversations.length+1];
		
		// On transfert les données
		for (int i=0; i<conversations.length; i++)
		{
			conversations_tmp[i] = conversations[i];
		}
		
		// On ajoute la nouvelle conversation
		conversations_tmp[conversations_tmp.length-1] = conversation;
		conversations = conversations_tmp;
		
		onglets.addTab(conversation.getDestinataire(), null, conversation, null);
	}
	
	public Conversation trouverConversation (String address)
	{
		for (Conversation conv : conversations)
		{
			if (conv.getDestinataire().equals(address))
				return conv;
		}
		Conversation nouvelle_conversation = new Conversation(cast, address);
		ajouterConversation(nouvelle_conversation);
		
		return nouvelle_conversation;
	}
	
	public ListeContact getListeContact () 
	{
		return onglet_liste_contact;
	}
	
	public Broadcast getBroadcast () 
	{
		return onglet_broadcast;
	}
	
	public Conversation[] getConversations ()
	{
		return conversations;
	}
	
	public Cast getCast ()
	{
		return cast;
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
					Connexion popup = new Connexion(mes, "Connexion", true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
