
//
// Complete this class: 60 points + 20 bonus points
//
// Note:
// Do NOT copy from textbook, or any other sources
// Do NOT copy from code that we did in class (as it contains errors)
//
// Do READ CODE from textbook and in-class code and write it out as your own code
//

/**
   * TreeDictionary class.
   * TreeDictionary implements the (self-balance) binary search tree as a Dictionary.
   * @author Sarah Fakhry
   */
public class TreeDictionary<T extends Comparable<T>>
{
    public static void main(String [] args)
    {
        //Optional: test TreeDictionary here
    }

    /**
     * Putting records into the tree based on their keywords.
     * @param record
     */
    public void insert(Record<T> record) 
    {
        for (T kword : record.Keywords) {
            // Initializing the new nodes fields.
            Node<T> temp_n = new Node<T>();
            Node<T> des = null;
            temp_n.records.insert(record);
            temp_n.keyword = kword;
            if (this.root != null) { 
                // Continuing...
            }
            else {
                this.root = temp_n;
                // Returning updated node.
                return;
            }
            // Looping through node
            for (Node<T> temp_r = this.root; temp_r != null;) {
                des = temp_r;
                // Checking if node exists.
                if (temp_r.keyword.compareTo(kword) == 0) {
                    // Inserting the record into the node.
                    temp_r.records.insert(record);
                    // Returning updated node.
                    return;
                }
                if (kword.compareTo(temp_r.keyword) > 0) {
                    // Putting right node.
                    temp_r = temp_r.right;
                }
                else {
                    // Putting left node.
                    temp_r = temp_r.left;
                }
            }
            if (kword.compareTo(des.keyword) > 0) {
                des.right = temp_n;
            }
            else {
                des.left = temp_n;
            }
        }
    }

    /**
     * Helper function thats a recursive in-order traversal.
     * @param self
     * @param store
     */
    private void inHelp(Node<T> self, LinkedList<Node<T>> store) {
        if (self == null) {
            return;
        }
        else {
            // In-Order Traversal : left, this, right
            // Go to left.
            inHelp(self.left, store);
            // Go to root.
            store.insert(self);
            // Go to right.
            inHelp(self.right, store);
        }
    }

    /**
     * Store in-order traversal of tree nodes in a linked list.
     * @return nodes in in-order traversal
     */
    private LinkedList<Node<T>> InOrderTraversal() 
    {
        LinkedList<Node<T>> store = new LinkedList<Node<T>>();
        // Getting the list of nodes in-order recursively.
        inHelp(this.root, store);
        // Returning built traversal list.
        return store;
    }

    /**
     * Helper function that recursively removes node based on keyword. 
     * @param node
     * @param kword
     * @return node removed
     */
    private Node<T> remHelp(Node<T> node, T kword) {
        if (node == null) {
            return null;
        }
        // Checking if node exists.
        if (node.keyword.compareTo(kword) == 0) {
            return node;
        }
        if (node.keyword.compareTo(kword) > 0) {
            // Go left to find and remove the keyword.
            node.left = remHelp(node.left, kword);
        }
        else {
            // Go right to find and remove the keyword.
            node.right = remHelp(node.right, kword);
        }
        return node;
    }

    /**
     * Using keyword to remove a node that contains this word.
     * @param keyword
     */
    private void remove(T keyword) 
    {
        this.root = remHelp(this.root, keyword);
    }

    /**
     * Finding a node that contains this keyword.
     * @param keyword
     * @return node
     */
    private Node<T> find(T keyword) 
    {
        Node<T> temp = root;
        if (temp == null) {
            return null;
        }
        for (Node<T> node = this.root; node != null;) {
            // Checking if keyword exists.
            if (keyword.compareTo(node.keyword) == 0) {
                return node;
            }
            if (keyword.compareTo(node.keyword) > 0) {
                // Putting right node.
                node = node.right;
            } 
            else {
                // Putting in left node.
                node = node.left;
            }
        }
        return null;
    }

    /**
     * Finding an array of records that contains the given keywords.
     * @param keywords
     * @return array of records
     */
    public DynamicArray<Record<T>> find( LinkedList<T> keywords  ) 
    {
        TreeDictionary<T> dict = null;
        for (T kword : keywords) {
            if (dict == null) {
                dict = find_then_build(kword);
            } 
            else {
                dict = dict.find_then_build(kword);
            }
        }
        Node<T> rec = null;
        // Looping through the whole node to find the max records.
        for (Node<T> node : dict.InOrderTraversal()) {
            if (rec == null) {
                // Initializing first value.
                rec = node;
            } 
            // Checking record sizes to certify same records are in nodes.
            if (node.records.size() <= rec.records.size()) {
                return rec.records;
            }
            else {
                node = rec;
            }
        }
        // Checking if max records are found.
        if (rec == null) {
            return null;
        }
        else {
            // Returning records.
            return rec.records;
        }
    }

    // ----------------------------------------------------------------------
    //
    // !!! READ but Do NOT Change anything after this line
    //
    // ----------------------------------------------------------------------

    private class Node<T>
    {
        Node(){ records=new DynamicArray<Record<T>>();}
        Node(T k){ keyword=k; records=new DynamicArray<Record<T>>();}

        T keyword; //nodes are ordered by Keywords
        Node<T> parent;
        Node<T> left, right; //children
        DynamicArray<Record<T>> records;
        public String toString(){ return "["+keyword+" ("+records.size()+")]"; }
    }

    private Node<T> root; //root of the tree, can be null

    //build this tree by inserting the records
    public void build( DynamicArray<Record<T>> records )
    {
        for(Record<T> r : records)
        {
          insert(r);
        }
    }

    //find a node that contains the given keyword and then
    //build a tree using the records stored in the found node
    //finally return the tree
    private TreeDictionary<T> find_then_build(T keyword)
    {
        //
        //use keyword to find the node
        Node<T> node = find(keyword);
        if(node==null) return null;

        //
        //build the tree from this node's record
        TreeDictionary<T> newT=new TreeDictionary<T>();
        newT.build(node.records);

        //
        //remove the keyword from the Tree
        newT.remove(keyword);

        //done
        return newT;
    }

    public String toString()
    {
      //list all the keyworkds and number of records for each keyword
      //visit all nodes in In-Order traversal.
      LinkedList<Node<T>> nodes = InOrderTraversal();
      String S="Tree Dictionary: {";
      for(Node<T> node : nodes) S+=node.toString()+", ";
      if(!nodes.is_empty()) S=S.substring(0,S.length()-2);
      S+="}";
      return S;
    }
}
