package com.uptc.prg3.datastruct.others;

import java.util.Comparator;

public class Heap <T> {
    protected HeapNode<T> root;
    protected HeapNode<T> last;
    protected Comparator<T> comparator;

    public Heap(Comparator<T> comparator) {
        this.root = null;
        this.last = null;
        this.comparator = comparator;
    }

    public HeapNode<T> getRoot() {
        return root;
    }

    public void setRoot(HeapNode<T> root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
       return this.root==null;
    }
    
    public void put(T info) {
        add(info);
        bubleUp();
    }
    
    private void add(T info) {
        if (this.last != null && this.last.parent != null) {
            if (this.last.parent.right == null) {
                this.last = this.last.parent.setRight(new HeapNode<>(info, this.last.parent));
            } else {
                while (this.last.parent != null && this.last == this.last.parent.right) {
                    this.last = this.last.parent;
                }
                if (this.last != this.root) {
                    this.last = this.last.parent.right;
                }
                while (this.last.left != null) {
                    this.last = last.left;
                }
                this.last = this.last.setLeft(new HeapNode<>(info, this.last));
            }
        } else {
            if (this.last == null) {
                this.last = this.root = new HeapNode(info, null);
            } else {
                this.last = this.last.setLeft(new HeapNode(info, this.last));
            }
        }
    }
    
    public T get(){
      T aux = this.root.info;
      this.root.info = this.last.info;
      deleteLast();
      sinkDown();
     return aux;
    }
    
    private void swap(HeapNode<T> a, HeapNode<T> b) {
        T aux = a.info;
        a.info = b.info;
        b.info = aux;
    }
    
    private void bubleUp(){
      HeapNode<T> heapNode = this.last;
      while (heapNode.parent!=null) {
          if (comparator.compare(heapNode.info,heapNode.parent.info)<0){
              swap(heapNode,heapNode.parent);
          }
          heapNode = heapNode.parent;
      }
    }
    
    private void deleteLast() {
        if (this.last.parent != null) {
            if (this.last.parent.right != null) {
                this.last = this.last.parent.left;
                this.last.parent.right = null;
            } 
            else {
                HeapNode <T> auxLast = last; 
                // up..
                while (this.last.parent != null && this.last.parent.left == this.last) {
                    this.last = this.last.parent;
                }
                if (this.last.parent == null) { // LLEGA A RAIZ POR LEFT...
                    while (this.last.right != null) {
                        this.last = last.right;
                    }
                } else {
                    this.last = this.last.parent.left;
                    // down
                    while (this.last.right != null) {
                        this.last = this.last.right;
                    }
                }
                auxLast.parent.left = null;
            }
        }
        else { 
           this.root = this.last = null;
    }
}

    private void sinkDown() {
        HeapNode<T> heapNode = this.root;
        while (heapNode != null) {
            if (heapNode.left!=null && comparator.compare(heapNode.info, heapNode.left.info) > 0 ||
                heapNode.right!=null && comparator.compare(heapNode.info, heapNode.right.info) > 0) {
                if ((heapNode.left!=null && heapNode.right!=null && 
                    comparator.compare(heapNode.left.info, heapNode.right.info) < 0) ||
                    heapNode.right==null) {
                    swap(heapNode,heapNode.left);
                    heapNode = heapNode.left;
                } else {
                    swap(heapNode, heapNode.right);
                    heapNode = heapNode.right;
                }
                
            }
            else {
               heapNode=null;
            }
        }
    }

}
