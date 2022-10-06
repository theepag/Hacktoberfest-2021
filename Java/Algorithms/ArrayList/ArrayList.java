/* Operations : 
 * 1. Declare an ArrayList of different type 
 * 2. Add Element 
 * 3. Get Element 
 * 4. Add Element at a specific index
 * 5. Set Element at a specific index 
 * 6. Delete Element from an index
 * 7. Size of the List 
 * 8. Loop/Iterate on the List
 * 9. Sort the List 
 */

 import java.util.*;
 import java.util.Collections;

 class ArrayList {
    public static void main (String args[]){
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Boolean> list = new ArrayList<Boolean>();

        //add elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);

        //to get an element 
        int element = list.get(0); // 0  is the index
        System.out.println(element);

        // add element in between 
        list.add(1,2); // 1 is the index and 2 is the element to the added 
        System.out.println(list);

        //set element 
        list.set(0,0);
        System.out.println(list);

        //delete elements 
        list.remove(0); // 0 is the index
        System.out.println(list);

        //size of list 
        int size = list.size();
        System.out.println(size);

        // loops on lists
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i) + " ");
        }

        System.out.println();


        // sorting the list 
        Collections.sort(list);
        System.out.println(list);
    }
 }