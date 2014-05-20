package alocacaoEstatica.fila;


public class Fila{
	private Object dados[];
	private int tamanho;
	
	public Fila(int capacidade){
		dados = new Object[capacidade];
		tamanho = 0;
	}
	
	public Fila(Object[] vector) {
		dados = new Object[vector.length];
		tamanho=0;
		for(int i = 0; i<vector.length;i++)
			add(vector[i]);
		
	}

	public void add(Object e){
		if(cheia()){
			System.out.println("A fila esta cheia");
		} else {
			dados[tamanho] = e;
			tamanho++;
		}
	}
	
	public Object remove(){
		if(vazia())
			return -1;
		Object deletado = dados[0];
		for(int i=0;i<tamanho -1;i++)
			dados[i] = dados[i + 1];		
		tamanho--;
		return deletado;
	}
	
	public Object getInicio(){
		if (vazia())
			return -1;
		return dados[0];
	}

	public Object getFim(){
		if(vazia())
			return -1;
		return dados[tamanho -1];
	}

	public void show(){
		for(int i=0; i< tamanho;i++)
			System.out.println(dados[i]);
		System.out.println("--------");
	}
	
	private boolean cheia(){
		if (tamanho == dados.length)
			return true;
		return false;
	}

	private boolean vazia(){
		if (tamanho == 0)
			return true;
		return false;
	}

	public boolean hasNext() {
		if(tamanho>0)
			return true;
		return false;
		
	}
}
