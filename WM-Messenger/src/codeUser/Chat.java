package codeUser;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Chat extends JFrame {

	private JPanel contentPane;
	private JTextField txtPseudo;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	private Cast cast;
	private int log=0;



	/**
	 * Create the frame.
	 */
	public Chat() {

		cast = new Cast();

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
					cast.hello();

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

	public String gettxtpseudo() {
		return txtPseudo.getText();
	}


}



