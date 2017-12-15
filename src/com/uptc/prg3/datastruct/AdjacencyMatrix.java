package com.uptc.prg3.datastruct;


public abstract class AdjacencyMatrix <V,K>{
	private V[] listVertex;
	private K[][] matrixEdges;
	private K empty;  

	public AdjacencyMatrix(V[] vertex, K[][] edges) {
		this.listVertex = vertex;
		this.matrixEdges = edges;
	}

	public void setEmpty(K empty){
		this.empty = empty;
	}

	//    public boolean isDirected(Comparator<E> comparator){
	//      // to do
	//      return false;
	//    }
	//    
	public int gradeVecter(V vertex){
		int value= 0;
		for (int i = 0; i < listVertex.length; i++) {
			if(listVertex[i].equals(vertex)){
				value = i;
			}
		}
		return helperVecter(value);
	}
	
	public int helperVecter(int index){
		int count = 0;
		for (int i = 0; i < listVertex.length; i++) {
			if(matrixEdges[index][i].equals(empty) != true){
				count++;
			}
		}
		return count;
	}
	//
	//    public int gradeVertex(V vertex){
	//        // todo 
	//        // buscar el index de vertex y aplicar anterior..   
	//     return 0;
	//    }
	//    
	//    public int  getCountPaths(int indexHome, int indexTarget){
	//      // to do 
	//      return 0;
	//    }
	//    /**
	//     * Retorna la lista de index de caminos potenciales ente un origen y un destino
	//     * @param indexHome
	//     * @param indexTarget
	//     * @return 
	//     */
	//    public SimpleList<SimpleList <Integer>> getPaths(int indexHome, int indexTarget){
	//      // to do 
	//      return null;
	//    }
	//
	//    public SimpleList<SimpleList <V>> getPaths(V home, V target){
	//      // to do  antes hay que buscar el index de cada v...
	//      return null;
	//    }
	//
	//    /**
	//     * Cual(es) es/son los caminos mas cortos....
	//     * @param home
	//     * @param target
	//     * @return 
	//     */
	//    public SimpleList<SimpleList <V>> getShortPaths(V home, V target){
	//      // to do ..
	//      return null;
	//    }

	protected int search(V vertex) {
		int value = 0;
		for (int i = 0; i < listVertex.length; i++) {
			if(listVertex[i].equals(vertex)){
				value = i;
			}
		}
		return value;
	}

	protected V searchByValue(int pos) {
		return listVertex[pos];
	}


}
