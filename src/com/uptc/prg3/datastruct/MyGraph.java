package com.uptc.prg3.datastruct;

import java.util.Comparator;

import com.uptc.prg3.datastruct.others.Queue;


public class MyGraph <T,L> {

	private SimpleList <Vertex <T,L>> vertexs; 
	private Comparator<Vertex<T, L>> comparador;

	public MyGraph(Comparator<Vertex<T, L>> comparador) {
		vertexs = new SimpleList<>(comparador);
		this.comparador = comparador;
	}

	public SimpleList<Vertex<T, L>> getVertexs() {
		return vertexs;
	}

	public void add(T info){
		vertexs.add(new Vertex<>(info));
	}
	
	/**
	 * Agrega un vector asignandole un objetivo y una marca
	 * @param home = vector inicial
	 * @param target = vector objetivo
	 * @param label = marca
	 */
	public void add(T home, T target, L label){
		Vertex<T, L> vertexHomeAux = new Vertex<T, L>(home);
		Vertex<T, L> vertexTargetAux = new Vertex<T, L>(target);
		if(vertexs.isHear(vertexHomeAux) == true){
			if(vertexs.isHear(vertexTargetAux) == true){
				save(vertexs.search(vertexHomeAux).getInfo(), vertexs.search(vertexTargetAux).getInfo(), label);
			}else{
				add(target);
				save(vertexs.search(vertexHomeAux).getInfo(), vertexs.search(vertexTargetAux).getInfo(), label);
			}
		}else{
			add(home);
			if(vertexs.isHear(vertexTargetAux) == true){
				save(vertexs.search(vertexHomeAux).getInfo(), vertexs.search(vertexTargetAux).getInfo(), label);
			}else{
				vertexs.add(vertexTargetAux);
				save(vertexs.search(vertexHomeAux).getInfo(), vertexs.search(vertexTargetAux).getInfo(), label);
			}
		}
	}
	
	/**
	 * Ayuda al metodo de add
	 * @param vertexHome
	 * @param vertexTarget
	 * @param label
	 */
	public void save(Vertex<T, L> vertexHome, Vertex<T, L> vertexTarget, L label){
		vertexHome.add(new Edge<>(label,vertexTarget));
	}
	
	/**
	 * Recorre el arbol en anchura para obtener el camino de un laberinto
	 * @param home = inicio
	 * @param target = final
	 * @return
	 */
	public SimpleList <Vertex<T,L>> amp(T home, T target){
		SimpleList<Vertex<T, L>> paths = new SimpleList<Vertex<T, L>>(comparador);
		Queue<Vertex<T, L>> listPathAux = new Queue<>();
		Vertex<T, L> aux = searchThePenultimate(search(target));
		helperOnlyPath(search(home), search(target), paths, listPathAux);
		boolean value = verifyInfo(paths, target, aux.getInfo()); 
		while(value == true){
			value = verifyInfo(paths, target, aux.getInfo()); 
		}

		return paths;
		//to do ..
	} 
	
	/**
	 * Busca el dato info en los vertices
	 * @param info = dato a buscar
	 * @return
	 */
	public Vertex<T, L> search(T info){
		SimpleNode<Vertex<T, L>> aux = vertexs.getHead();
		while(aux != null){
			if(aux.getInfo().getInfo().equals(info)){
				return aux.getInfo();
			}
			aux = aux.getNext();
		}
		return null;
	}
	
