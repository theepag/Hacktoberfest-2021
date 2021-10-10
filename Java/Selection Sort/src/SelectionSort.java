import java.util.Arrays;
import java.util.Scanner;

public class SelectionSort {
    public static int[] selectionSort (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int lowerIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (j < arr[lowerIndex]) {
                    lowerIndex = j;
                }
            }
            int lowerNum = arr[lowerIndex];
            arr[lowerIndex] = arr[i];
            arr[i] = lowerNum;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int arrSize = input.nextInt();

        int[] arr = new int[arrSize];

        for (int i = 0; i < arr.length; i++) {
            System.out.print("Enter a number: ");
            arr[i] = input.nextInt();
        }
        System.out.println("Array before Selection Sort:");
        System.out.println(Arrays.toString(arr));
        System.out.println("Array after Selection Sort:");
        System.out.println(Arrays.toString(selectionSort(arr)));
    }
}
