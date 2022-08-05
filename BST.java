/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtreeproject;


// BST class only takes in refences that implement Comparable interface 
public class BST <E extends Comparable> 
{
 
    // Inner Node class 
    public class Node
    {
        Node right; 
        Node left; 
        E data; 
        
        public Node(E data)
        {
            this.data = data; 
            right = null; 
            left = right; 
        }
        
        // Node methods
        
        // String our data
        @Override
        public String toString()
        {
            return "(" + data.toString() + ")"; 
        }
       
    }
    //*********************************************************//
    
    // Bst data fields 
    private Node root; 
    
    // Default constructor 
    public BST()
    {
        root = null; 
    }
    
    public void remove(E data)
    {
        root = removeNodeHelper(root,data);
    }
    
    public Node removeNodeHelper(Node root,E data)
    {
         // Base case if noode is null throw an excpetion 
         if(root == null){throw new BSTException("Element not found"); }
         
         // Base case found the element in cuurent root
         if(root.data.compareTo(data) == 0)
         {
             // check what type of node 
             // if leaf 
             if(root.left == null && root.right == null)
             {
                 // leaf case and equal just return null 
                 return null;
             }
             if(root.right == null && root.left != null)
             {  // child on the left case
                return root.left; // assign previous refence to left child
             }
             else if(root.right != null && root.left ==null)
             { // child on the right case
                 return root.right; // assign previous refence to rigth child 
             }
             else
             { // two children case, replace with inOrder successor 
                 Node temp = inOrderSuccessor(root.right); 
                 // Have succesor refence  swap data 
                root.data = temp.data; 
                // Now delete the inOrderSccessor node and initlize refernce to right 
                root.right = removeNodeHelper(root.right, root.data);
               return root; 
             }
         } // case when not found traverse  
         else
         {
             // case to traverse  
             // case root is bigger go left
             if(root.data.compareTo(data) > 0)
             {
                 // traverse to the left
                 root.left = removeNodeHelper(root.left,data);
                 return root; 
             } // case root is less than data go right 
             else if(root.data.compareTo(data) < 0)
             {
                 // trave right and reassign the refence returned
                 root.right = removeNodeHelper(root.right,data); 
                 return root; 
             }
             return root; 
         }
    }
    
    // Void method inserts node in the BST
    public void insertNode(E data)
    {
        
        root = insertHelper(root, data );    
        
    }
    
    private Node insertHelper(Node root, E data)
    {
        // Base case if root is null return new node 
        if(root == null){return new Node(data); }
        
        // Make sure the data is not same then traverse 
        if(root.data.compareTo(data ) != 0 )
        {
            // Otherwise root is not empty now compare and traverse Data
            if(root.data.compareTo(data) > 0)
            {
            // current root data is bigger  traverse to left 
            root.left = insertHelper(root.left,data); 
            }
            else // Otherwise root data is smaller traverse to right
            {
            root.right = insertHelper(root.right,data); 
            }
        }
        // if the same just return current root no need to create a new root
        return root; 
    } 
    
    
    /*Node returning method returns method to inOrder successor */
    private Node inOrderSuccessor(Node root)
    {
        // Base case if left side is null return root 
        if(root.left == null)
        {
            return root; 
        }
        else // Otherwise recursive call
        {
            return inOrderSuccessor( root.left); 
        }
    }
    
    // Override string method from object class return node data of the tree
    @Override 
    public String toString()
    {
        String result = this.inOrderString(root); 
        return result; 
    }
    
    // InOrder Traveral ? L,ROOT,RIGHT
    
    private String inOrderString(Node root)
    {
        String result = ""; 
        // Base case if null is reached
        if(root != null)
        {
        // Recursive case to Left node first and returns its string
        result +=   inOrderString(root.left);
        result +=  root.toString() + " "; 
        result +=  inOrderString(root.right); 
        }
        
        return result; 
    }
    
    // return number of elements in BST
    public int size()
    {
        return sizeHelper(root); 
    }
    
    
    /*Int returngin recursive method traverse inOrder BST and returns number
        of elements of the Tree */  
    private int sizeHelper(Node root)
    {
        // Base case if  root is null return 0
        if(root == null){return 0; }
        // Otherwise there is element recursive call and increment
        return  sizeHelper(root.left) + 1 + sizeHelper(root.right); 
    }
    
    
    public boolean search(E target)
    {
        return (root == null )? (false): (searchHelper(root,target)); 
    }
    
    /*Recursive boolean method binary search, for data in the 
        Binary search tree*/ 
    private boolean searchHelper(Node root, E target)
    {
        // if root is null return false didnt find it in tree
        if(root.data == null){return false; }
        // Cases check if same 
        if(root.data.compareTo(target) == 0 ){return true; } 
        // Otherwise recursive case left and right
        
        // Check left side 
        if(root.left != null && root.data.compareTo(target) > 0)
        {
            // Case target is less than current root data go left 
            return searchHelper(root.left,target); 
        }
        else if(root.right != null)
        {   
            // Check right
            return searchHelper(root.right,target); 
        }
        
        // 
        return false; 
    }
}
