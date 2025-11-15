/**
 * Class to implement a singly linked list
 *
 * @author
 * @version Spring 2024
 */

public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T> {
        /// Class  variables
        /// Node head for tracking the top of the list
        /// size for tracking the number of list elements
        private NodeSL<T> head=null;
        private int size=0;

        /**
         *  Accessor for head node
         *  @return the head node
         */
        public NodeSL<T> getHead(){
            return head;
        }

        /**
         *  Accessor for tail node
         *  @return the tail node
         */
        public NodeSL<T> getTail(){
            if(this.isEmpty()){
                return null;
            }
            NodeSL<T> current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            return current;
        }

        /**
         *  Determines whether a list is empty
         *  @return T/F is the list empty?
         */
        public boolean isEmpty(){
            return (this.head==null);
        }
        /**
         *  Inserts the given item at the head of the list
         *  @param v item to insert
         */
        public void addFirst(T v){
            NodeSL<T> newNode= new NodeSL<>(v,this.head);
            this.head=newNode;
            this.size++;
        }

    /**
     *  Inserts the given item at the tail of the list
     *  @param v to insert
     */
    public void addLast(T v){
        if(this.isEmpty()){
            this.addFirst(v);
        }else{
            NodeSL<T>current=head;
            NodeSL<T> newNode= new NodeSL<>(v,null);
            while(current.getNext()!=null){
                current=current.getNext();
            }
            current.setNext(newNode);
        }
        this.size++;
    }
    /**
     *  Inserts the given item after the specified node
     *  @param here node to insert after
     *  @param v item to insert
     */
    public void addAfter(NodeSL<T> here, T v){
        if(this.isEmpty()){
           // System.out.println("Node not found");
            //return;
            throw new MissingElementException("The list is empty");
        }
        NodeSL<T> current=head;
        while((current!=here) && (current.getNext()!=null)){
            current=current.getNext();
        }if((current!=here)&&(current.getNext()==null)){
            throw new MissingElementException("The node" + here+"is not in the list");
        }else{
        NodeSL<T> temp=current.getNext();
        NodeSL<T> newNode= new NodeSL<>(v,temp);
        current.setNext(newNode);
        this.size++;
        }
    }
    /**
     *  Removes the given item from the head of the list
     *  @return v item removed
     */
    public T removeFirst(){
        if(this.isEmpty()){
            //return null;
            throw new MissingElementException("The list is empty");
        }
        NodeSL<T> current=this.head;
        this.head=this.head.getNext();
        current.setNext(null);
        this.size--;
        return current.getData();
    }

    /**
     *  Removes the given item from the tail of the list
     *  @return item removed
     */
    public T removeLast(){
        if(this.isEmpty()){
           // return null;
            throw new MissingElementException("The list is empty");
        }else if(this.head.getNext()==null){
            return this.removeFirst();
        }else{
            NodeSL<T> current=this.head;
            while((current.getNext()!=null)&&(current.getNext().getNext()!=null)){
                current=current.getNext();
            }
            NodeSL<T> removed=current.getNext();
            current.setNext(null);
            this.size--;
            return removed.getData();
        }
    }

    /**
     *  Removes the node after the given position
     *  @param here marks position to remove after
     *  @return item removed
     */
    public T removeAfter(NodeSL<T> here){
        if(this.isEmpty()){
            //return null;
            throw new MissingElementException("Can't remove an element from an empty list");
        }else if(here==null){
            throw new MissingElementException("Can't remove an null element from a list");
        }else if(this.head.getNext()==null){
            throw new MissingElementException("Can't remove a second element from a single element list");
        }
        else {
            T data;
            NodeSL<T> current=head;
            while((current!=here)&&(current.getNext()!=null)){
                current=current.getNext();
            } if(current.getNext()==null){
               // return null;
                throw  new MissingElementException("Can't remove an element after the last element");
            }
            data=current.getNext().getData();
            current.setNext(current.getNext().getNext());
            this.size--;
            return data;
        }
    }
    /**
     *  Returns a count of the number of elements in the list
     *  @return current number of nodes
     */
    public int size() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return this.size;
        }
    }
    /** Converts to a string representation */
    public String toString(){
        String list="[";
        for(NodeSL<T> node =head;node!=null;node=node.getNext()){
            list+=(String)node.getData();
            if(node.getNext()!=null){
                list+=",";
            }
        }
        list=list+"]";

        return list;

    }
}
