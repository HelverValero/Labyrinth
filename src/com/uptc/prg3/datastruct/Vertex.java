package com.uptc.prg3.datastruct;


public class Vertex <T,L> {
    protected T info;
    protected SimpleList <Edge<T,L>> edges;

    public Vertex(T info) {
        this.info = info;
        this.edges = new SimpleList<>();
    }
    
    public void add(Edge<T, L> info){
		edges.add(info);
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public SimpleList<Edge<T, L>> getEdges() {
		return edges;
	}

	public void setEdges(SimpleList<Edge<T, L>> edges) {
		this.edges = edges;
	}

	public boolean isEmptyHead(){
		return edges.isEmpty();
	}
  
}
