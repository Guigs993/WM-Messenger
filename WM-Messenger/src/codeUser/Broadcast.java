
package codeUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Broadcast extends JPanel{
	
	private JTextField bcast_chat_field;
	private  JTextField bcast_address_window;
	private JTextArea bcast_window;
	private Cast cast;
	
	
	public Broadcast(Cast c){
		
		cast = c;


		setLayout(null);


		bcast_chat_field=new JTextField();
		bcast_chat_field.setBounds(10, 240, 375, 74);
		add(bcast_chat_field);
		bcast_chat_field.setColumns(10);



		JButton btnenvoyer2 = new JButton("Envoyer");
		btnenvoyer2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				cast.broadcast();

			}
		});

		btnenvoyer2.setBounds(428, 291, 89, 23);
		add(btnenvoyer2);


		bcast_address_window = new JTextField();
		bcast_address_window.setBounds(395, 240, 122, 40);
		bcast_address_window.setEditable(false);
		add(bcast_address_window);
		bcast_address_window.setColumns(10);
		bcast_address_window.setText(cast.getAddress());

		bcast_window = new JTextArea();
		bcast_window.setEditable(false);
		bcast_window.setBounds(10, 11, 375, 218);
		add(bcast_window);
		bcast_window.setColumns(10);

	}
	
	
	public String getbcast_chat_field() {
		return bcast_chat_field.getText();
	}


	public void setbcast_window(String texte) {	
		bcast_window.setText(texte);
	}
	
	public String getbcast_window() {
		return bcast_window.getText();
	}

}
