package com.uptc.prg3.datastruct;

import java.lang.reflect.Array;
import java.util.Comparator;

public class PrimMatrix<V,E> extends AdjacencyMatrix<V, E>{
	
	public PrimMatrix(V[] vertex, E[][] edges, E empty) {
		super(vertex, edges);
		setEmpty(empty);
	}

	public AdjacencyMatrix<V,E> getTreePrim(V startVertex, Comparator<E> labelComparator){
		E[][] matrixA = (E[][]) Array.newInstance(edges[0].getClass(), vertex.length);
		for (int i = 0; i < matrixA.length; i++) {
			matrixA[i] = (E[]) Array.newInstance(edges[0][0].getClass(), vertex.length);
			for (int j = 0; j < matrixA.length; j++) {
				matrixA[i][j] = empty;
			}
		}
		AdjacencyMatrix<V,E> graph = new PrimMatrix<V, E>(vertex, matrixA, empty);
		SimpleList<V> visitedNodes = new SimpleList<>();
		visitedNodes.add(startVertex);
		int visitedVertices = 1;
		int numberOfVertices = vertex.length;
		while (visitedVertices < numberOfVertices) {
			Cursor<V> visitedCursor = new Cursor<>(visitedNodes);
			E minEdge = empty;
			V minTarget = null;
			V minVertex = null;
			while (!visitedCursor.isLast()) {
				V currentVertex = visitedCursor.next();
				E[] edges = this.edges[searchPosEdge(currentVertex)];
				for(int i = 0; i < edges.length;i++) {
					E currEdge = edges[i];
					V currTarget = vertex[i];
					if(!visitedNodes.isIn(currTarget)){
						if(!currEdge.equals(empty)){
							if(minEdge == empty || labelComparator.compare(currEdge, minEdge) <= 0){
								minVertex = currentVertex;
								minTarget = currTarget;
								minEdge = currEdge;
							}
						}
					}
				}
			}
			graph.addRelation(minVertex, minTarget, minEdge);
			graph.addRelation(minTarget, minVertex, minEdge);
			visitedNodes.add(minTarget);
			visitedVertices++;
		}
		return graph;
	}
	
	public int searchPosEdge(V edge){
		int pos = -1;
		for (int i = 0; i < vertex.length; i++) {
			if(edge.equals(vertex[i])){
				pos = i;
				break;
			}
		}
		return pos;
	}
}
