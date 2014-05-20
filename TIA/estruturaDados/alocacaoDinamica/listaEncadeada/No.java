package alocacaoDinamica.listaEncadeada;

/**
 * @author Bruno
 * @since 18/02/2014
 */
public class No<T> {
	public T dado;
	public No<T> prox;
	
	public No(T e){
		dado = e;
		prox = null;
	}
	
}
