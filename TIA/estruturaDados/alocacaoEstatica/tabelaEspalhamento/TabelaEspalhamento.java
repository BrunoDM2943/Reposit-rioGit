package alocacaoEstatica.tabelaEspalhamento;


public class TabelaEspalhamento {
	private int tamanho;
	private Object [] dados;
	
	public TabelaEspalhamento(int tamanho) {
		this.tamanho = tamanho;
		dados = new Object[tamanho];
	}
	
	private int hash (int chave) {
		return chave-1;
	}
	
	
	public void add(int chave , Object valor) {
		dados[hash(chave)] = valor;
	}
	
	public void show() {
		for(int i=0;i<tamanho;i++) {
			System.out.println(toString(i));
		}
	}
	
	public String toString(int i) {
		return "Chave " + i + " Valor " + dados[i];		
	}
}
