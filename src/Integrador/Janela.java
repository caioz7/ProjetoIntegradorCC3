package Integrador;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	protected ImageIcon imgIcone = new ImageIcon(getClass().getResource("Icone.png"));
	public final ImageIcon planoDeFundo = new ImageIcon(getClass().getResource("Background_2.jpg"));
	public final JLabel imagemFundo = new JLabel(planoDeFundo);
	public Janela(int l, int h) {
		this.setSize(l, h);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);	
		this.setIconImage(imgIcone.getImage());
		revalidate();
		this.add(imagemFundo);
	}

	public void changerFond(JPanel fond) {
		setContentPane(fond);
		revalidate();
	}
}
