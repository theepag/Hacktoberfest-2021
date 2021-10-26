import java.util.Scanner;
import java.util.Random;

public class InsertionSort {

    public static void insertionSort(int[] array)
    {
        int n = array.length;
        for (int i=1; i<n; ++i)
        {
            int key = array[i];
            int j = i-1;
  
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && array[j] > key)
            {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
            System.out.println("Array after sorting element "+ key);
            printList(array);
        }
        System.out.println("Sorted Array: ");
        printList(array);
    }

    public static void printList(int[] array)
    {
        String result = "";
        for(int i = 0; i < array.length; i++)
        {
            result += array[i] + ", ";
        }
        System.out.println(result);
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Random random = new Random(); 

        System.out.println("Enter array size: ");
        int arrSize = input.nextInt();
        int[] numArray = new int[arrSize];

        System.out.println("Enter Max number in array");
        int maxValue = input.nextInt();

        for(int i = 0; i < arrSize; i++)
        {
            int value = random.nextInt(maxValue);
            numArray[i] = value;
        }

        System.out.println("Array before sorting: ");
        printList(numArray);

        insertionSort(numArray);

        input.close();
    }
}
