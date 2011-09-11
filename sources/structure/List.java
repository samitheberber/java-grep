package structure;

import structure.implementation.ListItem;

public class List implements ListItem, Cloneable
{
    private class Node
    {
        protected int key;
        protected ListItem data;
        protected Node next;
        protected Node prev;

        public Node(int key, ListItem item)
        {
            this.key = key;
            this.data = item;
            this.prev = null;
            this.next = null;
        }

        public void setPrev(Node item)
        {
            this.prev = item;
        }

        public void setNext(Node item)
        {
            this.next = item;
        }

        public Node getPrev()
        {
            return this.prev;
        }

        public Node getNext()
        {
            return this.next;
        }

        public int getKey()
        {
            return this.key;
        }

        public ListItem getData()
        {
            return this.data;
        }
    }

    protected Node head;
    protected Node current;

    public List()
    {
        this.head = null;
        this.current = this.head;
    }

    public ListItem insert(ListItem item, int key)
    {
        Node newNode = new Node(key, item);
        newNode.setNext(this.head);
        if (this.head != null)
            this.head.setPrev(newNode);
        this.head = newNode;
        this.current = this.head;
        return item;
    }

    public ListItem insert(ListItem item)
    {
        int key = (this.head == null) ? 0 : this.head.getKey() + 1;
        return this.insert(item, key);
    }

    public ListItem delete(ListItem crapData)
    {
        Node crap = this.search(crapData);
        if (crap == null)
            return null;
        Node oldPrev = crap.getPrev();
        Node oldNext = crap.getNext();
        if (oldPrev == null)
            this.head = oldNext;
        else
            oldPrev.setNext(oldNext);
        if (oldNext != null)
            oldNext.setPrev(oldPrev);
        return crap.getData();
    }

    public ListItem search(int key)
    {
        Node currentNode = this.head;
        while ( (currentNode != null) && (key != currentNode.getKey()) )
            currentNode = currentNode.getNext();
        return currentNode.getData();
    }

    protected Node search(ListItem searchedData)
    {
        Node currentNode = this.head;
        while ( (currentNode != null) && (!searchedData.equals(currentNode.getData())) )
            currentNode = currentNode.getNext();
        return currentNode;
    }

    public ListItem next()
    {
        ListItem data = this.current.getData();
        this.current = this.current.getNext();
        return data;
    }

    public boolean hasNext()
    {
        return (this.current != null);
    }

    public void rewind()
    {
        this.current = this.head;
    }

    public boolean isEmpty()
    {
        return (this.head == null);
    }

    public String toString()
    {
        String text = new String();
        while (this.hasNext())
            text += this.next().toString() + "\n";
        this.rewind();
        return text;
    }

    public List getCopy()
        throws Exception
    {
        return (List) this.clone();
    }
}
