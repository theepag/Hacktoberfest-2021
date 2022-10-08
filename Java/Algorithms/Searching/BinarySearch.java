import java.util.*;

public class BinarySearch(){

//-Important
// Binary search is only used in Sorted array[];
  
public static int Search(int arr[], int key){
  int st = 0; // starting index
  int end = arr.length-1; // ending index
  while(st<=end){
    int mid = (st+end)/2; // mid term
    if(key==mid)
        return mid;
    else if(key<mid){
      end=mid-1;
    }
    else 
      st=mid+1;
  }
}

public static void main(){
  Scanner sc = new Scanner(System.in);
  System.out.println("Enter the size of the array");
  int n = sc.nextInt(); // Size of the array;
  int arr[]= new int [n];
        System.out.println("Enter Elements of the array");
        for(int i=0;i<n;i++){
            arr[i]= sc.nextInt();
        }
        System.out.println("Enter the Key")
        int key = sc.nextInt();
        System.out.println("Element found on - "+Search(arr, key));
    } 
}

}
