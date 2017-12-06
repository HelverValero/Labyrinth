package com.uptc.prg3.datastruct.others;

public class HeapNode <T> {
    protected T info;
    protected HeapNode<T> parent;
    protected HeapNode<T> left;
    protected HeapNode<T> right;

    public HeapNode(T info, HeapNode<T> parent) {
        this.info = info;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public HeapNode<T> getParent() {
        return parent;
    }

    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }

    public HeapNode<T> getLeft() {
        return left;
    }

    public HeapNode<T> setLeft(HeapNode<T> left) {
        this.left = left;
        return left;
    }

    public HeapNode<T> getRight() {
        return right;
    }

    public HeapNode<T> setRight(HeapNode<T> right) {
        this.right = right;
        return right;
    }
    
    public HeapNode<T> setLeft(T info) {
       HeapNode heapNode= new HeapNode(info,this);
       this.setLeft(heapNode);
       return heapNode;
    }
    
    public HeapNode<T> setRight(T info) {
       HeapNode heapNode= new HeapNode(info,this);
       this.setRight(heapNode);
       return heapNode;
    }
}
