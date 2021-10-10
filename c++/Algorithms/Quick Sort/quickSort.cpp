/*
#include<iostream>
using namespace std;


int divide=0,conquer=0,counti=0;
int partitionArray(int arr[],int left,int right){
    int pivot = arr[left];
    counti++;//initialization
    int j = left;
    counti++;//initialization
    for(int i =left+1;i<=right;i++){
            counti++;//for loop condition true;
        if(arr[i]<= pivot){
            counti++;//if condition true
            j++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            counti+=4;//swap operation
        }
        counti++;//if condition false
        counti++;//incrementing i
    }
    counti++;//exiting for loop
    int temp = arr[j];
    arr[j] = pivot;
    arr[left] = temp;
    counti+=3;//swap operation
    counti++;//return statement
    return j;

}

void quickSort(int arr[],int left,int right) {
        conquer++;
        counti++;
        if(left<right){
            divide++;
            counti++;
            int m = partitionArray(arr,left,right);
            counti++;
            quickSort(arr,left,m-1);
            quickSort(arr,m+1,right);
        }

        return;
    }



int main(){
int n;
cin>>n;

int arr[n];
for(int i =0;i<n;i++){
    cin>>arr[i];
}
quickSort(arr,0,n-1);
conquer++;
counti++;
for(int i=0;i<n;i++){
    cout<<arr[i]<<" ";
}

cout<<"\nTotal no. of divide instructions : "<<divide<<"\n";
cout<<"Total no. of conquer instructions : "<<conquer<<"\n";
cout<<"Total no. of instructions : "<<counti<<"\n";
}
*/



#include<iostream>
using namespace std;


int divide=0,conquer=0,counti=0;
int partitionArray(int arr[],int left,int right){
    int pivot = arr[left];
    counti++;//initialization pivot
    int j = left;
    counti++;//initialization j
    counti++ ;// i initialization
    for(int i =left+1;i<=right;i++){
            counti++;//for loop condition true;
        if(arr[i]<= pivot){
            counti++;//if condition true
            j++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            counti+=4;//swap operation
        }
        counti++;//if condition false
        counti++;//incrementing i
    }
    counti++;//exiting for loop
    int temp = arr[j];
    arr[j] = pivot;
    arr[left] = temp;
    counti+=3;//swap operation
    counti++;//return statement
    return j;

}

void quickSort(int arr[],int left,int right) {
        conquer++;
        counti++; // function call : quicksort
        counti++; //if condition checking either true/false
        if(left<right){
            divide++;
            counti++; // for m
            counti++; // function call partitionarray
            int m = partitionArray(arr,left,right);
            //counti++;
            quickSort(arr,left,m-1);
            quickSort(arr,m+1,right);
        }
       counti++; // function return statement
        return;
    }



int main(){
int n;
cin>>n;

int arr[n];
for(int i =0;i<n;i++){
    cin>>arr[i];
}
quickSort(arr,0,n-1);
//conquer++;
//counti++;
for(int i=0;i<n;i++){
    cout<<arr[i]<<" ";
}

cout<<"\nTotal no. of divide instructions : "<<divide<<"\n";
cout<<"Total no. of conquer instructions : "<<conquer<<"\n";
cout<<"Total no. of instructions : "<<counti<<"\n";
}
