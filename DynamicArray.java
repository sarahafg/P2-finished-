import java.util.Iterator;

/**
   * DynamicArray class.
   * Implements Dynamic Array.
   * @author Sarah Fakhry
   */
public class DynamicArray<T> implements Iterable<T>
{
  public static void main(String [] args)
  {
    //Optional: test DynamicArray here
    DynamicArray<Double> A = new DynamicArray<Double>();
    for(int i=0;i<20;i++) 
    A.insert(Math.random()*100);
    for(Double v : A)
    {
      System.out.println(v);
    }
    System.out.println("-------");
  }

  private final int min_cap = 7;
  private int cap;
  private int size;
  private T[] arr;

  @SuppressWarnings("unchecked")
  DynamicArray() 
  {
    this.arr = (T[]) new Object[this.cap];
    this.size = 0;
    this.cap = min_cap;
  }

  /**
   * Inserting data passed into the dynamic array.
   * @param data
   */
  @SuppressWarnings("unchecked")
  public void insert(T data)
  {
    // Checking if size of array is same or greater than length.
    if (this.size() >= this.arr.length) {
      T[] bigshow = (T[]) new Object[this.cap * 2];
      int i = 0;
      for (T value : this.arr) {
        bigshow[i++] = value;
      }
      // Replacing with expanded array.
      this.arr = bigshow;
      // Expanding capacity.
      this.cap *= 2;
    }
    // Putting data where theres space.
    this.arr[size++] = data;
  }

  /**
   * Get the value given the index.
   * @param index
   * @return the value or error
   * @throws IndexOutOfBoundsException
   */
  public T get(int index) 
  {
    if (index > this.size() && index < -1) {
      throw new IndexOutOfBoundsException();
    }
    else {
      return arr[index];
    }
  }

  /**
   * Determines size of the dynamic array.
   * @return the size
   */
  public int size()
  {
    return this.size;
  }

  /**
   * Deletes the last value in the array.
   * @param data
   */
  void delete() 
  {
    if (0 < size) {
      // Deleting last value while decrementing size.
      arr[size - 1] = null;
    }
  }

  /**
   * Deletes element at index "loc".
   * @param loc
   */
  void delete(int loc) 
  {
    if (loc < size() - 1) {
      if (loc > -1) {        
        arr[loc] = null;
        int i = loc;
        // Looping through to shift values.
        while (i < size() - 1) {
          if ((i + 1) < arr.length) {
            arr[i] = arr[i + 1];
            i++;
          }
        }
        // Decrementing size.
        this.size--;
      }
    }
  }

  /**
   * Checking if array is empty.
   * @return true if array is empty, otherwise false
   */
  boolean is_empty(){ return this.size() == 0;}

  public Iterator<T> iterator(){
    DynArrIter<T> new1 = new DynArrIter(this);
    return new1;
  }

  /**
   * Implementing an iterator class.
   */
  class DynArrIter<T> implements Iterator<T> {
    /**
     * Variables to use inside our iterator.
     */
    int size = 0;
    T[] data = null;
    int i = 0;

    /**
     * Initialize an interator for instantiated object.
     * @param obj
     */
    public DynArrIter(DynamicArray<T> obj) {
      data = obj.arr;
      size = obj.size;
    }

    /**
     * Validating if index is near the end.
     * @return when next value exists
     */
    public boolean hasNext() {
      return i < size;
    }

    /**
     * Getting the next element.
     * @return element at the index
     */
    public T next() {
      T element = data[i];
      i++;
      return element;
    }
  }
  
}
