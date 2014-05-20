package alocacaoDinamica.fila;


public class Fila {
	private No inicio;
	private final String vazia = "A lista esta vazia";
	public Fila() {
		inicio = null;
	}
	

	/**
	 * Adiciona um elemento no final da fila 
	 * @param e Objeto a ser adicionado
	 * @since 12/04/2014
	 */
	public void add(Object e) {
		if(!isEmpty()) {
			No aux = inicio;
			while(aux.prox != null) {
				aux = aux.prox;
			}
			aux.prox = new No(e);
		}else {
			inicio = new No(e);
		}
	}
	
	/**
	 * Remove um elemento do início da fila
	 * @return Objeto removido
	 * @since 12/04/2014
	 */
	public Object rem() {
		Object removed = -1;
		if(!isEmpty()) {
			removed = inicio.dado;
			inicio = inicio.prox;			
		}else {
			System.err.println(vazia);
		}
		return removed;
	}
	
	/**
	 * Retorna o primeiro elemento da fila
	 * @return Primeiro elemento ou -1
	 * @since 12/04/2014
	 */
	public Object getInicio() {
		if(!isEmpty())
			return inicio.dado;
		System.err.println(vazia);
		return -1;
	}
	
	/**
	 * Retorna o ultimo elemento da fila
	 * @return Ultimo elemento ou -1
	 */
	public Object getFim() {
		if(!isEmpty()) {
			No aux = inicio;
			while(aux.prox != null) {
				aux = aux.prox;
			}
			return aux.dado;
		}else {
			System.err.println(vazia);
			return -1;
		}
	}
	
	/**
	 * Mostra todos os elementos da fila
	 * @since 12/04/2014
	 */
	public void show() {
		if(!isEmpty()) {
			No aux  = inicio;
			while(aux != null) {
				System.out.println(aux.dado);
				aux = aux.prox;
			}
		}else {
			System.err.println(vazia);
		}
	}
	
	/**
	 * Valida se a fila esta vazia
	 * @return False se vazia 
	 * True se carregada
	 * @since 12/04/2014
	 */
	private boolean isEmpty() {
		if(inicio == null)
			return true;
		return false;
	}
	
}
