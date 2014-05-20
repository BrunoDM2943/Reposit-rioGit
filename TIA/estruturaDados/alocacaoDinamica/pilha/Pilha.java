package alocacaoDinamica.pilha;

public class Pilha {

    private No topo;
    private final String erro = "A pilha esta vazia!";
    private int size = 0;

    public Pilha() {
	topo = null;
    }

    /**
     * Empilha um elemento no topo da pilha
     * 
     * @param e
     *            Elemento a ser empilhado
     * @since 12/04/2014
     */
    public void push(Object e) {
	if (!isEmpty()) {
	    No aux = this.topo;
	    while (aux.prox != null) {
		aux = aux.prox;
	    }
	    aux.prox = new No(e);
	} else {
	    this.topo = new No(e);
	}
	size ++;
    }

    /**
     * Desempilha um elemento do topo da pilha
     * 
     * @return Elemento desempilhado
     * @since 12/04/2014
     */
    public Object pop() {
	if (!isEmpty()) {
	    No aux = topo;
	    topo = null;
	    while (aux.prox != null) {
		push(aux.dado);
		aux = aux.prox;
	    }
	    return aux.dado;
	} else {
	    System.err.println(erro);
	    return -1;
	}
	
    }

    /**
     * Mostra todos os elementos da Pilha
     * 
     * @since 12/08/2014
     */
    public void show() {
	if (!isEmpty()) {
	    No aux = topo;
	    while (aux != null) {
		System.out.println(aux.dado);
		aux = aux.prox;
	    }
	} else {
	    System.err.println(erro);
	}
    }

    /**
     * Valida se o topo esta vazia
     * 
     * @return False se vazia True se carregada
     * @since 12/04/2014
     */
    private boolean isEmpty() {
	if (topo == null)
	    return true;
	return false;
    }

    public Object getTopo() {
	if (!isEmpty()) {
	    No aux = topo;
	    while (aux.prox != null) {
		aux = aux.prox;
	    }
	    return aux.dado;

	}
	return -1;
    }
    
    public boolean hasNext() {
	return !isEmpty();
    }
    
    public int size() {
	return this.size;
    }
}
