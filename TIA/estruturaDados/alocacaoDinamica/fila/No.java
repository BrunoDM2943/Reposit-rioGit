package alocacaoDinamica.fila;


public class No<T> {
	
	public T dado;
	public No<T> prox;
	
	public No(T dado) {
		this.dado = dado;
		prox = null;
	}
}
