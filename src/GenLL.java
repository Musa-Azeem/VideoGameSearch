/*
Written by Musa Azeem
This class defines a generic linked list,
which will be used to create a linked list of type VideoGame
This linked list has the functionality to add nodes to the end of the list,
to return the current value, to move current along the list, to check if there
is another node after current, and to print the values of the nodes
 */
public class GenLL<T> {
    private class ListNode{
        T data;
        ListNode link;
        public ListNode(T data, ListNode link){
            this.data = data;
            this.link = link;
        }
    }
    private ListNode head;   //will always be first node in list
    private ListNode current;  //used for getting values from the list
    private ListNode tail;   //will always be last node in list

    public GenLL(){
        this.head = this.tail = null;
    }
    public void add(T data){
        ListNode newNode = new ListNode(data, null);
        if(this.head==null){     //if list is empty, set head to the new node
            this.head = newNode;
            this.tail = this.current = this.head;     //start tail and current at head, as for now this is the only node
        }
        else {
            tail.link = newNode;       //if list is not empty, set tail's link to the new node
            tail = tail.link;          //set tail to the new node, so that it is at the end
        }
    }
    public T getCurrent(){
        if(current == null)
            return null;
        return current.data;
    }
    public void gotoNext(){
        if(current == null)
            return;
        current = current.link;
    }
    public void reset(){
        current = head;
    }
    public boolean hasNext(){
        if(current == null)
            return false;
        return current.data != null;
    }
    public void print(){
        for(ListNode temp = head; temp != null; temp = temp.link){
            System.out.println(temp.data);
        }
    }
}
