/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;
import csci152.adt.Set;

/**
 *
 * @author Serik Zhilibayev Aikorkem Zhumabek
 * 
 */
public class BSTSet<T extends Comparable> implements Set<T> {
    
    public TreeNode<T> root;
    private int size = 0;
    
    public BSTSet(){
        root = null;
        size = 0;
    }
    
    /**
     *
     * @return
     */
    
    public TreeNode<T> getRoot(){
        return root;
    }
    @Override
    public void add(T value) {
        
        if (size == 0){
            root = new TreeNode(value);
            size++;
        }else{
            addHelper(root, value);        
            
        }
    }

    @Override
    public boolean contains(T value) {
        return containsHelper(root, value);
    }

    @Override
    public boolean remove(T value) {
        boolean exist = false;
        
        TreeNode<T> node = root;
        TreeNode<T> parent = null;
        
        while (node != null){
            int c = value.compareTo(node.getValue());
            if (c < 0){
                    parent = node;
                    node = node.getLeft();
            }else if (c > 0){
                    parent = node;
                    node = node.getRight();
            }else if (c == 0){
                    exist = true;
                    break;
            }
        }    
        
        if (exist == true){
            if (node.getLeft() == null && node.getRight() == null){
                if (parent == null){
                    this.clear();
                }else{
                    switchChild(null, parent);
                }
                size--;
            }else if (node.getLeft() == null && node.getRight() != null){
                if (parent == null){
                    root = node.getRight();
                }else{
                    switchChild(node.getRight(), parent);
                }
                size--;
            }else if (node.getLeft() != null && node.getRight() == null){
                if (parent == null){
                    root = node.getLeft();
                }else{
                    switchChild(node.getLeft(), parent);
                }
                size--;
            }else{
                node.setValue(findRightMin(node.getRight(), node, parent));
            }
            
        }
        return exist;
    }
    
    private void switchChild(TreeNode<T> newChild, TreeNode<T> parent){
        if ((newChild==null) || parent.getValue().compareTo(newChild.getValue()) < 0){
            parent.setRight(newChild);
        }else{
            parent.setLeft(newChild);
        }
    }
    
    private T findRightMin(TreeNode<T> node, TreeNode<T> ancestor1, TreeNode<T> preAncestor){
        TreeNode<T> temp = node;
        TreeNode<T> parent = ancestor1;
        
        while (temp.getLeft() != null){
                parent = temp;
                temp = temp.getLeft();
        }
        remove(temp.getValue());
        return temp.getValue();
    }
    
    @Override
    public T removeAny() throws Exception {
        if (size == 0){
            throw new Exception("The Set Is Empty!!!");
        }else if (size == 1){
            T k = root.getValue();
            this.clear();
            return k;
        }
        T k = root.getValue();
        remove(k);
        return k;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private void addHelper(TreeNode<T> node, T value) {
            if (node.getValue().compareTo(value) > 0){
                if (node.getLeft() != null){
                    addHelper(node.getLeft(), value);
                }else{
                    node.setLeft(new TreeNode(value));
                    size++;
                }
            }else if (node.getValue().compareTo(value) < 0){
                if (node.getRight() != null){
                    addHelper(node.getRight(), value);
                }else{
                    node.setRight(new TreeNode(value));
                    size++;
                }    
            }
    }
    
    public String toString(){
        return "[" + toStringHelper(root) + "]";
    }

    private String toStringHelper(TreeNode<T> node) {
        
        if (node == null){
            return "";
        }
        return toStringHelper(node.getLeft()) + " " + node.getValue()+ " " + toStringHelper(node.getRight());
        
    }

    private boolean containsHelper(TreeNode<T> node, T value) {
        
        if (value.compareTo(node.getValue()) < 0){
            if (node.getLeft() != null){
                return containsHelper(node.getLeft(), value);
            }
        }else if (value.compareTo(node.getValue()) > 0){
            if (node.getRight() != null){
                return containsHelper(node.getRight(), value);
            }
        }else if (value.compareTo(node.getValue()) == 0){
            return true;
        }
        return false;
        
    }

    
    
}
