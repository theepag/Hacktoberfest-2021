import java.util.Scanner;
import java.util.Random;

public class BubbleSort {

    public static void bubbleSort(int[] array)
    {
        int temp;
        int n = array.length;
        for(int i = 0; i < n; i++)
        {
            for(int j = 1; j < n - i; j++)
            {
                if(array[j-1] > array[j])
                {
                    // swap the elements
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
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

        bubbleSort(numArray);

        input.close();
    }
}
