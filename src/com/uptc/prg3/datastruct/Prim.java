package com.uptc.prg3.datastruct;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim<T,L> extends MyGraph<T,L>{
	
	public MyGraph<T,L> getTreePrimQueue(Vertex<T,L> startVertex, boolean isDirected, Comparator<L> labelComparator){
		MyGraph<T,L> graph = new MyGraph<>();
		PriorityQueue<Edge<T,L>> cola = new PriorityQueue<>(new Comparator<Edge<T,L>>() {
			@Override
			public int compare(Edge<T, L> o1, Edge<T, L> o2) {
				return labelComparator.compare(o1.label, o2.label);
			}
		});
		
		SimpleList<Vertex<T,L>> visitedVerticesL = new SimpleList<>();
		do{
			if(!visitedVerticesL.isIn(startVertex)) visitedVerticesL.add(startVertex);
			Cursor<Edge<T,L>> cursorEdges = new Cursor<Edge<T,L>>(startVertex.edges);
			while (!cursorEdges.isLast()) {
				Edge<T,L> currentEdge = cursorEdges.next();
				if (!(visitedVerticesL.isIn(startVertex) && visitedVerticesL.isIn(currentEdge.target)) && !cola.contains(currentEdge)) {
                	cola.offer(currentEdge);
                }
			}

			Edge<T,L> minEdge = cola.poll();
			Vertex<T,L> parentEdge = searchEdgeParentVertex(minEdge); 
			if (!(visitedVerticesL.isIn(parentEdge) && visitedVerticesL.isIn(minEdge.target))){
				if(!isDirected) graph.add(minEdge.target.info, parentEdge.info, minEdge.label);
				graph.add(parentEdge.info, minEdge.target.info, minEdge.label);
				startVertex = minEdge.target;
			}
		}while(!cola.isEmpty());
		return graph;
	}
	
	protected Vertex<T,L> searchEdgeParentVertex(Edge<T,L> edge){
		Cursor<Vertex<T,L>> cursorVertices = new Cursor<>(getVertexs());
		while (!cursorVertices.isLast()) {
			Vertex<T,L> currentVertex = cursorVertices.next();
			Cursor<Edge<T,L>> cursorEdges = new Cursor<>(currentVertex.edges);
			while (!cursorEdges.isLast()) {
				if(edge == cursorEdges.next()){
					return currentVertex;
				}
			}
		}
		return null;
	}
}
