package factu_mb_param.footer.header;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Footer extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel footerPanel;
	private JLabel footerCenter,footerEast;
	
	public Footer(){
		
	//FOOTER CONTACT NUM
    footerPanel = new JPanel();
    footerPanel.setLayout(new BorderLayout());
    footerCenter = new JLabel(new ImageIcon(getClass().getResource("/footerCenterCompletel.png")));
    footerEast = new JLabel(new ImageIcon(getClass().getResource("/footerEastCompletel.png")));
    footerPanel.add(footerCenter,BorderLayout.CENTER);
    footerPanel.add(footerEast,BorderLayout.EAST);
//    footerPanel.setBackground(Color.getHSBColor(39,233,255));
    
	}
	
}
