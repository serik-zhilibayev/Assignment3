/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;


import csci152.adt.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serik Zhilibayev
 * @param <T>
 */
public class LLQHashTableSet<T> implements Set<T> {
    private LinkedListQueue<T>[] buckets;
    private int size;
    public LLQHashTableSet(int val){
        size = 0;
        buckets = (LinkedListQueue<T>[]) new LinkedListQueue[val];
    }
    
    
    @Override
    public void add(T value) {
        
        int k = Math.abs(value.hashCode())%buckets.length;
        
        if (buckets[k] == null){
            buckets[k] = new LinkedListQueue(); 
        }   
        T result = null;
        
        for (int i = 0; i < buckets[k].getSize(); i ++){
            try {
                result = buckets[k].dequeue();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (result.equals(value)){
                buckets[k].enqueue(result);
                return;
            }
            buckets[k].enqueue(result);
        }
        
        buckets[k].enqueue(value);
        size++;
    }

    @Override
    public boolean contains(T value) {
        int k = Math.abs(value.hashCode())%buckets.length;
        
        T result = null;
        for (int i = 0; i < buckets[k].getSize(); i ++){
            try {
                result = buckets[k].dequeue();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (result.equals(value)){
               buckets[k].enqueue(result);          
               return true;
            }
            buckets[k].enqueue(result);
        }
        return false;
    }

    @Override
    public boolean remove(T value) {
        int k = Math.abs(value.hashCode())%buckets.length;
        if (buckets[k] == null){
            return false;
        }
        
        T result = null;
        for (int i = 0; i < buckets[k].getSize(); i ++){
            try {
                result = buckets[k].dequeue();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (result.equals(value)){
                size--;
                return true;
            }
            buckets[k].enqueue(result);
        }
        return false;    
    }

    @Override
    public T removeAny() throws Exception {
        
        if (size == 0){
            throw new Exception("Empty!!!");
        }
        
        int i;
        
        for (i = 0; i < buckets.length; i++){
            if (buckets[i] != null && buckets[i].getSize() > 0){
                break;
            }
        }
        size--;
        return buckets[i].dequeue();
    
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        int i;
        for (i = 0; i < buckets.length; i++){
            if (buckets[i] != null){
                buckets[i].clear();
            }
        }
        size = 0;
    }
    
    public String toString(){
        
        String res = "[";
        for (int i = 0; i < buckets.length; i++){
            if (buckets[i] != null){
                for(int j = 0; j < buckets[i].getSize(); j++){
                    T x = null;
                    try {
                        x = buckets[i].dequeue();
                    } catch (Exception ex) {}
                    buckets[i].enqueue(x);
                    res += x.toString() + " ";
                }
            }
        }
        return res+"]";
    
    }

}
