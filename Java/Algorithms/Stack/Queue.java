// Queue using array

import java.util.*;

public class Queue{
    static class Queue {
        static int arr[];
        static int size;
        static int rear ;

        Queue (int size) {
            this.size = size ;
            size = new int[size];
            raer = -1 ;

        }
         public static boolean isEmpty() {
            return rear == -1 ;
         } 
    }

    public static boolean isFull(){
        return rear == size-1;

    }

    public static void add(int data){
        if(isFull()) {
            System.out.println("Overflow");
            return;
        }

        arr[++rear] = data;
    }
}