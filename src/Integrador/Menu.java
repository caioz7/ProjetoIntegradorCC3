package Integrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	public ImageIcon imgStart = new ImageIcon(getClass().getResource("Start.png"));
	public ImageIcon fundoMenu = new ImageIcon(getClass().getResource("Background_2.jpg"));
	public ImageIcon imgSair = new ImageIcon(getClass().getResource("ExitButton.png"));
	private JButton jogar = new JButton("",imgStart);
	private JButton sair = new JButton("",imgSair);
	private JLabel janelaMenu = new JLabel(fundoMenu);
	public Menu() {

		super();
		janelaMenu.setBounds(0,0,1400,600);
		jogar.setBorder(null);
		jogar.setBounds(540, 150, 330, 220);
		jogar.setContentAreaFilled(false);
		jogar.setToolTipText("Iniciar");
		jogar.setFocusable(false);
		sair.setBorder(null);
		sair.setBounds(1260, 480, 110, 110);
		sair.setContentAreaFilled(false);
		sair.setToolTipText("Fechar");
		setLayout(null);
		revalidate();
		
	
		jogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cenario cenario = new Cenario(Main.obterJanela().getContentPane().getWidth(),
						Main.obterJanela().getContentPane().getHeight(), 40, 135, 195);
				cenario.adicionarPassaro(new Passaro(cenario));
				//new Gravidade(cenario);
				Main.obterJanela().changerFond(cenario);
				
				
			}
		});
		sair.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		add(sair);
		add(jogar);
		add(janelaMenu);
	}
}
