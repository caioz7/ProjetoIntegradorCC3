package Integrador;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;

	public Janela(int l, int h) {
		this.setSize(l, h);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void changerFond(JPanel fond) {
		setContentPane(fond);
		revalidate();
	}
}
