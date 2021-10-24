#include <iostream>
#define SIZE 10

using namespace std;

void swap(int arr[],int firstIndex, int secondIndex)
{
    int tmp = arr[firstIndex];
    arr[firstIndex] = arr[secondIndex];
    arr[secondIndex] = tmp;
}

void bubbleSort(int arr[],int sizeArr)
{
    for(int i = 0;i < sizeArr-1;i++)
        for(int j = 0;j < sizeArr-i-1;j++)
            if(arr[j] > arr[j+1])
                swap(arr,j,j+1);
}

void printArr(int arr[],int sizeArr)
{
    for(int i = 0;i < sizeArr;i++)
        cout<<arr[i]<<" ";
    cout<<"\n";
}

int main()
{
    int arr[SIZE] = {4,342,1,23,4,1,34,566,32};

    bubbleSort(arr,sizeof(arr)/4);

    printArr(arr,sizeof(arr)/4);
}


