package com.naver.wordladder.model;

public  class Node<T> {
        private T data;
        private Node<T> parent;
        private int level = 0;

        public Node (T data) {
                this.data = data;
        }

        public T getData() {
                return data;
        }

        public Node<T> getParent() {
                return parent;
        }

        public int getLevel() {
                return level;
        }

        public void setData(T data) {
                this.data = data;
        }

        public void setParent(Node<T> parent) {
                this.parent = parent;
        }

        public void setLevel(int level) {
                this.level = level;
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