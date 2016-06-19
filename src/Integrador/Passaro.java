package Integrador;

import java.util.Observable;

public class Passaro extends Observable {

	private Cordenada cord, novaCordenada;
	private int tamanho;
	private Cenario cen;
	private boolean noAr = false;
	private boolean noChao;

	public Passaro(Cenario d) {
		cen = d;
		tamanho = 65;
		this.cord = new Cordenada(-tamanho / 2 - 40 * cen.obterPassaro().size(), tamanho / 2);
		novaCordenada = new Cordenada(-tamanho / 2 - 20 * cen.obterPassaro().size(), tamanho / 2);
		noChao = false;
	}

	public void definirX(int i) {
		cord.alterarX(i);
	}

	public int obterX() {
		return cord.obterX();
	}

	public void definirY(int i) {
		cord.alterarY(i);
	}

	public int obterY() {
		return cord.obterY();
	}

	public Cordenada obterCordenada() {
		return cord;
	}

	public void definirCordenada(Cordenada c) {
		this.cord = c;
	}

	public Cordenada obterProximaCordenada() {
		return this.novaCordenada;
	}

	public void alterarProximaCordenada(Cordenada c) {
		novaCordenada = c;
	}

	public int obterTamanho() {
		return tamanho;
	}

	public boolean inicioVoo() {
		return noAr;
	}

	public void voar() {
		noAr = true;
	}

	public boolean fimVoo() {
		return noChao;
	}

	public void finalVoo() {
		noChao = true;
		noAr = false;
	}

	public void localLancamento() {
		this.definirCordenada(new Cordenada(0, cen.obterAlturaLancamento() + tamanho / 2));
		this.alterarProximaCordenada(new Cordenada(20, cen.obterAlturaLancamento() + tamanho / 2));
	}
}
