import java.util.*;

public class Hashing {
    public static void main (string args[]){
        // Creation 
        HashMap<String,Integer> map = HashMap<>();

        //Insertion 
        map.put("Hello",120);
        map.put("Man",30);
        map.put("van",150);

        System.out.println(map);

        map.put("van",180);
        System.out.println(map);

        //Searching 
        if(map.containsKey("Indonasia")){
            System.out.println("Key is present in the map");
        } else{
            System.out.println("Key is not present in the map");
        }

        System.out.println(map.get("van")); // Key is exists
        System.out.println(map.get("Indonasia")); //Key doesn't exists 

        //Iteration (1)
        for(Map.Entry<String,Integer> e : map.entrySet()){
            System.out.println(e.getKey());
            System.out.println(e.getValue());

        }

        //Iteration (2)
        Set<String> Key = map.keySet();
        for(String key : key ){
            System.out.println(key + " " + map.get(key));

        }

        // Removing 
        map.remove("van");
        System.out.println(map);
    }
}