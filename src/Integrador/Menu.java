package Integrador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	protected Image img;
	protected JButton jogar;

	public Menu() {

		super();
		setBackground(Color.yellow);
		setLayout(null);

		jogar = new JButton("");
		jogar.setIcon(new ImageIcon("http://i.imgur.com/xjMpoHs.png"));
		jogar.setBounds(356, 100, 270, 270);
		jogar.setForeground(Color.BLACK);
		jogar.setContentAreaFilled(false);
		jogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cenario cenario = new Cenario(Main.obterJanela().getContentPane().getWidth(),
						Main.obterJanela().getContentPane().getHeight(), 40, 125, 100);
				cenario.adicionarPassaro(new Passaro(cenario));
				new Gravidade(cenario);
				Main.obterJanela().changerFond(cenario);
			}
		});
		add(jogar);
	}

}
