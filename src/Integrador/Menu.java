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
<<<<<<< HEAD
	private JButton sair = new JButton("",imgSair);
=======
	private JButton Sair= new JButton("",imgSair);
>>>>>>> origin/master
	private JLabel janelaMenu = new JLabel(fundoMenu);
	public Menu() {

		super();
		janelaMenu.setBounds(0,0,1400,600);
		jogar.setBorder(null);
		jogar.setBounds(540, 150, 330, 220);
		jogar.setContentAreaFilled(false);
		jogar.setToolTipText("Iniciar");
		jogar.setFocusable(false);
<<<<<<< HEAD
		sair.setBorder(null);
		sair.setBounds(1260, 480, 110, 110);
		sair.setContentAreaFilled(false);
		sair.setToolTipText("Fechar");
=======
		Sair.setBorder(null);
		Sair.setBounds(1260, 480, 110, 110);
		Sair.setContentAreaFilled(false);
		Sair.setToolTipText("Fechar");
>>>>>>> origin/master
		setLayout(null);
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
<<<<<<< HEAD
		sair.addActionListener(new ActionListener() {			
=======
		Sair.addActionListener(new ActionListener() {			
>>>>>>> origin/master
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
<<<<<<< HEAD
		add(sair);
=======
		add(Sair);
>>>>>>> origin/master
		add(jogar);
		add(janelaMenu);
	}
}
