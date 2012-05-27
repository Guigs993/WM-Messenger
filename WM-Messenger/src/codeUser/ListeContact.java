
package codeUser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;




public class ListeContact extends JPanel{

	private JList list_contact_window;
	private ArrayList<String> list_contact;
	private JTabbedPane tabbedPane;
	private Cast cast;



	public ListeContact(){

		super();
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
					Conversation conversation = new Conversation(cast);
					conversation.setLayout(null);
					tabbedPane.addTab(list_contact.get(index), null, conversation, null);





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

		DefaultListModel list_contact_model = new DefaultListModel();

		int taille = list_contact.size();

		for (int i = 0; i < taille; i++){
			list_contact_model.addElement(list_contact.get(i));
		}

		list_contact_window.removeAll();
		list_contact_window.setModel(list_contact_model);

	}
}

