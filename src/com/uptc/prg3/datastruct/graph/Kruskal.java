package grafos;

import java.util.Comparator;

import dinamic.Node;
import dinamic.SimpleList;
import test.MinHeap;

public class Kruskal<T,L,K> extends Graph<T, L, K>{

	public Kruskal(Comparator<Vertex<T, L>> comparador) {
		super(comparador);
	}

	//metodo que usare para que me de pues la solcion
	public void kruskalMethod() {
		Node<Vertex<T, L>> ver = list.getHead();
		Node<Edge<T, L>> node = list.getHead().getInfo().getEdges().getHead();
		while (ver != null) {
			kruskalHelp(ver);
			ver = ver.getNext();
		}
	}

	//me ayuda para sacar todas las distancias que existen y las agrego a una lista
	private SimpleList<Vertex<T, L>> kruskalHelp(Node<Vertex<T, L>> node) {
		Node<Edge<T, L>> help = node.getInfo().getEdges().getHead();
		SimpleList<Vertex<T, L>> ver = new SimpleList<>();
		MinHeap<Edge<T, L>> ar = new MinHeap<>(new Comparator<Edge<T,L>>() {
		
			@Override
			public int compare(Edge<T, L> o1, Edge<T, L> o2) {

				return Integer.parseInt(o1.label.toString())- Integer.parseInt(o2.label.toString());
			}
		});
		while (help != null) {
			ar.insert(help.getInfo());
			help = help.getNext();
		}
//		this.search(ar.getMin());
		ver.add(ar.getMin().getTarget());
		return ver;
//		FinalizarMetodo(ar);
	}
}
