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


		if(chat.getucast_address_window().length() > 0)
		{
			Address addr1 = new Address(chat.getucast_address_window());
			try {
				netif.sendUnicast(chat.getucast_chat_field(), addr1);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// copie à l'envoyeur 
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast(chat.getucast_chat_field(), addr2);

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

		try {
			netif.sendBroadcast(chat.getbcast_chat_field());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static String getAddress() {
		return netif.getAddress().toString();
	}


	@Override
	public void unicastReceived(Address senderAddress, Serializable content) 
	{
		/*
		if(content instanceof String)
		{
		*/
		if(((String) content).matches("roger.connect"))
		{
			chat.setlist_contact(senderAddress.toString());	
		}
		else
		{
			String content_1=chat.getucast_window();
			chat.setucast_window(content_1+"\n"+senderAddress.toString()  + " : " + content);
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


	@Override
	public void broadcastReceived(Address senderAddress,
			Serializable content) 
	{

		if(((String) content).matches("hello.connect"))
		{
			try {
				netif.sendUnicast("roger.connect",senderAddress);
				
				if (!senderAddress.toString().equals(getAddress())){

					chat.setlist_contact(senderAddress.toString());
				} 
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}

		else
		{
			String content_1=chat.getbcast_window();
			chat.setbcast_window(content_1 + "\n" +senderAddress.toString()  + " : " +content);
		}
	}

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
		String path=chat.getucast_chat_field();
		
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


		if(chat.getucast_address_window().length() > 0)
		{
			Address addr1 = new Address(chat.getucast_address_window());
			try {

				netif.sendUnicast(fileContent, addr1);


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// copie à l'envoyeur 
			Address addr2 = netif.getAddress();
			try {
				netif.sendUnicast("Fichier Envoy�", addr2);

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

