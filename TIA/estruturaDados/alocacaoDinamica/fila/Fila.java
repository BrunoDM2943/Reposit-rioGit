package alocacaoDinamica.fila;

import java.util.Iterator;


public class Fila<T> implements Iterable<T> {
	private No<T> inicio;
	private final String vazia = "A lista esta vazia";
	public int size = 0;
	public Fila() {
		inicio = null;
	}
	

	/**
	 * Adiciona um elemento no final da fila 
	 * @param e Objeto a ser adicionado
	 * @since 12/04/2014
	 */
	public void add(T e) {
		if(!isEmpty()) {
			No<T> aux = inicio;
			while(aux.prox != null) {
				aux = aux.prox;
			}
			aux.prox = new No<T>(e);
			size++;
		}else {
			inicio = new No<T>(e);
			size++;
		}
	}
	
	/**
	 * Remove um elemento do início da fila
	 * @return Objeto removido
	 * @since 12/04/2014
	 */
	public T rem() {
		T removed = null;
		if(!isEmpty()) {
			size--;
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
	public T getInicio() {
		if(!isEmpty())
			return inicio.dado;
		System.err.println(vazia);
		return null;
	}
	
	/**
	 * Retorna o ultimo elemento da fila
	 * @return Ultimo elemento ou -1
	 */
	public T getFim() {
		if(!isEmpty()) {
			No<T> aux = inicio;
			while(aux.prox != null) {
				aux = aux.prox;
			}
			return aux.dado;
		}else {
			System.err.println(vazia);
			return null;
		}
	}
	
	/**
	 * Mostra todos os elementos da fila
	 * @since 12/04/2014
	 */
	public void show() {
		if(!isEmpty()) {
			No<T> aux  = inicio;
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


	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
