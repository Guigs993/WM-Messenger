package codeUser;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Chat extends JFrame {

	private JPanel contentPane;
	private JTextField txtPseudo;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	private JTextField ucast_chat_field;
	private Cast onglet;
	private JTextField ucast_address_window;
	private  JTextField user_address_window;
	private JTextArea ucast_window;
	private JTextField bcast_chat_field;
	private  JTextField bcast_address_window;
	private JTextArea bcast_window;
	private JList list_contact_window;
	private int log=0;
	private ArrayList<String> list_contact;





	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat frame = new Chat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}






	/**
	 * Create the frame.
	 */
	public Chat() {

		try {
			onglet = new Cast(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);



		// Onglet connexion

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 532, 353);
		panel.add(tabbedPane);
		
		setContentPane(tabbedPane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Connexion", null, panel_1, null);
		panel_1.setLayout(null);


		

		JButton btnconnexion = new JButton("Connexion");
		btnconnexion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(log==0)
				{
					
					
					// Onglet Liste Contatcs

					list_contact = new ArrayList<String>();
					
					DefaultListModel list_contact_model = new DefaultListModel();
					
					int taille = list_contact.size();
					
					for (int i = 0; i < taille; i++){
						list_contact_model.addElement(list_contact.get(i));
					}
					
					list_contact_window = new JList(list_contact_model);
					list_contact_window.setBounds(10, 10, 400, 300);
					list_contact_window.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent me) {
							if (me.getClickCount() == 2) {
								
								
								
								
								// Onglet Conversation créé en double cliquant
								int index = list_contact_window.locationToIndex(me.getPoint());
								
								JPanel panel_3 = new JPanel();
								panel_3.setLayout(null);
								tabbedPane.addTab(list_contact.get(index), null, panel_3, null);

								ucast_chat_field=new JTextField();
								ucast_chat_field.setBounds(10, 240, 375, 74);
								panel_3.add(ucast_chat_field);
								ucast_chat_field.setColumns(10);


								// Bouton envoi de messages
								JButton btnenvoyer = new JButton("Envoyer");
								btnenvoyer.addActionListener(new ActionListener() 
								{
									public void actionPerformed(ActionEvent arg0) 
									{

										onglet.sendmessage();
										ucast_chat_field.setText("");

									}
								});


								btnenvoyer.setBounds(428, 291, 89, 23);
								panel_3.add(btnenvoyer);



								ucast_address_window = new JTextField();
								ucast_address_window.setBounds(395, 13, 122, 40);
								panel_3.add(ucast_address_window);
								ucast_address_window.setColumns(10);

								user_address_window = new JTextField();
								user_address_window.setBounds(395, 240, 122, 40);
								user_address_window.setEditable(false);
								panel_3.add(user_address_window);
								user_address_window.setColumns(10);
								user_address_window.setText(onglet.getAddress());

								ucast_window = new JTextArea();
								ucast_window.setEditable(false);
								ucast_window.setBounds(10, 11, 375, 218);
								panel_3.add(ucast_window);
								ucast_window.setColumns(10);
								ucast_window.setLineWrap(true);

				
								
								
								// Bouton envoi de fichiers
								JButton btnfichier = new JButton("Fichier");
								btnfichier.addActionListener(new ActionListener() 
								{
									public void actionPerformed(ActionEvent arg0) 
									{

										onglet.sendFile_Unicast();
										ucast_chat_field.setText("");

									}
								});

								
								btnfichier.setBounds(428, 200, 89, 23);
								panel_3.add(btnfichier);
								
								
								
								
								
								
							}
						}
					});
					
					
					JPanel panel_listecontact = new JPanel();
					panel_listecontact.setLayout(null);
					tabbedPane.addTab("Liste Contacts", null, panel_listecontact, null);


					panel_listecontact.add(list_contact_window);

					




					
					
					
					//Onglet Broadcast

					JPanel panel_3 = new JPanel();
					tabbedPane.addTab("Broadcast", null, panel_3, null);
					panel_3.setLayout(null);


					bcast_chat_field=new JTextField();
					bcast_chat_field.setBounds(10, 240, 375, 74);
					panel_3.add(bcast_chat_field);
					bcast_chat_field.setColumns(10);



					JButton btnenvoyer2 = new JButton("Envoyer");
					btnenvoyer2.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent arg0) 
						{

							onglet.broadcast();

						}
					});

					btnenvoyer2.setBounds(428, 291, 89, 23);
					panel_3.add(btnenvoyer2);


					bcast_address_window = new JTextField();
					bcast_address_window.setBounds(395, 240, 122, 40);
					bcast_address_window.setEditable(false);
					panel_3.add(bcast_address_window);
					bcast_address_window.setColumns(10);
					bcast_address_window.setText(onglet.getAddress());

					bcast_window = new JTextArea();
					bcast_window.setEditable(false);
					bcast_window.setBounds(10, 11, 375, 218);
					panel_3.add(bcast_window);
					bcast_window.setColumns(10);









					onglet.hello();

					log=1;
				}
				else
				{


				}
			}
		}
				);



		btnconnexion.setBounds(210, 165, 105, 32);
		panel_1.add(btnconnexion);

		txtPseudo = new JTextField();
		txtPseudo.setBounds(210, 101, 105, 32);
		panel_1.add(txtPseudo);
		txtPseudo.setText("Pseudo");
		txtPseudo.setColumns(10);
	}







	public void test()
	{
		System.out.println("aaaa");

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
	public String getbcast_chat_field() {
		return bcast_chat_field.getText();
	}


	public void setbcast_window(String texte) {	
		bcast_window.setText(texte);
	}
	public void setlist_contact(String texte) {
		list_contact.add(texte);
		
		/* Affiche le contenu de la liste
			for (String str : list_contact){
			System.out.println(str);
		}
		//*/
		
		DefaultListModel list_contact_model = new DefaultListModel();
		
		int taille = list_contact.size();
		
		for (int i = 0; i < taille; i++){
			list_contact_model.addElement(list_contact.get(i));
		}

		list_contact_window.removeAll();
		list_contact_window.setModel(list_contact_model);
	}


	public String getbcast_window() {
		return bcast_window.getText();
	}

	public String getucast_window() {
		return ucast_window.getText();
	}

	public String gettxtpseudo() {
		return txtPseudo.getText();
	}







}



