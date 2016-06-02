package Integrador;

public class Main {

	private static Janela janela;
	public static int tamanho = 1;

	public static void main(String[] args) {
		janela = new Janela(1024, 576);
		janela.setTitle("Projeto Integrador - Angry Birds");
		janela.changerFond(new Menu());

	}

	public static Janela obterJanela() {
		return janela;

	}
}
