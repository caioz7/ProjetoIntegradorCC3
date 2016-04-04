package Integrador;

import java.util.Timer;
import java.util.TimerTask;

public class objetoLancado {

	private Cenario cen;
	double t;

	double velocidade, angulo;

	public objetoLancado(Passaro o, Cenario d1, double vel, double ang) {
		cen = d1;
		velocidade = vel;
		angulo = ang;
		lancarObjeto(o);
	}

	public void lancarObjeto(final Passaro o) {
		t = 0;
		final Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				t += 1;
				if (o.obterCordenada()
						.distancia(new Cordenada(0, cen.obterAlturaLancamento() + o.obterTamanho() / 2)) > 0) {
					o.definirCordenada(prochCoordDroite((int) t, o.obterCordenada(),
							new Cordenada(0, cen.obterAlturaLancamento() + o.obterTamanho() / 2)));
					o.alterarProximaCordenada(prochCoordDroite((int) t + 10, o.obterCordenada(),
							new Cordenada(0, cen.obterAlturaLancamento() + o.obterTamanho() / 2)));
					cen.repaint();
				} else {
					timer.cancel();
					new Animacao(o, cen, velocidade, angulo);
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 5);
	}

	public static Cordenada prochCoordDroite(int t, Cordenada dep, Cordenada arr) {
		Cordenada p = arr;

		while (p.distancia(dep) > t) {
			if (Math.abs(p.obterX() - dep.obterX()) > Math.abs(p.obterY() - dep.obterY())) {
				if (p.obterX() > dep.obterX())
					p.alterarX(p.obterX() - 1);
				else
					p.alterarX(p.obterX() + 1);
			} else {
				if (p.obterY() > dep.obterY())
					p.alterarY(p.obterY() - 1);
				else
					p.alterarY(p.obterY() + 1);
			}
		}
		return p;

	}

}
