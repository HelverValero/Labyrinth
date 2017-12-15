package com.uptc.prg3.datastruct;

import java.util.Comparator;

import com.uptc.prg3.datastruct.others.Queue;


public class BTSAlgorythm<V,K> extends AdjacencyMatrix<V, K>{
	
	private V[] listVertex;
	private K[][] matrixEdges;
	private K empty;  

	public BTSAlgorythm(V[] vertex, K[][] edges) {
		super(vertex, edges);
		this.listVertex = vertex;
		this.matrixEdges = edges;
		
	}
	
	public SimpleList<V> isPathLab(V vertex, V target){
		Comparator<V> comparador = new Comparator<V>() {

			@Override
			public int compare(V arg0, V arg1) {
				if(arg0.equals(arg1) ){
					return 0;
				}else{
					return 1;
				}
			}
		};
		SimpleList<V> recorridos = new SimpleList<V>(comparador);
		recorridos.add(vertex);
		Queue<V> cola = new Queue<V>();
		int pos = search(vertex);
		for (int i = pos; i < listVertex.length; i++) {
			for (int j = 0; j < matrixEdges[0].length; j++) {
				if(!matrixEdges[i][j].equals(empty)){
					cola.put(searchByValue(j));
				}
			}
		}
		helperOnlyPath(cola.get(), target, cola, recorridos);
		verifyPath(recorridos, target);
		return recorridos;
	}

	private boolean verifyPath(SimpleList<V> recorridos, V target){
		boolean isChange = false;
		SimpleNode<V> aux = recorridos.getHead();
		while(aux != null){
			int posVector = search(aux.getInfo());
			if(aux.getNext() != null){
				int posVector2 = search(aux.getNext().getInfo());
				if(matrixEdges[posVector][posVector2] == empty){
					recorridos.eraseElement(searchByValue(posVector));    
				}
			}
			aux = aux.getNext();
		}
		return isChange;
	}

	private void helperOnlyPath(V aux, V target, Queue<V> cola, SimpleList<V> recorridos) {
		System.out.println("hola");
		if(aux != null && target != null){
			if(isPath(aux, target) == true){
				recorridos.add(aux);
			}
			if(gradeVecter(aux) < 1){
				helperOnlyPath(cola.get(), target, cola, recorridos);
			}else{
				if(matrixEdges[search(aux)][search(target)].equals(empty)){
					helperOnlyPath(cola.get(), target, cola, recorridos);	
				}else if(!matrixEdges[search(aux)][search(target)].equals(empty)){
					recorridos.add(target);
				}
			}
		}else{
			System.out.println("No se encuentran dichos inicio y final");
		}

	}

	public boolean isPath(V aux, V target){
		for (int i = 0; i < matrixEdges.length; i++) {
			if(!matrixEdges[search(aux)][i].equals(empty)){
				if(matrixEdges[search(aux)][i] != empty){
					return true;
				}else if(gradeVecter(searchByValue(i)) >1){
					return true;
				}else if(matrixEdges[search(aux)][search(target)] != empty){
					return true;
				}
			}
		}
		return false;
	}

}
