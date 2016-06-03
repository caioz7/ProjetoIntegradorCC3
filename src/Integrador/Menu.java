package Integrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	protected ImageIcon imgStart = new ImageIcon(getClass().getResource("Start.png"));
	protected ImageIcon fundoMenu = new ImageIcon(getClass().getResource("BackgroundAngry.jpg"));
	protected JButton jogar = new JButton("",imgStart);
	protected JLabel janelaMenu = new JLabel(fundoMenu);
	public Menu() {

		super();
		janelaMenu.setBounds(0,0,1024,576);
		setLayout(null);
		jogar.setBounds(336, 150, 330, 220);
		jogar.setContentAreaFilled(false);
		jogar.setToolTipText("Iniciar");
		jogar.setBorder(null);
		revalidate();
		
	
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
		add(janelaMenu);
	}
}
