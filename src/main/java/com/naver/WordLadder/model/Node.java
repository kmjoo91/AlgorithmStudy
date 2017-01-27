package com.naver.WordLadder.model;

public  class Node<T> {
        private T data;
        private Node<T> parent;

        public Node (T data) {
                this.data = data;
        }

        public T getData() {
                return data;
        }

        public Node<T> getParent() {
                return parent;
        }

        public void setData(T data) {
                this.data = data;
        }

        public void setParent(Node<T> parent) {
                this.parent = parent;
        }

        public void printTree() {
                //Tree 출력
                System.out.println("===트리출력===");
                Node<T> currentNode = this;
                do {
                        System.out.println(currentNode.getData());
                } while ((currentNode = currentNode.getParent()) != null);
        }

}