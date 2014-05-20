package alocacaoDinamica.listaEncadeada;

import java.util.Iterator;

import com.tia.model.Curso;
import com.tia.model.Professor;
import com.tia.model.Sala;



public class ListaEncadeada<T> implements Iterator<T>{

	private No<T> inicio;
	private int tamanho;
	private int chave;
	private T next;

	public ListaEncadeada() {
		inicio = null;
	}

	public void addFim(T e) {
		if (isEmpty()) {
			addInicio(e);
		} else {
			No<T> aux = inicio;
			while (aux.prox != null) {
				aux = aux.prox;
			}
			aux.prox = new No<T>(e);
			this.tamanho++;
		}
		
	}

	public void addInicio(T e) {
		No<T>Novo = new No<T>(e);
		Novo.prox = inicio;
		inicio = Novo;
		this.tamanho++;
	}

	public T getFim() {
		No<T> aux = inicio;
		while (aux.prox != null) {
			aux = aux.prox;
		}
		return aux.dado;
	}

	public T getInicio() {
		return inicio.dado;
	}

	private boolean isEmpty() {
		if (inicio == null)
			return true;
		return false;
	}

	public T removeFim() {
		if (isEmpty())
			return null;
		T deletado;
		No<T>aux = null;
		while (inicio.prox != null) {
			if (aux == null) {
				aux = new No<T>(inicio.dado);
			} else {
				No<T>aux2 = aux;
				while (aux2.prox != null) {
					aux2 = aux2.prox;
				}
				aux2.prox = new No<T>(inicio.dado);
				aux2 = aux;
			}
			inicio = inicio.prox;
		}
		deletado = inicio.dado;
		inicio = aux;
		this.tamanho--;
		return deletado;
	}

	public T removeInicio() {
		if (isEmpty())
			return null;
		T deletado = inicio.dado;
		inicio = inicio.prox;
		this.tamanho--;
		return deletado;
		
	}

	public void show() {
		if (isEmpty()) {
			System.out.println("A Lista esta vazia! \n-----");
		} else {
			No<T> aux = inicio;
			while (aux != null) {
				System.out.println(aux.dado);
				aux = aux.prox;
			}
			System.out.println("-----");
		}
	}
	
	public T getElement(int index) {
	    No<T> aux = inicio;
	    if(index < tamanho) {
		for(int i = 0; i < index; i ++)
		    aux = aux.prox;
		aux.dado.getClass();
		return aux.dado;
	    }else{
		System.err.println("Indice não permitido!");
		return null;
	    }
	}
	

	public Object[] toArray(Class<?> tipo) {	 
	    Object[] array = null;
	    if(tipo.getName().equals(Professor.class.getName()))
	    	array = new Professor[tamanho];
	    if(tipo.getName().equals(Sala.class.getName()))
	    	array = new Sala[tamanho];
	    if(tipo.getName().equals(Curso.class.getName()))
	    	array = new Curso[tamanho];
	    for(int i = 0; i < tamanho; i++)
		array[i] = getElement(i);
	    
	    return array;
	}

	@Override
	public boolean hasNext() {
	    if(getElement(chave) != null) {
		next = getElement(chave);
		chave++;
		return true;
	    }
	    chave = 0;
	    next = null;
	    return false;	    
	}

	@Override
	public T next() {	    
	    return next;	    
	}

	@Override
	public void remove() {
	    // TODO Auto-generated method stub
	    
	}
	
	public Iterator<Integer> iterator() {
	    // TODO Auto-generated method stub
	    return null;
	}
}
