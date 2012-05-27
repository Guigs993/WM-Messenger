package codeUser;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import net.Address;



/**
 * Minimal receiver: just prints any message it receives
 * @author t.perennou
 */
public class Cast implements NetListener {



	private Conversation conversation;
	private Broadcast broadcast;
	private ListeContact listecontact;
	private Chat chat;
	private static NetInterface netif;



	public Cast(Chat c) throws IOException 
	{

		chat = c;
		NetInterface.setVerbose(false);
		netif = new NetInterface();
		netif.addNetListener(this);

	}



	public void sendmessage() {

		// envoi du message
		if(conversation.getucast_address_window().length() > 0)
		{
			Address addr1 = new Address(conversation.getucast_address_window());
			try {
				netif.sendUnicast(conversation.getucast_chat_field(), addr1);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// copie à  l'envoyeur 
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast(conversation.getucast_chat_field(), addr2);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else
		{
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast("Veuillez entrer une adresse", addr2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	public void broadcast() {
		
		//envoie du broadcast
		try {
			netif.sendBroadcast(broadcast.getbcast_chat_field());
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
		/*
		if(content instanceof String)
		{
		 */
		
		//Si on recoit "roger.connect" on actualise la liste des contacts
		if(((String) content).matches("roger.connect"))
		{
			listecontact.setlist_contact(senderAddress.toString());	
		}
		else
		{
			String content_1=conversation.getucast_window();
			conversation.setucast_window(content_1+"\n"+senderAddress.toString()  + " : " + content);
		}
		/*
		}
		else
		{
			String strFilePath = "D://test.txt";

		      FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(strFilePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      String strContent = "Write File using Java FileOutputStream example !";


		       try {
				fos.write(strContent.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		       try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		 */
	}



	public void broadcastReceived(Address senderAddress,
			Serializable content) 
	{
		//On actualise la liste des contacts si on recoit "hello.connect" et on renvoit "roger.connect" pour que l'autre utilisateur actualise
		//sa liste de contacts
		if(((String) content).matches("hello.connect"))
		{
			try {
				netif.sendUnicast("roger.connect",senderAddress);

				if (!senderAddress.toString().equals(getAddress())){

					listecontact.setlist_contact(senderAddress.toString());
				} 

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}

		else
		{
			String content_1=broadcast.getbcast_window();
			broadcast.setbcast_window(content_1 + "\n" +senderAddress.toString()  + " : " +content);
		}
	}
	
	

	//Méthode appelée à la connexion
	public void hello()
	{
		try {
			netif.sendBroadcast("hello.connect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//Envoi de fichiers
	public void sendFile_Unicast()
	{
		String path=conversation.getucast_chat_field();

		File file = new File(path);
		FileInputStream file_sent = null;
		try {
			file_sent = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast("Veuillez entrer un chemin correct", addr2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		byte fileContent[] = new byte[(int) file.length()];


		if(conversation.getucast_address_window().length() > 0)
		{
			Address addr1 = new Address(conversation.getucast_address_window());
			try {

				netif.sendUnicast(fileContent, addr1);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// copie Ã  l'envoyeur 
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast("Fichier Envoyé", addr2);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast("Veuillez entrer une adresse", addr2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

