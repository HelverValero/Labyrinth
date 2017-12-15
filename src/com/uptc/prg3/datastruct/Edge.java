package com.uptc.prg3.datastruct;


public class Edge <T,L> {
	
   protected L label;
   protected Vertex <T,L> target;

    public Edge(L label, Vertex<T, L> target) {
        this.label = label;
        this.target = target;
    }
    
    public Vertex<T, L> getTarget() {
		return target;
	}

	public void setTarget(Vertex<T, L> target) {
		this.target = target;
	}

	
	public boolean isEmpty(){
		if(target == null){
			return true;
		}else{
			return false;
		}
	}
}
