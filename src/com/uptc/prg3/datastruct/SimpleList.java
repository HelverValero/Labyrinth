package com.uptc.prg3.datastruct;

import com.uptc.prg3.datastruct.SimpleNode;
import java.util.Comparator;

public class SimpleList<T> {
	protected SimpleNode<T> head;
	private Comparator<T> comparator;

	public SimpleList() {
		this.head = null;
		this.comparator = null;
	}

	public SimpleList(Comparator<T> comparator) {
		this.head = null;
		this.comparator = comparator;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public SimpleNode<T> getHead() {
		return head;
	}

	public void setHead(SimpleNode<T> head) {
		this.head = head;
	}

	/**
	 * Agrega al final de la lista...
	 * @param t 
	 */
	 public void add(T t) {
		 if (head == null) {
			 head = new SimpleNode<>(t);
		 } else {
			 SimpleNode<T> aux = head;
			 while (aux.getNext() != null) {
				 aux = aux.getNext();
			 }
			 aux.setNext(new SimpleNode<T>(t));
		 }
	 }

	 public void insert(T t) {
		 head = new SimpleNode<>(t, head);
	 }

	 public SimpleNode<T> search(T info){
		 SimpleNode<T> nodeAux = null;
		 if( head != null){
			 SimpleNode<T> node = this.head;
			 while(node.getNext() != null){
				 if(comparator.compare(node.getInfo(),info) == 0){
					 nodeAux = node;
				 }
				 node = node.next;
			 }
		 }
		 return nodeAux;
	 }



	 public void eraseElement(T info){
		 if(head != null){
			 SimpleNode<T> node = this.head;
			 while(node.getNext() != null){
				 if(comparator.compare(node.getInfo(), info) == 0){
					 node.setInfo(node.next.getInfo());
					 node.setNext(node.next.next);
				 }
				 node = node.next;
			 }
		 }
	 }

	 public void addSort(T info) {
		 SimpleNode<T> temporal = new SimpleNode<T>(info);
		 if (isEmpty()) {
			 this.head = temporal;
		 } else {
			 SimpleNode<T> node = this.head;
			 SimpleNode<T> node1 = node.getNext();
			 while (node1 != null && (comparator.compare(temporal.getInfo(), node1.getInfo())) < 0) {
				 node = node1;
				 node1 = node1.getNext();
			 }
			 if (node == this.head && (comparator.compare(node.getInfo(), temporal.getInfo())) < 0) {
				 temporal.setNext(head);
				 this.head = temporal;
			 } else {
				 temporal.setNext(node.getNext());
				 node.setNext(temporal);
			 }
		 }
	 }

	 public void insertAt(T info, int index) {
		 SimpleNode<T> insertion = new SimpleNode<T>(info);
		 SimpleNode<T> initial = this.head;
		 SimpleNode<T> previous = null;

		 for (int i = 1; i < index; i++) {
			 previous = initial;
			 initial = initial.getNext();
		 }

		 if (initial == this.head) {
			 insertion.setNext(head);
			 head = insertion;
		 } else {
			 previous.setNext(insertion);
			 insertion.setNext(initial);
		 }
	 }

	 public void eraseAt(int index) {
		 SimpleNode<T> aux = null;
		 SimpleNode<T> initial = this.head;
		 SimpleNode<T> next = initial.getNext();
		 for (int i = 0; i < index; i++) {
			 aux = initial;
			 initial = next;
			 next = next.getNext();
		 }
		 if (initial == this.head) {
			 this.head = next;
		 } else {
			 aux.setNext(next);
		 }
	 }

	 public void addInOrder(T inf) {
		 SimpleNode<T> tmp = new SimpleNode<T>(inf);
		 tmp.setInfo(inf);
		 if (head == null) {
			 head = tmp;

		 } else {
			 SimpleNode<T> aux = head.getNext();
			 SimpleNode<T> aux1 = head;
			 while (aux != null
					 && (comparator.compare(tmp.getInfo(), aux.getInfo())) < 0) {
				 aux1 = aux;
				 aux = aux.getNext();
			 }
			 if (aux1 == head
					 && (comparator.compare(aux1.getInfo(), tmp.getInfo())) < 0) {
				 tmp.setNext(head);
				 head = tmp;
			 } else {
				 tmp.setNext(aux1.getNext());
				 aux1.setNext(tmp);

			 }
		 }
	 }


	 public void addSortz(T info){
		 //       if (head==null) {
		 //          this.head = new SimpleNode(info);
		 //       } 
		 //       else {
		 SimpleNode<T> node = this.head;
		 SimpleNode <T> ant = node;
		 while (node!=null && this.comparator.compare(node.info, info)<0) {
			 ant = node;
			 node = node.next; 
		 }
		 if (ant==node) {
			 this.head = new SimpleNode<T>(info, ant);
		 }
		 else {
			 ant.next = new SimpleNode<T>(info, node); 
		 }
		 //       }
	 }


	 public boolean isIn(T info) {
		 SimpleNode<T> simpleNode = this.head;
		 while (simpleNode != null && this.comparator.compare(simpleNode.info, info)!=0) {
			 simpleNode = simpleNode.next;
		 }
		 return (simpleNode != null && this.comparator.compare(simpleNode.info, info)==0);
	 }

	 public T find(T info) {
		 SimpleNode<T> simpleNode = this.head;
		 while (simpleNode != null && this.comparator.compare(simpleNode.info, info)!=0) {
			 simpleNode = simpleNode.next;
		 }
		 return simpleNode!=null?simpleNode.info:null;
	 }

	 public int length(){
		 int count = 0;
		 SimpleNode<T> aux = head;
		 while(aux != null){
			 count++;
			 aux = aux.getNext();
		 }

		 return count;
	 }

	 public boolean isHear(T info) {
		 boolean value = false;
		 if( head != null){
			 SimpleNode<T> node = this.head;
			 while(node.getNext() != null){
				 if(comparator.compare(node.getInfo(),info) == 0){
					 value = true;
				 }
				 node = node.next;
			 }
		 }
		 return value;
	 }

}
