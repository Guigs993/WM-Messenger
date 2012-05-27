package codeUser;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Connexion extends JDialog implements ActionListener
{
	private Messenger messenger;
	
	private JLabel label_pseudo;
	private JTextField pseudo;
	private JButton bouton_connexion;
	
	public Connexion (JFrame parent, String title, boolean modal) 
	{
		super(parent, title, modal);
		
		// On lie le programme principal pour récuperer le pseudo
		messenger = (Messenger) parent;

		setPreferredSize(new Dimension(200, 110));
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		// Il ne se passe rien quand on essaie de fermer le JDialog
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		initComponent();

		setVisible(true);
	}
	
	private void initComponent ()
	{
		setLayout(null);
		label_pseudo = new JLabel("Pseudo : ");
		label_pseudo.setHorizontalAlignment(JLabel.CENTER);
		label_pseudo.setBounds(2, 0, 190, 25);
		add(label_pseudo);
		
		pseudo = new JTextField();
		pseudo.setBounds(27, 25, 140, 20);
		add(pseudo);
		
		bouton_connexion = new JButton("Connexion");
		bouton_connexion.setBounds(27, 50, 140, 25);
		bouton_connexion.addActionListener(this);
		add(bouton_connexion);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		messenger.setPseudo(pseudo.getText());
		setVisible(false);
	}
}
