package Integrador;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cenario extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private final Movimento trac;
	private ArrayList<Passaro> passaro;
	private final int altura;
	private final int largura;
	private final int alturaChao;
	private final int posicaoTrajetoria;
	private final int alturaLancamento;
	private boolean arrastar = false;
	private double angulo;
	private double velocidade;
	protected JButton novoPassaro;
	protected JButton sair;
	private Passaro passaroLancado;
	ImageIcon passaroVermelho = new ImageIcon(getClass().getResource("RedBird_01.png"));
	ImageIcon fundoMenu = new ImageIcon(getClass().getResource("Background_2.jpg"));
	ImageIcon catapulta = new ImageIcon(getClass().getResource("Catapult.png"));

	public Cenario(int larg, int alt, int alturaC, int posTraj, int alturaL) {
		setLayout(null);
		passaro = new ArrayList<Passaro>();
		largura = larg;
		altura = alt;
		alturaChao = alturaC;
		alturaLancamento = alturaL;
		posicaoTrajetoria = posTraj;

		trac = new Movimento(new Cordenada(posicaoTrajetoria, altura - alturaChao));
		this.setRequestFocusEnabled(true);
		this.requestFocus();
		repaint();

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if (passaroLancado != null && !passaroLancado.inicioVoo() && !passaroLancado.fimVoo()) {
					Cordenada coord = trac.mapaCenario(new Cordenada(arg0.getX(), arg0.getY()));
					int dist = new Cordenada(0, alturaLancamento).distancia(coord);
					if (dist < 120) {
						passaroLancado.definirCordenada(coord);
						repaint();
						velocidade = dist + 20;
						angulo = 0;
						double tan = 0;
						if (passaroLancado.obterX() <= 0 && passaroLancado.obterY() <= alturaLancamento) {
							double coteOppose = alturaLancamento - passaroLancado.obterY();
							double coteAdj = -passaroLancado.obterX();
							tan = coteOppose / coteAdj;
							angulo = Math.toDegrees(Math.atan(tan));
						}
						if (passaroLancado.obterX() < 0 && passaroLancado.obterY() > alturaLancamento) {
							double catetoOposto = passaroLancado.obterY() - alturaLancamento;
							double catetoAdjacente = -passaroLancado.obterX();
							tan = catetoOposto / catetoAdjacente;
							angulo = -Math.toDegrees(Math.atan(tan));
						}
						if (passaroLancado.obterX() > 0 && passaroLancado.obterY() > alturaLancamento) {
							double catetoOposto = passaroLancado.obterY() - alturaLancamento;
							double catetoAdjacente = passaroLancado.obterX();
							tan = catetoOposto / catetoAdjacente;
							angulo = Math.toDegrees(Math.atan(tan)) + 180;
						}
						if (passaroLancado.obterX() >= 0 && passaroLancado.obterY() <= alturaLancamento) {
							double catetoOposto = passaroLancado.obterX();
							double catetoAdjacente = alturaLancamento - passaroLancado.obterY();
							tan = catetoOposto / catetoAdjacente;
							angulo = 90 + Math.toDegrees(Math.atan(tan));
						}
						Cordenada cord2 = ObjetoLancado.proximaCordenada(25, passaroLancado.obterCordenada(),
								new Cordenada(0, alturaLancamento + passaroLancado.obterTamanho() / 2));
						passaroLancado.alterarProximaCordenada(cord2);
						arrastar = true;
					}
				}
			}
		});
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent me) {
				if (arrastar) {
					if (passaroLancado != null && !passaroLancado.inicioVoo() && !passaroLancado.fimVoo()) {
						lancarObjeto();
					}
					arrastar = false;
				}

			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

		});
		ImageIcon imgVoltar = new ImageIcon(getClass().getResource("HomeButton.png"));
		JButton sair = new JButton("", imgVoltar);
		sair.setBounds(1300, 0, 100, 100);
		sair.setToolTipText("Voltar ao menu principal");
		sair.setBorder(null);
		sair.setFocusPainted(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.obterJanela().changerFond(new Menu());
			}
		});
		add(sair);
		
		ImageIcon imgRestart = new ImageIcon(getClass().getResource("RestartButton.png"));
		novoPassaro = new JButton("", imgRestart);
		novoPassaro.setBounds(1170, 0, 117, 113);
		novoPassaro.setBorder(null);
		novoPassaro.setFocusPainted(false);
		novoPassaro.setContentAreaFilled(false);
		novoPassaro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cenario cenario = new Cenario(Main.obterJanela().getContentPane().getWidth(),
						Main.obterJanela().getContentPane().getHeight(), 40, 135, 195);
				Main.obterJanela().changerFond(cenario);
			}
		});
		add(novoPassaro);
	}

	public Passaro passaroLancado() {
		for (Passaro o : passaro) {
			if (!o.fimVoo()) {
				passaroLancado = o;
				o.localLancamento();
				return o;
			}
		}
		return null;
	}

	public void lancarObjeto() {
		new ObjetoLancado(passaroLancado, this, velocidade, angulo);
		passaroLancado.voar();
		
	}

	public Movimento obterMovimento() {
		return trac;
		
	}

	// PLANO DE FUNDO IN-GAME
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// PLANO DE FUNDO
		Image imgBack = fundoMenu.getImage();
		g.drawImage(imgBack, 0, 0, this);

		// ESTILINGUE
		Image catapult = catapulta.getImage();
		g.drawImage(catapult, 50, 300, this);
		
		// PASSARO
		for (Passaro o : passaro) {
			Image imgBird = passaroVermelho.getImage();
			Cordenada cordPos = trac.mapaTracado(o.obterCordenada());

			g.drawImage(imgBird, cordPos.obterX() - o.obterTamanho() / 2, cordPos.obterY() - o.obterTamanho() / 2,
					o.obterTamanho(), o.obterTamanho(), this);

		}
		// ELASTICO DO ESTILINGUE
		if (passaroLancado != null) {
			int dist = new Cordenada(0, alturaLancamento).distancia(passaroLancado.obterCordenada());
			if (dist < 120) {
				Graphics2D g2 = (Graphics2D) (g);
				g2.setStroke(new BasicStroke(8));
				g2.setColor(new Color(238, 201, 0));
				Cordenada co = trac.mapaTracado(passaroLancado.obterCordenada());
				Cordenada cl = trac.mapaTracado(new Cordenada(0, alturaLancamento));
				g2.draw(new Line2D.Float(co.obterX(), co.obterY(), cl.obterX(), cl.obterY()));
				
			} else if (dist > 200) {
				Graphics2D g2 = (Graphics2D) (g);
				g2.setStroke(new BasicStroke(8));
				g2.setColor(new Color(238, 201, 0));
				Cordenada cl = trac.mapaTracado(new Cordenada(0, alturaLancamento));
				g2.draw(new Line2D.Float(cl.obterX(), cl.obterY(), cl.obterX(), cl.obterY()));		
			}	
		}

		revalidate();
	}

	public int obterAlturaLancamento() {
		return alturaLancamento;
	}

	public int obterLargura() {
		return largura;
	}

	public int obterAltura() {
		return altura;
	}

	public int obterAlturaChao() {
		return alturaChao;
	}

	public int obterPosicaoTrajetoria() {
		return posicaoTrajetoria;
	}

	public ArrayList<Passaro> obterPassaro() {
		return passaro;
	}

	public void adicionarPassaro(Passaro o) {
		passaro.add(o);
		o.addObserver(this);
		if (passaroLancado == null) {
			passaroLancado = o;
			o.localLancamento();
			
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
