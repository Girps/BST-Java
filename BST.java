/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtreeproject;

/**
 *
 * @author Jesse
 */

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
        
        /*Recursive void method traverses the BST and adds until null is 
            found and inserts our node into the tree */ 
        public void add(E target)
        {
            // case if same data skip return to calling method 
            if (data.compareTo(target) != 0)
            {
                // 3 cases
                // if data less than current node traverese left 
                if(this.data.compareTo(target) > 0)
                {
                // go left if not null
                    if(this.left != null)
                    {
                        left.add(target); // trav left
                    }
                    else
                    {
                        // otherwise its empty 
                        left = new Node(target);
                    }
                } // go right 
                else
                {
                    // If right is not empty
                    if(this.right != null)
                    {
                        right.add(target); // trav to right
                    }
                    else
                    {
                        // other wise intialize right=
                        right = new Node(target); 
                    }
                }
            }
        }
        
        /*Romoves a node in the list */ 
        public void removeNode(E target)
        {
            root = removeNodeHelper(root,target); 
        }
       
        // Returning node to mutatad BST 
        private Node removeNodeHelper(Node root, E target)
        {
            
            // Case we found the node 
            if(root.data.compareTo(target) == 0 )
            {
                // have no children 
                if(root.left == null && root.right == null)
                {
                   // No children case set result to null 
                     return null; 
                } // 1 child case on right
                else if(root.left != null && root.right == null)
                {
                    return root = root.left; 
                }
                else if(root.right != null && root.left == null)// child on the right
                {
                   return root = root.right;  
                }
                else // two children case 
                {
                        // find inorder sucessor swap data 
                    Node temp = inOrderSuccessor(root.right);
                    // swap data 
                    root.data = temp.data; 
                    // Now delete it 
                    root.right = removeNodeHelper(root.right,temp.data);
                    return root; 
                }
            }
            
            // Didnt find equal trave cases
            // if root data is bigger go left 
            if(root.data.compareTo(target) > 0)
            {
                if(root.left != null)
                {
                    root.left = removeNodeHelper(root.left,target); 
                }
            } // if root data is less go right
            else if(root.data.compareTo(target) < 0)
            {
                if(root.right != null)
                {
                    root.right = removeNodeHelper(root.right,target); 
                }
            }
            
            return root; 
        }
        
        
        //Node reference returning method returns node to be removed 
        private Node inOrderSuccessor(Node root)
        {
            // Find minimum of given root 
            // Just traverse Left  
            if(root.left != null)
            {
                return inOrderSuccessor(root.left); 
            }
            else
            {
                return root; // return root no more left found 
            }
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
        root.removeNode(data);
    }
    
    // Void method inserts node in the BST
    public void insertNode(E data)
    {
        if(root == null)
        {
        // root is null create new node pointed by root
        root = new Node(data); 
        }
        else
        {
            // Use instance method of root and add it to our tree
            root.add(data); 
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
