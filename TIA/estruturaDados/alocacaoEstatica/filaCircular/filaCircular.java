package alocacaoEstatica.filaCircular;

public class filaCircular {
	private int dados[];
	private int tamanho;
	private int inicio;
	private int fim;

	public filaCircular(int capacidade) {
		dados = new int[capacidade];
		tamanho = 0;
		inicio = 0;
		fim = 0;
	}

	public void add(int e) {
		if (cheia()) {
			System.out.println("A fila esta cheia");
		} else {
			dados[fim] = e;
			tamanho++;
			fim++;
			if (fim == dados.length)
				fim = 0;
		}
	}

	public int remove() {
		if (vazia())
			return -1;
		int deletado = dados[inicio];
		tamanho--;
		inicio++;
		if (fim == dados.length)
			fim = 0;
		if (inicio == dados.length)
			inicio = 0;
		return deletado;
	}

	public int getInicio() {
		if (vazia())
			return -1;
		return dados[inicio];
	}

	public int getFim() {
		if (vazia())
			return -1;
		if (fim == 0)
			return dados[dados.length - 1];
		return dados[fim - 1];
	}

	public void show() {
		int i = inicio;

		do{			
			if (i == dados.length) {
				i=0;				
			} else {
				System.out.println(dados[i]);
				i++;
			}
		}while(i != fim);
		System.out.println("---------");
	}

	private boolean cheia() {
		if (tamanho == dados.length)
			return true;
		return false;
	}

	private boolean vazia() {
		if (tamanho == 0)
			return true;
		return false;
	}
}
