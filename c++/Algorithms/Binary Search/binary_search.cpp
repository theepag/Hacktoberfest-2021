#include <iostream>
#include <vector>

using std::vector;

int count = 0,divide=0,conquer=0;

int binary_search(const vector<int> &a,int left,int right,int x) {
    conquer++;
    count++;// function call :
    count++;//if condition checking either true/false
    if(left<=right){

        int mid = (left+right)/2;
        count++;//initialization of mid
        divide++;//dividing array into sub-array
        count++;//condition check
        if(a[mid]<x){
            count++;//functioncall
            //conquer++;//solving sub-problem
			return binary_search(a,mid+1,right,x);
        }
        else if(a[mid]>x){
            count++;//function call
            //conquer++;
			return binary_search(a,left,mid-1,x);
        }
        else{
            count++;//returning mid
            //conquer++;
            return mid;
        }
    }
    //conquer++;
  count++;//return
  return -1;
}


int main() {
  int n;
  std::cin >> n;
  vector<int> a(n);
  for (int i = 0; i < a.size(); i++) {
    a[i]=i+1;
  }
  int m;
  std::cout<<"Enter number to be searched\n";
  std::cin >> m;
  std::cout << binary_search(a,0,a.size()-1,m) << ' '<<"th index \n";
  count++;//function call
  std::cout<<"Total Instructions : "<<count<<"\n";
  std::cout<<"Total Number of divide : "<<divide<<"\n";
  std::cout<<"total Number of conquer : "<<conquer<<"\n";
}
