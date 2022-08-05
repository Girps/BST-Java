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
public class BinarySearchTreeProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BST<Integer> tree = new BST();
        
       tree.insertNode(100);
       tree.insertNode(50);
       tree.insertNode(80);
       tree.insertNode(120);
       tree.insertNode(110);
       tree.insertNode(180);
       System.out.println(tree);
       tree.remove(100); 
       
       System.out.println(tree.toString()); 
       System.out.println(tree.size()); 
    }
    
}
