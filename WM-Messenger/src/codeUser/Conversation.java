package codeUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Conversation extends JPanel{
	private String destinataire;
	private JTextField ucast_chat_field;
	private JTextField ucast_address_window;
	private JTextField user_address_window;
	private JTextArea ucast_window;
	private Cast cast;
	
	private JFileChooser jfc;
	
	public Conversation(Cast c, String adr){
		
		cast = c;
		
		
		setLayout(null);	
		
		destinataire = adr;

		ucast_chat_field=new JTextField();
		ucast_chat_field.setBounds(10, 240, 375, 74);
		add(ucast_chat_field);
		ucast_chat_field.setColumns(10);


		// Bouton envoi de messages
		JButton btnenvoyer = new JButton("Envoyer");
		btnenvoyer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				cast.sendmessage(destinataire);
				ucast_chat_field.setText("");
			}
		});


		btnenvoyer.setBounds(428, 291, 89, 23);
		add(btnenvoyer);



		ucast_address_window = new JTextField(destinataire);
		ucast_address_window.setBounds(395, 13, 122, 40);
		add(ucast_address_window);
		ucast_address_window.setColumns(10);

		user_address_window = new JTextField();
		user_address_window.setBounds(395, 240, 122, 40);
		user_address_window.setEditable(false);
		add(user_address_window);
		user_address_window.setColumns(10);
		user_address_window.setText(cast.getAddress());

		ucast_window = new JTextArea();
		ucast_window.setEditable(false);
		ucast_window.setBounds(10, 11, 375, 218);
		add(ucast_window);
		ucast_window.setColumns(10);
		ucast_window.setLineWrap(true);


		
		
		// Bouton envoi de fichiers
		JButton btnfichier = new JButton("Fichier");
		btnfichier.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File fichier = jfc.getSelectedFile();
					ucast_chat_field.setText("file.name."+fichier.getName());
					cast.sendmessage(destinataire);
					ucast_chat_field.setText("");
					
					cast.sendFile_Unicast(fichier, destinataire);
				}
				/*
				Cast onglet;
				cast.sendFile_Unicast();
				ucast_chat_field.setText("");
				*/

			}
		});

		
		btnfichier.setBounds(428, 200, 89, 23);
		add(btnfichier);
		
		
		
		
		
		
	}
	
	public String getDestinataire ()
	{
		return destinataire;
	}
	
	public String getucast_chat_field() {
		return ucast_chat_field.getText();
	}
	public String getucast_address_window() {
		return ucast_address_window.getText().trim();
	}

	public void setucast_window(String texte) {
		ucast_window.setText(texte);
	}
	
	public String getucast_window() {
		return ucast_window.getText();
	}

}

 


