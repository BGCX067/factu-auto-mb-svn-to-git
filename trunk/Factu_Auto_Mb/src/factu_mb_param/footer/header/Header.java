package factu_mb_param.footer.header;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Header extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel headerPanel;
	private JLabel headerCenter,headerEast;
	
	public Header(){
		
	//HEADER CONTACT NUM
    headerPanel = new JPanel();
    headerPanel.setLayout(new BorderLayout());
    headerCenter = new JLabel(new ImageIcon(getClass().getResource("/headerCenterCompletel.png")));
    headerEast = new JLabel(new ImageIcon(getClass().getResource("/headerEastCompletel.png")));
    headerPanel.add(headerCenter,BorderLayout.CENTER);
    headerPanel.add(headerEast,BorderLayout.EAST);
//    headerPanel.setBackground(Color.getHSBColor(39,233,255));
    
	}
}
