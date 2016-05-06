/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.test;
import csci152.adt.*;
import csci152.impl.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aikorkem Zhumabek Serik Zhilibayev
 */
public class Assignment3 {
    
    
    public static boolean isSubset(Set<Integer> set1, Set<Integer> set2) throws Exception{
        if (set1.getSize() == 0 || set2.getSize() < set1.getSize()){
            return true;
        }
        
        Set<Integer> temp = new BSTSet();
        boolean ok = true;
        int k = 0;
        
        while(set1.getSize() > 0){
            k = set1.removeAny();
            temp.add(k);
            if (!set2.contains(k)){
                ok = false;
            }
        }
        while (temp.getSize() > 0){    
            try {k = temp.removeAny();} catch (Exception ex) {}
            set1.add(k);
        }
        
        return ok;
    }
    
    public static Set<String> union(Set<String> set1, Set<String> set2){
        Set<String> temp1 = new BSTSet();
        Set<String> temp2 = new BSTSet();
        Set<String> union = new BSTSet();
        String k = "";
        
        while(set1.getSize() > 0){
            try {k = set1.removeAny();} catch (Exception ex) {System.out.println(ex.getMessage());}
            temp1.add(k);
            union.add(k);
        }
        while(set2.getSize() > 0){
            try {k = set2.removeAny();} catch (Exception ex) {System.out.println(ex.getMessage());}
            temp2.add(k);
            union.add(k);
        }
        
        while (temp1.getSize() > 0){
            try {set1.add(temp1.removeAny());} catch (Exception ex) {System.out.println(ex.getMessage());}
        }
        
        while (temp2.getSize() > 0){
            try {set2.add(temp2.removeAny());} catch (Exception ex) {System.out.println(ex.getMessage());}
        }
        return union;
    }   
    
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
    
        Set<Integer> temp1 = new BSTSet();
        Set<Integer> ints = new BSTSet();
        int k = 0;
        
        while(set1.getSize() > 0){
            try {k = set1.removeAny();} catch (Exception ex) {System.out.println(ex.getMessage());}
            temp1.add(k);
            if (set2.contains(k)){
                ints.add(k);
            }   
        }
        while (temp1.getSize() > 0){
            try {set1.add(temp1.removeAny());} catch (Exception ex) {System.out.println(ex.getMessage());}
        }
        return ints;
    }
    
    public static boolean areAllPrime(Set<Integer> pset){
        Set<Integer> temp1 = new BSTSet();
        int k = 0;
        boolean isPrime = true;
        
        while(pset.getSize() > 0 && isPrime == true){
            try {k = pset.removeAny();} catch (Exception ex) {System.out.println(ex.getMessage());}
            temp1.add(k);   
            for (int i = 2; i < Math.sqrt((double)k);i ++){
                if (k % i == 0 && k <= 1 ){
                    isPrime = false;
                }
            }
        }    
        
        while (temp1.getSize() > 0){
            try {pset.add(temp1.removeAny());} catch (Exception ex) {System.out.println(ex.getMessage());}
        }
        return isPrime;
    }
    
    public static boolean arePrimeDivisors(int num, Set<Integer> pset) throws Exception{
        Set<Integer> temp1 = new BSTSet();
        boolean ok = true;
        int isize = pset.getSize();
        if (num <= 1||!areAllPrime(pset)){
            throw new Exception("Invalid data was entered!!!");
        }
        
        int k = 0;
        
        while (pset.getSize() > 0){
            try {k = pset.removeAny();} catch (Exception ex) {System.out.println(ex.getMessage());}
            temp1.add(k);   
            if (num % k == 0){
                while (num % k == 0 && num != 1){
                    num = num / k;
                }
            }else{
                ok = false;
            }
        }
        
        while (temp1.getSize() > 0){
            try {pset.add(temp1.removeAny());} catch (Exception ex) {System.out.println(ex.getMessage());}
        }
        
        return ok;
    }
    
    
    
    public static void main(String[] args) throws Exception {
       
       Set<Integer> set1 = new BSTSet();
       Set<Integer> set4 = new LLQHashTableSet(10);
       
       
       set4.add(2);
       set4.add(13);
       set4.add(3);
       set4.add(7);
       
       
       System.out.println(arePrimeDivisors(546, set4));
       
    }
    
}
