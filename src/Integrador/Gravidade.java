package Integrador;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Gravidade {

	Cenario cen;
	JPanel criar;

	public Gravidade(Cenario d) {
		cen = d;
		definirGravidade();
	}

	public Gravidade(Cenario d, JPanel j) {
		cen = d;
		criar = j;
		definirGravidade();
	}

	
	public void definirGravidade() {
		final Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				if (criar != null)
					criar.repaint();
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 2);

	}

}
