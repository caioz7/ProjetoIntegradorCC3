package Integrador;

public class Cordenada {

	private int x, y;

	public Cordenada(int x1, int y1) {
		this.x = x1;
		
		this.y = y1;
	}

	public int obterX() {
		return x;
	}

	public int obterY() {
		return y;
	}

	public void alterarX(int x1) {
		x = x1;
	}

	public void alterarY(int y1) {
		y = y1;
	}

	public boolean comparar(Cordenada c) {
		return this.y == c.obterY() && this.x == c.obterX();
	}

	public int distancia(Cordenada c) {
		int distanciaX = c.obterX() - x;
		if (distanciaX < 0)
			distanciaX *= -1;
		int distanciaY = c.obterY() - y;
		if (distanciaY < 0)
			distanciaY *= -1;
		return (int) Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);
	}

	public Cordenada soma(Cordenada vet) {
		return new Cordenada(x + vet.obterX(), y + vet.obterY());
	}

	public Cordenada inversa() {
		return new Cordenada(x * -1, y * -1);
	}

	public Cordenada diferenca(Cordenada vet) {
		return new Cordenada(x - vet.obterX(), y - vet.obterY());
	}

}
