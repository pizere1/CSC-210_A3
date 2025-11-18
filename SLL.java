/**
 * Class to implement a singly linked list
 *
 * @author Peace Izere
 * @version Fall 2025
 */

public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T> ,Phase4SLL<T>{
        /// Class  variables
        /// Node head for tracking the top of the list
        /// size for tracking the number of list elements
        NodeSL<T> head;
        int size;
        public SLL(){
            this.head=null;
            this.size=0;
        }


        public SLL (SLL<T> list){
            NodeSL<T> temp=list.getHead();
            SLL<T> deepCopy=new SLL<>();
            if(list.isEmpty()){
                throw new MissingElementException("Can't make a copy of an empty List");
            }else{
                for(NodeSL<T> current=list.getHead(); current!=null; current=current.getNext()){
                    deepCopy.addLast(current.getData());
                }
            }
        }
        /**
         *  Accessor for head node
         *  @return the head node
         */
        public NodeSL<T> getHead(){
            return this.head;
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
    public int size(){
        if (this.isEmpty()) {
            return 0;
        }else{
            this.size=0;
            NodeSL<T> current=this.head;
            while(current!=null){
                this.size++;
                current=current.getNext();
            }
            return size;
        }
    }
    /**public int size() {
     if (this.isEmpty()) {
     return 0;
     } else {
     return this.size;
     }
     }**/

    /**
     *  Makes a copy of elements from the original list
     *  @param here  starting point of copy
     *  @param n  number of items to copy
     *  @return the copied list
     */
    public SLL<T> subseqByCopy(NodeSL<T> here, int n){
        if(this.isEmpty()){
            throw new MissingElementException("Can't make a copy of an empty List");
        }else if(here==null){
            throw new MissingElementException("Node"+here+"not in the list");
        }else if(n<=0){
            return null;
        }
        else if (n>this.size()){
            throw new MissingElementException("Can't make a copy of an element that exceed the list size");
        }else {
            SLL<T> subseq=new SLL<>();
            NodeSL<T> current=this.head;
            while(current!=null&&current!=here){
                current=current.getNext();
            }if(current==null){//at the end without the shown point
                throw new MissingElementException("Node"+here+"is not in the list");
            }//else if(current==here){
            int count=0;
            for(NodeSL<T> node=current;node.getNext()!=null;node=node.getNext()){
                if(count<n){
                    subseq.addLast(node.getData());
                }
                count++;
            }
                if(subseq.size()<n){
                    throw new MissingElementException("Insufficient list elements");
                }
            //}
            return subseq;
        }
    }

    /**
     *  Places copy of the provided list into this after the specified node.
     *  @param list  the list to splice in a copy of
     *  @param afterHere  marks the position in this where the new list should go
     */
    public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
        if(this.isEmpty()){
            throw new MissingElementException("The list is empty, Node" +afterHere+"is not in the list");
        }else if(list.isEmpty()){
            throw new MissingElementException("Can't add an empty List");
        }else if(afterHere==null){//afterhere is null
            SLL<T>new_List=new SLL<>(list);
            this.size=this.size+list.size();
            new_List.getTail().setNext(this.head);
            this.head=new_List.getHead();
        }else{
            SLL<T>new_List=new SLL<>(list);
            NodeSL<T>current=this.head;
            while((current!=null)&&(current!=afterHere)){
                current=current.getNext();
            }if((current!=afterHere)){//afterhere is not found
                throw new MissingElementException("Node"+afterHere+"is not in the list");
            }//else if((current==afterHere)){
                this.size=this.size+new_List.size();
                NodeSL<T> temp=afterHere.getNext();
                current.setNext(new_List.getHead());
                new_List.getTail().setNext(temp);
            //}
        }
    }

    /**
     *  Extracts a subsequence of nodes and returns them as a new list
     *  @param afterHere  marks the node just before the extraction
     *  @param toHere  marks the node where the extraction ends
     *  @return  the new list
     */
    public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
        if(this.isEmpty()){
            throw new MissingElementException("Can't extract an empty List");
        }else if(afterHere==null){
            SLL<T>subseq=new SLL<>();
            subseq.head=this.head;
            NodeSL<T> current=this.head;
            while(current!=null&&current!=toHere){
                current=current.getNext();
            }if((current==null)){
                throw new MissingElementException("Node"+toHere+"is not in the list");
            }else if(current==toHere){
                int a=this.size;
                NodeSL<T> temp=toHere.getNext();
                toHere.setNext(null);
                this.head=temp;
               // this.size=a-subseq.size();
            }
            return subseq;
        }if(afterHere.getNext()==null){
            throw new MissingElementException("There are no elements after"+afterHere+"in the list");
        }
        SLL<T> subseq=new SLL<>();
        NodeSL<T> current=this.head;
        while((current.getNext()!=null)&&current!=afterHere){
            current=current.getNext();
        }if(current==null){
            throw new MissingElementException("Node"+afterHere+"was not found in the list");
        }else if(current==afterHere){
            subseq.head=current.getNext();
            while(current.getNext()!=null&&(current.getNext()!=toHere)){
                current=current.getNext();
            }if(current.getNext()==null){
                throw new MissingElementException("Node"+toHere+"is not in the list");
            }else if(current.getNext()==toHere){
                int a=this.size;
                NodeSL<T> temp=toHere.getNext();
                toHere.setNext(null);
                //current.setNext(null);
                afterHere.setNext(temp);
               // this.size=a-subseq.size();
            }
        }
        return subseq;
    }

    /**
     *  Takes the provided list and inserts its elements into this
     *  after the specified node.  The inserted list ends up empty.
     *  @param list  the list to splice in.  Becomes empty after the call
     *  @param afterHere  Marks the place where the new elements are inserted
     */
    public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere){
        if (this.isEmpty()){
            throw new MissingElementException("Node"+afterHere+"is not found. The ist is empty");
        }else if(afterHere==null){
            throw new MissingElementException("Node"+afterHere+"is not in the list");
        }else if(list.isEmpty()){
            throw new MissingElementException("Can't add an empty List");
        }else if(list.equals(this)){
            throw new SelfInsertException("Can't splice a list onto itself");
        } else{
            NodeSL<T> current=this.head;
            while(current!=null&&current!=afterHere){
                current=current.getNext();
            }if((current==null)){
                throw new MissingElementException("Node"+afterHere+"is not in the list");
            }
            NodeSL<T> lastList=current.getNext();
            NodeSL<T>temp2=list.getHead();
            while(temp2.getNext()!=null){
                temp2=temp2.getNext();
            }
            current.setNext(list.getHead());
            temp2.setNext(lastList);
            list.head=null;
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
