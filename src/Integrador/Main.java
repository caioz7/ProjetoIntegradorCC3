package Integrador;

public class Main {

	private static Janela janela;
<<<<<<< HEAD
	public static final int tamanho = 100;
=======
	public static int tamanho = 100;
>>>>>>> origin/master

	public static void main(String[] args) {
		janela = new Janela(1400, 600);
		janela.setTitle("Projeto Integrador - Angry Birds");
		janela.changerFond(new Menu());

	}

	public static Janela obterJanela() {
		return janela;

	}
}
