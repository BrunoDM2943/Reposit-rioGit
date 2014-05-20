package alocacaoEstatica.lista;

public class Lista {
	private int dados[];
	private int tamanho;
	
	public Lista(int capacidade){
		dados = new int[capacidade];
		tamanho = 0;		
	}
	
	public void addInicio(int e){
		if(cheia()){
			System.out.println("A lista esta cheia");
		}else{
			for(int i = tamanho; i > 0; i--)
				dados[i] = dados[i-1];
			dados[0] = e;
			tamanho++;
		}
	}

	public void addFim(int e){
		if(cheia()){
			System.out.println("A lista esta cheia");
		}else{
			dados[tamanho] = e;
			tamanho ++;
		}
	}
	
	public void show(){
		if(vazia()){
			System.out.println("A lista esta vazia");
		}else{
			for(int i=0;i<tamanho;i++){
				System.out.println(dados[i]);
			}
			System.out.println("----------");
		}
	}

	public void addPos(int e, int pos) {
		if(cheia()){
			System.out.println("A lista esta cheia");
		} else if(vazia()){
			addInicio(e);
		}else if(pos<0 || pos>dados.length -1){
			System.out.println("Posição inválida.");
		}else{
			for(int i = tamanho; i > pos;i--)
				dados[i] = dados[i -1];
			dados[pos] = e;
			tamanho ++;
		}
		
	}

	public int removeInicio() {
		if(vazia())
			return -1;
		
		int deletado = dados[0];
		for(int i = 0; i < tamanho -1;i++)
			dados[i] = dados[i +1];
		tamanho--;
		return deletado;
	}

	public int removeFim() {
		if(vazia())
			return -1;
		
		int deletado = dados[tamanho-1];
		tamanho--;
		return deletado;
	}

	public int removePos(int pos) {
		if(vazia())
			return -1;
		
		if(pos == 0){
			return removeInicio();
		}else if(pos == tamanho-1){
			return removeFim();
		}else{
			int deletado = dados[pos];
			
			for(int i = pos; i < tamanho - 1; i++)
				dados[i] = dados[i +1];
			tamanho --;
			return deletado;
		}			
	}

	public int getInicio(){
		if(vazia())
			return -1;
		return dados[0];
	}
	
	public int getFim() {
		if(vazia())
			return -1;
		return dados[tamanho-1];
	}

	private boolean cheia(){
		if(tamanho == dados.length)
			return true;
		return false;
	}

	private boolean vazia(){
		if(tamanho == 0)
			return true;
		return false;
	}
	
}
