package codeUser;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import net.Address;



/**
 * Minimal receiver: just prints any message it receives
 * @author t.perennou
 */
public class Cast implements NetListener {
	private Messenger messenger;
	
	private ListeContact liste_contact;
	private Broadcast broadcast;
	private Conversation[] conversations;
	
	private static NetInterface netif;

	private String nom_fichier;

	public Cast(Messenger mes)
	{
		try 
		{
			messenger = mes;
			
			NetInterface.setVerbose(false);
			netif = new NetInterface();
			netif.addNetListener(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void lien_onglets() {
		liste_contact = messenger.getListeContact();
		broadcast = messenger.getBroadcast();
		conversations = messenger.getConversations();
	}

	public void sendmessage(String destinataire) {

		// envoi du message	
		Conversation conv = messenger.trouverConversation(destinataire);
		Address addr1 = new Address(conv.getucast_address_window());
		try {
			netif.sendUnicast(conv.getucast_chat_field(), addr1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// copie à  l'envoyeur
		Address addr2 = netif.getAddress();
		Conversation conv2 = messenger.trouverConversation(addr1.toString());
		conv2.setucast_window(conv.getucast_window()+"\n"+addr2.toString()  + " : " + conv.getucast_chat_field());
	}

	
	
	public void broadcast(String message) {
		
		//envoie du broadcast
		try {
			netif.sendBroadcast(message);
			broadcast.setbcast_chat_field(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getAddress() {
		return netif.getAddress().toString();
	}

	public void unicastReceived(Address senderAddress, Serializable content)
	{
		
		if (content instanceof String)
		{
			//Si on recoit "roger.connect" on actualise la liste des contacts
			if(((String) content).matches("roger.connect"))
			{
				liste_contact.setlist_contact(senderAddress.toString());
				/*messenger.pseudoLinkAddress(senderAddress);
				liste_contact.setlist_contact(messenger.getpseudo());
				*/
			}
			else if (((String) content).length() > 9 && ((String) content).substring(0, 9).equals("file.name"))
			{
				nom_fichier = ((String) content).substring(10, ((String) content).length());
			}
			else
			{
				Conversation conv = messenger.trouverConversation(senderAddress.toString());
				String content_1=conv.getucast_window();
				conv.setucast_window(content_1+"\n"+senderAddress.toString()  + " : " + content);
			}
		}
		else
		{
			byte[] fichier_byte = (byte[]) content;
			
			String fichier_path = "D://" + nom_fichier;

			try {
				FileOutputStream fils = new FileOutputStream(fichier_path);
				fils.write(fichier_byte);
				fils.close();
				Conversation conv = messenger.trouverConversation(senderAddress.toString());
				String content_1=conv.getucast_window();
				conv.setucast_window(content_1+"\n"+senderAddress.toString()  + " : Fichier " + nom_fichier + " reçu");
			}
			catch (FileNotFoundException fnfe)
			{
				System.out.println("Créer le fichier avant noob");
				fnfe.printStackTrace();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}



	public void broadcastReceived(Address senderAddress,
			Serializable content) 
	{
		// On actualise la liste de contacts si on recoit "hello.connect" et on renvoit "roger.connect" pour que l'autre utilisateur
		// actualise sa liste de contacts
		if(((String) content).length() > 13 && ((String) content).substring(0, 13).equals("hello.connect"))
		{
			System.out.println("Roger Bébé");
			try {
				netif.sendUnicast("roger.connect",senderAddress);

				if (!senderAddress.toString().equals(getAddress())){

					liste_contact.setlist_contact(senderAddress.toString());
					//liste_contact.setlist_contact(messenger.getpseudo());
				} 

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
		
		else if (((String) content).matches("goodbye.connect"))
		{
			String expediteur = senderAddress.toString();
			liste_contact.remove_list_contact(expediteur);
		}
		
		else
		
		{
			String content_1 = broadcast.getbcast_window();
			broadcast.setbcast_window(content_1 + "\n" +senderAddress.toString()  + " : " +content);
		}
	}
	
	

	//Méthode appelée à la connexion
	public void hello()
	{
		try {
			System.out.println("hello.connect." + messenger.getpseudo());
			netif.sendBroadcast("hello.connect." + messenger.getpseudo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//Méthode appelée à la déconnexion
	public void goodbye()
	{
		try {
			netif.sendBroadcast("goodbye.connect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Envoi de fichiers
	public void sendFile_Unicast(File fichier, String destinataire)
	{
		try 
		{
			FileInputStream fils;
			fils = new FileInputStream(fichier);
			byte[] fichier_byte = new byte[(int) fichier.length()];
			fils.read(fichier_byte);
			
			Conversation conv = messenger.trouverConversation(destinataire);
			Address addr1 = new Address(destinataire);
			netif.sendUnicast(fichier_byte, addr1);

			Address addr2 = netif.getAddress();
			Conversation conv2 = messenger.trouverConversation(addr1.toString());
			conv2.setucast_window(conv.getucast_window()+"\n"+addr2.toString()  + " : Fichier envoyé");
		} 
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

}

