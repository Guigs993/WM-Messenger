
package codeUser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;





public class ListeContact extends JPanel
{
	private Messenger messenger;
	private Cast cast;
	
	private JList list_contact_window;
	private ArrayList<String> list_contact;
	private ArrayList<String> list_contact_pseudo;

	public ListeContact (Cast c, Messenger mes)
	{
		super();
		
		cast = c;
		messenger = mes;
		
		// Onglet Liste Contacts
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
					String destinataire = list_contact.get(index);
					Conversation conversation = new Conversation(cast, destinataire);
					messenger.ajouterConversation(conversation);
					
				}
			}
		});

		add(list_contact_window);
	}


	public void setlist_contact(String texte) {
		list_contact.add(texte);

		/* Affiche le contenu de la liste
		for (String str : list_contact){
		System.out.println(str);
	}
	//*/
		
		refresh_list_contact();
	}
	
	public void remove_list_contact (String texte) 
	{
		ListIterator<String> it = list_contact.listIterator();
		while (it.hasNext())
		{
			String str = it.next();
			
			if (str.equals(texte))
				it.remove();
		}
		
		refresh_list_contact();
	}
	
	public void refresh_list_contact ()
	{

		DefaultListModel list_contact_model = new DefaultListModel();

		int taille = list_contact.size();

		for (int i = 0; i < taille; i++){
			list_contact_model.addElement(list_contact.get(i));
		}

		list_contact_window.removeAll();
		list_contact_window.setModel(list_contact_model);

	}
	
	public ArrayList<String> getListContact ()
	{
		return list_contact;
	}
}

