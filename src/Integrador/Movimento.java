package Integrador;

public class Movimento {

	private final Cordenada cordenadaOrigem;

	public Movimento(Cordenada cord) {
		this.cordenadaOrigem = cord;
	}
	
	public Cordenada obterCordenadaOrigem() {
		return this.cordenadaOrigem;
	}

	public int obterPosicaoX() {
		return this.cordenadaOrigem.obterX();
	}

	public int obterPosicaoY() {
		return this.cordenadaOrigem.obterY();
	}

	public Cordenada mapaTracado(Cordenada cordTracado) {
		return new Cordenada(cordTracado.obterX() + obterPosicaoX(), obterPosicaoY() - cordTracado.obterY());
	}

	public Cordenada mapaCenario(Cordenada cordCenario) {
		return new Cordenada(cordCenario.obterX() - obterPosicaoX(), obterPosicaoY() - cordCenario.obterY());
	}

}
