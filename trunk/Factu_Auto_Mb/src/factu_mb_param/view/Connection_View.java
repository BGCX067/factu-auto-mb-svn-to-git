package factu_mb_param.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import factu_mb_param.controller.ConnexionController;



public class Connection_View extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container pane;
	private Font fontBouton = new Font("Broadway", Font.BOLD, 12);
	private JPanel panelBordAuthent,nord,sud,est,ouest,centre;
	private JPanel panePrincipal= new JPanel();
	private JLabel lbNameUser,lbMdpUser;
	public JTextField txtNameUser;
	public JPasswordField txtPass;
	public JButton btConnection;
	
	public Connection_View(){
		
		pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		
		//Panel contour
		
		panelBordAuthent=new JPanel();
		panelBordAuthent.setLayout(new BorderLayout());
		nord=new JPanel();
		nord.setPreferredSize(new Dimension(520,80));
		sud=new JPanel();
		sud.setPreferredSize(new Dimension(520,80));
		est=new JPanel();
		est.setPreferredSize(new Dimension(80,100));
		ouest=new JPanel();
		ouest.setPreferredSize(new Dimension(80,100));
		
		//Panel Centre Authentification
		centre=new JPanel();
		centre.setPreferredSize(new Dimension(80,130));
		centre.setBackground(Color.black);
		Font font = new Font("Comic Sans MS", Font.BOLD, 12);
		Border titledBorder = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Authentification :", TitledBorder.RIGHT, TitledBorder.TOP, font, Color.getHSBColor(39,233,255));
		centre.setBorder(titledBorder);
		centre.setLayout(new BorderLayout());
		JPanel panelLogin=new JPanel();
		panelLogin.setLayout(new FlowLayout());
		panelLogin.setBackground(Color.black);
		panelLogin.add(lbNameUser=new JLabel("Login                : "));
		lbNameUser.setForeground(Color.getHSBColor(39,233,255));
		panelLogin.add(txtNameUser=new JTextField(10));
		JPanel panelPass=new JPanel();
		panelPass.setLayout(new FlowLayout());
		panelPass.setBackground(Color.black);
		panelPass.add(lbMdpUser=new JLabel("Mot de passe : "));
		lbMdpUser.setForeground(Color.getHSBColor(39,233,255));
		panelPass.add(txtPass=new JPasswordField(10));
		centre.add(panelLogin,BorderLayout.NORTH);	
		centre.add(panelPass,BorderLayout.CENTER);
		
		//Panel Bouton et label Authentification
		JPanel panelConnect=new JPanel();
		panelConnect.setLayout(new BorderLayout());
		panelConnect.setBackground(Color.black);
		JPanel panelcheckConnect=new JPanel();
		panelcheckConnect.add(btConnection=new JButton("Connexion"));
		btConnection.setFont(fontBouton);
		btConnection.setPreferredSize(new Dimension(135,21));
		panelcheckConnect.setBackground(Color.black);
		panelConnect.add(panelcheckConnect,BorderLayout.CENTER);
		centre.add(panelConnect,BorderLayout.SOUTH);
		
		//Ajout des contour
		panelBordAuthent.add(nord,BorderLayout.NORTH);
		panelBordAuthent.add(sud,BorderLayout.SOUTH);
		panelBordAuthent.add(est,BorderLayout.EAST);
		panelBordAuthent.add(ouest,BorderLayout.WEST);
		panelBordAuthent.add(centre,BorderLayout.CENTER);
		System.out.println("panel connection " +panelBordAuthent.getPreferredSize());
		panePrincipal.add(panelBordAuthent,BorderLayout.CENTER);		
		
		pane.add(panePrincipal,BorderLayout.CENTER);
		System.out.println(pane.getPreferredSize());
		
		btConnection.addActionListener(new ConnexionController(this));
		txtNameUser.addKeyListener(new ConnexionController(this));
		txtPass.addKeyListener(new ConnexionController(this));
	}
}
