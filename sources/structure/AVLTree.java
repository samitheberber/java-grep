package structure;

import structure.implementation.TreeData;

public class AVLTree
{

    protected class Node
    {
        public int key;
        public Node leftChild;
        public Node rightChild;
        public Node parent;
        public int height;

        public Node()
        {
            this.key = 0;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
            this.height = 0;
        }

    }

    protected Node root;

    public AVLTree()
    {
        this.root = null;
    }

    public void add(int key)
    {
        this.root = insert(this.root, key);
    }

    public boolean searchExists(int key)
    {
        if (this.root == null)
            return false;
        Node current = this.root;
        while (true) {
            if (current.key == key)
                return true;
            else if (current.key > key) {
                if (current.leftChild == null)
                    return false;
                else
                    current = current.leftChild;
            } else {
                if (current.rightChild == null)
                    return false;
                else
                    current = current.rightChild;
            }
        }
    }

    protected int height(Node node)
    {
        if (node == null)
            return -1;
        else
            return node.height;
    }

    protected Node leftRotate(Node x)
    {
        Node y = x.leftChild;
        y.parent = x.parent;
        x.rightChild = y.leftChild;
        x.rightChild.parent = x;
        y.leftChild = x;
        x.parent = y;
        x.height = ( x.leftChild.height > x.rightChild.height ? x.leftChild.height : x.rightChild.height ) + 1;
        y.height = ( y.leftChild.height > y.rightChild.height ? y.leftChild.height : y.rightChild.height ) + 1;
        return y;
    }

    protected Node rightRotate(Node x)
    {
        Node y = x.leftChild;
        y.parent = x.parent;
        x.leftChild = y.rightChild;
        x.leftChild.parent = x;
        y.rightChild = x;
        x.parent = y;
        x.height = ( x.leftChild.height > x.rightChild.height ? x.leftChild.height : x.rightChild.height ) + 1;
        y.height = ( y.leftChild.height > y.rightChild.height ? y.leftChild.height : y.rightChild.height ) + 1;
        return y;
    }

    protected Node rightLeftDoubleRotate(Node x)
    {
        x.rightChild = this.rightRotate(x.rightChild);
        return this.leftRotate(x);
    }

    protected Node leftRightDoubleRotate(Node x)
    {
        x.leftChild = this.leftRotate(x.leftChild);
        return this.rightRotate(x);
    }

    protected Node insert(Node x, int k)
    {
        if (x == null) {
            x = new Node();
            x.key = k;
            x.leftChild = x.rightChild = null;
        } else if (k > x.key) {
            x.rightChild = insert(x.rightChild, k);
            if (height(x.rightChild) == height(x.leftChild) + 2) {
                if (k > x.rightChild.key)
                    x = leftRotate(x);
                else
                    x = rightLeftDoubleRotate(x);
            }
        } else if (k < x.key) {
            x.leftChild = insert(x.leftChild, k);
            if (height(x.leftChild) == height(x.rightChild) + 2) {
                if (k < x.leftChild.key)
                    x = rightRotate(x);
                else
                    x = leftRightDoubleRotate(x);
            }
        }
        x.height = (height(x.leftChild) > height(x.rightChild) ? height(x.leftChild) : height(x.rightChild)) + 1;
        return x;
    }
}
