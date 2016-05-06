/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import csci152.adt.Queue;
import csci152.adt.Set;

/**
 *
 * @author Serik Zhilibayev Aikorkem Zhumabek
 * @param <T>
 */

public class LLQueueSet<T> implements Set<T>{
    private Queue<T> q;
    
    public LLQueueSet(){
        q = new LinkedListQueue();
    }
    
    @Override
    public void add(T value) {
        if (!contains(value)){
            q.enqueue(value);
        }
    }

    @Override
    public boolean contains(T value) {
        int size = q.getSize();
        T k = null;
        for (int i = 0; i < size; i ++){
            try {
                k = q.dequeue();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (k.equals(value)){
                q.enqueue(k);
                return true;
            }
            q.enqueue(k);
        }
        return false;
    }

    @Override   
    public boolean remove(T value) {
        
        int size = q.getSize();
        T k = null;
        for (int i = 0; i < size; i ++){
            
            try {
                k = q.dequeue();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            if (k.equals(value)){
               return true;
            }
            
            q.enqueue(k);
        }
        return false;
    }

    @Override   
    public T removeAny() throws Exception {
        if (q.getSize() == 0){
            throw new Exception ("The Set Is Empty!!!");
        }
        return q.dequeue();
    
    }

    @Override
    public int getSize() {
        return q.getSize();
    }

    @Override
    public void clear() {
        q.clear();
    }
    
    public String toString(){
        T k = null;
        String res = "[";
        for (int i = 0; i < q.getSize(); i ++){
            try {
                k = q.dequeue();
                q.enqueue(k);
            } catch (Exception ex) {}
            res += k + " ";
        }
        return res + "]";
    }
        
}
