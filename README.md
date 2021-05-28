# BSTHEAPTREE

## Table of contents
* [General info](#general-info)
* [Setup](#setup)

## General info
This is my homework for Data Structures and Algorithm class.

It contains extended Heap class with the search, merge, remove ith element and also, set feauture for the Iterator class.
* search: Search for an element.
* merge: Merge with another heap.
* remove: Removing ith largest element from the Heap.
* set: set the value passed as parameter of the last element returned by the next methods.

I used this Heap structure in the BSTHeapTree. BSTHeapTree keeps the elements in a Binary Search Tree where the nodes store max-Heap with a maximum depth of 2. It has add, remove, find and find mode features.
* Each node in the heap holds two data: a value and the number of occurrences of the value.
* Remove operation removes only one occurrence of the value. If the number of occurrences becomes zero than the value will be removed. 
* During a remove operation, if the heap at a node becomes empty, the corresponding BST node will be removed as well.

There's a test class where I tested each method at BSTHeapTree as follows:

1. Inserted 3000 numbers that are randomly generated in the range 0-5000 into the BSTHeapTree. Stored these numbers in an array and sorted the numbers to found the number occurrences of all the numbers.
2. Searched for 100 numbers in the array and 10 numbers not in the array and make sure that the number of occurrences is correct.
3. Found the mode of the BSTHeapTree. Checked whether the mode value is correct by comparing it with the array.
4. Removed 100 numbers in the array and 10 numbers not in the array and checked that the number of occurrences after removal is correct.

## Setup
To run this project in terminal:

```
$ cd ../lorem
$ javac *.java
$ java Test
```

or use the makeFile,
```
$ cd ../lorem
$ make
```

 
