package Integrador;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Animacao {

	private Cenario cen;
	double tempoDelay;

	double velocidade, angulo;

	public Animacao(Passaro pass, Cenario d1, double veloc, double ang) {
		cen = d1;
		velocidade = veloc;
		angulo = ang;
		trajetoriaParabola(pass);
	}

	public void trajetoriaParabola(final Passaro o) {
		tempoDelay = 0;
		if (angulo < 91 && angulo > 89)
			angulo = (new Random().nextBoolean()) ? 91 : 89;
		final Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				tempoDelay += 0.013;
				o.definirCordenada(cordenadaParabola(tempoDelay, velocidade, angulo, 0, cen.obterAlturaLancamento()));
				double t2 = tempoDelay + 0.002;
				o.alterarProximaCordenada(cordenadaParabola(t2, velocidade, angulo, 0, cen.obterAlturaLancamento()));
				while (o.obterCordenada().distancia(o.obterProximaCordenada()) < o.obterTamanho() / 2 + 10) {
					t2 += 0.002;
					o.alterarProximaCordenada(cordenadaParabola(t2, velocidade, angulo, 0, cen.obterAlturaLancamento()));
				}
				cen.repaint();

			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 1);
	}

	static Cordenada cordenadaParabola(double t, double velocidade, double angulo, int posDepX, int posDepY) {
		double rad = Math.toRadians(angulo);
		double x = velocidade * Math.cos(rad) * t + posDepX;

		double truc = x / (velocidade * Math.cos(rad));
		final double G = 9.81;

		double y = (-(G / 2)) * truc * truc + velocidade * Math.sin(rad) * truc + posDepY;
		return new Cordenada((int) x, (int) y);

	}

}
