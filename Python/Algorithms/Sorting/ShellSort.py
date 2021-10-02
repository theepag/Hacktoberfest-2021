# Python Program for Shell sort, including Knuth sequence for selecting gaps
# Shell Sort Algorithm: https://en.wikipedia.org/wiki/Shellsort
from random import *

def ShellSort(arr):
    gap = nextGap(len(arr))
    while gap > 0:
        for i in range(gap, len(arr)):

            # we need a temp variable to temporarily store the value before any swapping takes place
            temp = arr[i]

            # shift earlier gap-sorted elements up until the correct location for arr[i] is found
            j = i
            while j >= gap and arr[j-gap] > temp:
                arr[j] = arr[j-gap]
                j -= gap
            
            # put the temp (arr[i] originally) into it's correct location
            arr[j] = temp

        # now grab next gap value until we get down to 0
        print("array after shell gap value of %d:" %gap)
        printList(arr)
        gap = nextGap(gap)

# This method selects what the next gap value is going to be for sorting, using the Knuth Sequence formula.
def nextGap(previousGapValue):
    # Knuth gap sequence is set with gap = 3gap + 1 upto gap < data.length
    # So to reverse that direction (start with largest gap and whittle down) we return the previous gap value minus one divided by 3
    if (previousGapValue == 1):
        return 0
    elif((previousGapValue - 1)/3 <= 1):
        return 1
    else:
        return int(round((previousGapValue - 1)/3))


# I got sick of writing a bunch of loops to print out the list in a (relatively) readable format, so this method does it for you!
def printList(arr):
    n = len(arr)
    valueList = ""
    for i in range(n):
        valueList = valueList + "%d, " % arr[i]
    print(valueList)

# Code to test out sort
arr = []

# You can replace the number of values with anything, I find 98 is a good one to start because then you get to see gaps of 32, 10, 3 and the final with a gap of 1.
for i in range(98):
    arr.append(randint(1, 100))

print("Array before sorting")
printList(arr)
ShellSort(arr)
print("Array after sorting")
printList(arr)