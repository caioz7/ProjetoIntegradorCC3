package Integrador;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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
	private double angulo, velocidade;
	protected JButton novoPassaro;
	protected JButton sair;
	private Passaro passaroLancado;

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

			public void mouseMoved(MouseEvent arg0) {
			}

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

			public void mouseReleased(MouseEvent me) {
				if (arrastar) {
					if (passaroLancado != null && !passaroLancado.inicioVoo() && !passaroLancado.fimVoo()) {
						lancarObjeto();
					}
					arrastar = false;
				}

			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent arg0) {
			}

		});

		JButton sair = new JButton("SAIR");
		sair.setBounds(860, 20, 100, 50);
		sair.setFont(new Font("", Font.ITALIC, 18));
		sair.setForeground(Color.BLACK);
		sair.setFocusPainted(false);
		sair.setContentAreaFilled(false);
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.obterJanela().changerFond(new Menu());
			}
		});
		add(sair);

		novoPassaro = new JButton("NOVO LANCAMENTO");
		novoPassaro.setBounds(630, 20, 220, 50);
		novoPassaro.setFont(new Font("", Font.ITALIC, 18));
		;
		novoPassaro.setForeground(Color.BLACK);
		novoPassaro.setFocusPainted(false);
		novoPassaro.setContentAreaFilled(false);
		novoPassaro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cenario cenario = new Cenario(Main.obterJanela().getContentPane().getWidth(),
						Main.obterJanela().getContentPane().getHeight(), 40, 125, 100);
				cenario.adicionarPassaro(new Passaro(cenario));
				new Gravidade(cenario);
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
		JLabel lblVelocidade = new JLabel("Velocidade:"+velocidade);
		lblVelocidade.setBounds(10, 11, 135, 14);
		add(lblVelocidade);
	}

	public Movimento obterMovimento() {
		return trac;
	}

	public void paintComponent(Graphics g) {

		g.setColor(new Color(91, 158, 238));
		g.fillRect(0, 0, largura, altura);
		g.fillRect(0, altura - alturaChao, largura, alturaChao);
		g.setColor(new Color(138, 104, 44));
		g.fillRect(100, 350, 50, 300);

		for (Passaro o : passaro) {
			g.setColor(Color.red);
			Cordenada cordPos = trac.mapaTracado(o.obterCordenada());
			g.fillOval(cordPos.obterX() - o.obterTamanho() / 2, cordPos.obterY() - o.obterTamanho() / 2,
					o.obterTamanho(), o.obterTamanho());
			Cordenada cordPos2 = trac.mapaTracado(o.obterProximaCordenada());
			g.drawLine(cordPos.obterX(), cordPos.obterY(), cordPos2.obterX(), cordPos2.obterY());
		}

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
				g2.draw(new Line2D.Float(cl.obterX() - 10, cl.obterY() + 20, cl.obterX(), cl.obterY()));
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

	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