	/**
	 * Verifica si los datos en la lista final son correctos o lo lleva
	 * a un callejon sin salida
	 * @param paths = lista de caminos
	 * @param target = obejtivo
	 * @param ant = anterior al objetivo
	 * @return
	 */
	private boolean verifyInfo(SimpleList<Vertex<T, L>> paths, T target, T ant) {
		boolean isChange = false;
		SimpleNode<Vertex<T, L>> aux = paths.getHead();
		while(aux != null){
			if(aux.getInfo().getEdges().length() == 1){
				if(!aux.getInfo().getInfo().equals(ant)){
					SimpleNode<Vertex<T,L>> auxDelete = paths.search(aux.getInfo().getEdges().getHead().getInfo().target);
					if(auxDelete == null){
						isChange = true;
						paths.eraseElement(aux.getInfo());
					}
				}
			}else if(aux.getInfo().getEdges().length() > 1){
				SimpleNode<Edge<T, L>> aux2 = aux.getInfo().getEdges().getHead();
				boolean[] isDeleter = new boolean[aux.getInfo().getEdges().length()];
				int pos = 0;
				while(aux2 != null){
					if(paths.search(aux2.getInfo().target) == null){
						isDeleter[pos] = true;
					}
					pos++;
					aux2 = aux2.getNext();
				}
				
				if(isDeleter[0] == true && isDeleter[1] == true){
					paths.eraseElement(aux.getInfo());
				}else if(isDeleter[0] == false && isDeleter[1] == false){
					Vertex<T, L> auxD1 = aux.getInfo().getEdges().getHead().getInfo().target;
					Vertex<T, L> auxD2 = aux.getInfo().getEdges().getHead().getNext().getInfo().target;
					if(paths.search(auxD1.getEdges().getHead().getInfo().target) == null){
						isChange = true;
						paths.eraseElement(auxD1);
					}else if(paths.search(auxD2.getEdges().getHead().getInfo().target) == null){
						isChange = true;
						paths.eraseElement(auxD2);
					}
				}
			}
			aux = aux.getNext();
		}
		return isChange;
	}
	
	/**
	 * Ayuda a recorrer el grafo para encontrar el camino de un laberinto
	 * @param home = inicio
	 * @param target = final
	 * @param paths = caminos tomados
	 * @param listQUeue = lista de Vectores
	 */
	private void helperOnlyPath(Vertex<T, L> home, Vertex<T, L> target, SimpleList<Vertex<T, L>> paths, Queue<Vertex<T, L>> listQUeue) {
		if(home != null && target != null){
			if(isPath(home, target) == true){
				paths.add(home);
			}
			SimpleNode<Edge<T, L>> node = home.getEdges().getHead();
			if(home.isEmptyHead() != true){
				while(node != null){
					listQUeue.put(node.getInfo().target);
					node = node.getNext();
				}
				helperOnlyPath(listQUeue.get(), target, paths, listQUeue);
			}else{
				if(comparador.compare(home, target) != 0){
					helperOnlyPath(listQUeue.get(), target, paths, listQUeue);	
				}else if(comparador.compare(home, target) == 0){
					paths.add(target);
				}
			}
		}else{
			System.out.println("No se encuentran dichos inicio y final");
		}
	}
	
	/**
	 * Nos retorna si un camino tiene salida o no
	 * @param aux = vector
	 * @param target = objetivo
	 * @return
	 */
	public boolean isPath(Vertex<T, L> aux, Vertex<T, L> target){
		SimpleNode<Edge<T, L>> aux2 = aux.getEdges().getHead();
		while(aux2 != null){
			if(aux2.getInfo().target != null){
				return true;
			}else if(aux.getEdges().length() >1){
				return true;
			}else if(comparador.compare(aux, target)== 0){
				return true;
			}else{
				isPath(aux2.getInfo().target, target);
			}
			aux2 = aux2.getNext();
		}
		return false;
	}
	
	/**
	 * Busca el penultimo para evitar errores en clases dinamicas
	 * @param target
	 * @return
	 */
	public Vertex<T, L> searchThePenultimate(Vertex<T, L> target){
		Vertex<T, L> aux = new Vertex<T,L>(target.getInfo());
		SimpleNode<Vertex<T, L>> head = vertexs.getHead();
		while(head.getNext() != null){
			SimpleNode<Edge<T, L>> aux2 = head.getInfo().getEdges().getHead();
			while(aux2 != null){
				if(!aux2.getInfo().isEmpty()){
					if(comparador.compare(aux2.getInfo().target, target) == 0){
						aux = head.getInfo();
					}
				}
				aux2 = aux2.getNext();
			}

			head = head.getNext();
		}

		return aux;
	}
	
	
	//    
	//    public SimpleList<T>  diskjtra(Vertex vertex){
	//       return null;
	//    
	//    }
}
