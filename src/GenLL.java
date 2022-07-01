/*
Author:     Musa Azeem
Date:       3/29/21
This file contains the GenLL class, and its subclass, the ListNode class, 
    which implement a generic linked list
This linked list has the functionality to add nodes to the end of the list,
    and move a pointer along the list to access values

Subclasses:
    ListNode: defines an instance of a node in the linked list

Members:
    head (ListNode):    first node in the list
    current (ListNode): movable node that can point to different nodes in the list
    tail (ListNode):    last node in the list

Methods:
    Constructor:    sets head and tail to null
    add:            adds a new node to the linked list
    getCurrent:     returns the value at the node current is currently pointing to
    gotoNext:       moves current to point to the next node in the list
    reset:          resets current to point to head node
    hasNext:        returns true if the next node after current exists
    print:          prints the value of each node in the list
*/

public class GenLL<T> {
    private class ListNode{
        /*
        This class defines an instance of a node in the list

        Class Members:
            data (<T>):      data of node, type is set by GenLL
            link (ListNode):    pointer to the next node in the list
        
        Class Methods:
            Constructor:    sets data and link members
        */

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
        /*
        Constructor:    sets head and tail to null - creates empty list
        */

        this.head = this.tail = null;
    }
    public void add(T data){
        /*
        Adds a new node to the end of the list
        If head is null, list is empty, so the tail, current, and head is set to this new node
        Otherwise, new node is added to the end of the list
        The node that the tail currently points to is set to point at the new node, 
            and tail is set to point at the new node

        Parameters:
            data (<T>):     data of specified type to be used to create a new node
        */

        ListNode newNode = new ListNode(data, null);
        if(this.head==null){     //if list is empty, set head to the new node
            this.head = newNode;
            this.tail = this.current = this.head;     //start tail and current at head, as for now this is the only node
        }
        else {
            this.tail.link = newNode;       //if list is not empty, set tail's link to the new node
            this.tail = this.tail.link;          //set tail to the new node, so that it is at the end
        }
    }
    public T getCurrent(){
        /*
        Returns the value of the node that current is at
        If current is null (list is empty) return null

        Return:
            <T>:    value of the current node
        */

        if(this.current == null)
            return null;
        return this.current.data;
    }
    public void gotoNext(){
        /*
        Moves current to the next node in the list
        if current is null (list is empty), do not proceed
        */

        if(this.current == null)
            return;
        this.current = this.current.link;
    }
    public void reset(){
        /*
        Resets current to point to the list head
        */

        this.current = this.head;
    }
    public boolean hasNext(){
        /*
        If current is pointing at null, current has reached the end of the list, so return false
        If not, return true

        Return:
            boolean:    true if there are still more nodes in the list
        */

        if(this.current == null)
            return false;
        return this.current.data != null;
    }
    public void print(){
        /*
        Prints all node values to the console by moving current along the list
        */

        for(ListNode temp = this.head; temp != null; temp = temp.link){
            System.out.println(temp.data);
        }
    }
}
