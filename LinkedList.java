import java.util.Iterator;
// import java.util.LinkedList;

/**
   * LinkedList class.
   * Implements Linked List.
   * @author Sarah Fakhry
   */
public class LinkedList<T> implements Iterable<T>
{
  public static void main(String [] args)
  {
    //Optional: test LinkedList here
    Double A[]={0.1,0.34,0.7,23.1,-0.75};
    LinkedList<Double> M = new LinkedList<Double>(A);
    Iterator<Double> llit = M.iterator();
    
    while (llit.hasNext()) {
      System.out.print(llit.next() + " ");
    }
    
    System.out.println();
    System.out.println("Linked List: "+M);
    System.out.println(M.head.next.data);
    System.out.println(M.tail.prev.data);
    System.out.println(M.tail.prev.prev.data);
    System.out.println("-----");
    M.delete(M.head.next);
    M.delete(M.tail.prev);
    System.out.println(M.tail.prev.prev.data);
    M.delete(M.tail.prev.prev);
    // System.out.println("Linked List: "+M);
    M.insert(7.17);
    M.insert(3.5);
    System.out.println("Linked List: "+M);
  
  }

  /**
   * Creating a linked list from an array.
   * @param A
   */
  public LinkedList(T [] A)
  {
    // Initializing head and tail.
    head = new Node<T>();
    tail = new Node<T>();
    tail.prev = head;
    head.next = tail;
    // Inserting elements from array into linked list.
    for(int i = 0; i < A.length; ++i) {
      insert(A[i]);
    }
  }

  /**
   * Inserting data into the linked list.
   * @param data
   */
  public void insert(T data)
  { 
    Node<T> temp = new Node<>(data);
    Node<T> next1 = head.next;
    if (head.next != tail) {
      // Looping through linked list.
      for (next1 = head.next; head.next.next != null;) {
        if(next1.next == null || tail != next1.next) {
          next1 = next1.next;
        }
        else {
          break;
        }
      }
      // Initializing last valid node.
      temp = new Node<>(data);
      temp.prev = next1;
      tail.prev = temp;
      temp.next = tail;
      next1.next = temp;
    }
    else {
      tail.prev = temp;
      head.next = temp;
      temp.prev = head;
      temp.next = tail;
    }
  }

  /**
   * Removing node n from the linked list.
   * @param n
   */
  private void delete(Node<T> n)
  {
    // Checks if linked list is empty.
    if (is_empty()){
      return;
    }
    else {
      // Removing from middle of linked list.
      if (n.next != tail && head != n.prev) {
        n.prev.next = n.next;        
        n.next.prev = n.prev;
      }
      // Removing from end of linked list.
      if (tail.prev == n) {
        tail = n;
      }
      // Removing from beginnning of linked list.
      if (head.next == n) {
        head.next = n.next;
      }
    }
  }

  /**
   * Checking if empty.
   * @return true if empty, otherwise false
   */
  public boolean is_empty()
  {
    // Checking if empty.
    if (head.next == tail.prev) {
      return true;
    }
    else {
      return false;
    }
  }

  public Iterator<T> iterator(){
    Iter<T> iter = new Iter<T>(head, tail);
    return iter;
  }

  /**
   * Implementing an iterator class.
   */
  class Iter<T> implements Iterator<T> {
    Node<T> temp = new Node<T>(null);
    Node<T> temp1 = new Node<T>(null);

    /**
     * Initialize an interator.
     * @param start
     * @param end
     */
    public Iter(Node<T> start, Node<T> end) {
      temp1 = end;
      temp = start.next;
    }

    /**
     * Validating if node is near the end.
     * @return when next node exists
     */
    public boolean hasNext() {
      if(temp1 == temp) {
        return false;      
      }
      else {
        return true;
      }
    }

    /**
     * Getting data at the next node.
     * @return data
     */
    public T next() {
      T elm = temp.data;
      temp = temp.next;
      return elm;
    }
  }


    //Note: You will need to implement an iterator class using java.util.Iterator
    //      interface

    //TO DO: implement iterator here

    // ----------------------------------------------------------------------
    //
    // !!! READ but Do NOT Change anything after this line
    //
    // ----------------------------------------------------------------------

    private class Node<T>
    {
      Node(){}
      Node(T data){ this.data=data; }
      public T data;
      public Node<T> next;
      public Node<T> prev; //for doubly linked list
    }

    Node<T> head; //pointing to the location BEFORER the first element
    Node<T> tail; //for doubly linked list
                  //pointing to the location AFTER the last element

    public LinkedList() //constructor
    {
      head=new Node<T>();
      tail=new Node<T>();
      head.next=tail;
      tail.prev=head;
    }

    public T last()
    {
      //nothing to return
      if(head.next==tail) return null;
      return tail.prev.data;
    }

    public String toString()
    {
      String S="(";
      for(T t : this) S=S+t+", ";
      if(is_empty()==false) S=S.substring(0,S.length()-2);
      S=S+")";
      return S;
    }
}
