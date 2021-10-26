import java.util.Scanner;
import java.util.Random;

public class ShellSort {

    // Normally best practice would be to have these methods part of a separate class so that they aren't static, but I'm lazy.
    public static void shellSort(int[] array)
    {
        int gap = nextGap(array.length);
        int temp, j;
        while(gap > 0)
        {
            for(int i = gap; i < array.length; i++)
            {
                // we need a temp variable to store the value before any swapping takes place.
                temp = array[i];

                // shift earlier gap-sorted elements up until the correct location for array[i] is found
                j = i;
                while(j >= gap && array[j-gap] > temp)
                {
                    array[j] = array[j-gap];
                    j -= gap;
                }

                // Now put the temp value into it's correct location
                array[j] = temp;

                
            }
            System.out.println("Array after sorting with a gap value of " + gap + " :");
            printList(array);

            // Grab the next gap value until we get to 0
            int newGap = nextGap(gap);
            gap = newGap;
        }
        
    }

    public static int nextGap(int previousGapValue)
    {
        if (previousGapValue == 1)
            return 0;
        else if ((previousGapValue - 1)/3 <= 1)
            return 1;
        else
            return Math.round((previousGapValue - 1)/3);
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

        shellSort(numArray);

        input.close();
    }

    
}