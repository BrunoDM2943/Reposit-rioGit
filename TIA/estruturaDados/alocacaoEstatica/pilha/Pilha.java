package alocacaoEstatica.pilha;

public class Pilha {
	private int dados[];
	private int topo;
	
	public Pilha(int capacidade){
		dados = new int[capacidade];
		topo = -1;
	}
	
	public void push(int e){
		if (cheia()){
			System.out.println("A pilha esta cheia.");
		}else{
			topo++;
			dados[topo] = e;
		}
	}
	
	public int pop(){
		if(vazia())
			return -1;
		int deletado = dados[topo];
		topo--;
		return deletado;
	}
	
	public int getInicio(){
		if(vazia())
			return -1;
		return dados[0];
	}
	
	public int getTopo(){
		if(vazia())
			return -1;
		return dados[topo];
	}
	
	public void show(){
		if(vazia())
			System.out.println("A pilha esta vazia.");
		for(int i =0;i<=topo;i++)
			System.out.println(dados[i]);
		System.out.println("--------");
	}
	
	private boolean cheia(){
		if (topo == dados.length -1)
			return true;
		return false;
	}

	private boolean vazia(){
		if(topo == -1)
			return true;
		return false;
	}
	

}
