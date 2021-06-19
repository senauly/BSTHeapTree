import java.util.*;

public class Test{
    public static int findNextItem(int index, ArrayList<Integer> arr){
        if(index < 0 || index >= arr.size()) throw new ArrayIndexOutOfBoundsException();
        return(index + findFreq(arr.get(index), arr));
    }

    public static int findFreq(int item, ArrayList<Integer> arr){
        int index = arr.indexOf(item);
        if(index == -1) throw new NoSuchElementException();
        int i = 0;
        
        while(index+i < arr.size() && arr.get(index+i) == item){
            i++;
        }

        return(i);
    }

    public static ArrayList<Integer> findModeArray(ArrayList<Integer> arr){
        ArrayList<Integer> mode = new ArrayList<>();
        int maxFreq = findFreq(arr.get(0), arr);

        for (int j = 0; j < arr.size(); j++) {
            if(findFreq(arr.get(j), arr) > maxFreq) maxFreq = findFreq(arr.get(j), arr);
        }

        for (int i = 0; i < arr.size(); i++){
            if(findFreq(arr.get(i), arr) == maxFreq && !mode.contains(arr.get(i))) mode.add(arr.get(i));
        }
        
        return mode;
    }

    public static void part2() {
        ArrayList<Integer> array = new ArrayList<>();
        BSTHeapTree<Integer> bst = new BSTHeapTree<>();
        Random rand = new Random();
        
        for(int i = 0; i < 3000; i++){
            int temp = rand.nextInt(5000);
            array.add(temp);
            bst.add(temp);
        }

        Collections.sort(array);
        System.out.println("3000 numbers added to BSTHeapTree and ArrayList.\n");

        System.out.println("Frequency Test:");
        System.out.println("I will find the frequency of the same numbers both in arrayList and BSTHeapTree.\n");
        
        int count = 0;
        int nextIndex = 0;
        int nextItem;
        
        while(count != 100){
            nextItem = array.get(nextIndex);
            
            try{
                System.out.println("Number of occurrences of " + nextItem + " in the BSTHeapTree: " + bst.find(nextItem));
            }

            catch(NoSuchElementException e){
                System.err.println("Number of occurrences of " + nextItem + " in the BSTHeapTree: 0. Item does not exist.");
            }
            
            try{
                System.out.println("Number of occurrences of " + nextItem + " in the arrayList: " + findFreq(nextItem, array));
                nextIndex = findNextItem(nextIndex, array);
            }

            catch(Exception e){
                System.err.println("Number of occurrences of " + nextItem + " in the arrayList: 0. Item does not exist.");
                nextIndex++;
            }

            count ++;
            System.out.println("\n");
        }

        count = 0;
        nextItem = 5001;
        while(count != 10){
            try{
                System.out.print("Number of occurrences of " + nextItem + " in the BSTHeapTree: " + bst.find(nextItem));
            }

            catch(NoSuchElementException e){
                System.err.println("Number of occurrences of " + nextItem + " in the BSTHeapTree: 0. Item does not exist.");
            }
            
            try{
                System.out.println("Number of occurrences of " + nextItem + " in the arrayList: " + findFreq(nextItem, array));
                nextIndex = findNextItem(nextIndex, array);
            }

            catch(Exception e){
                System.err.println("Number of occurrences of " + nextItem + " in the arrayList: 0. Item does not exist.");
                nextIndex++;
                nextItem++;
            }

            count ++;
            System.out.println("\n");
        }

        System.out.println("\nMode Test:\n");

        if(bst.find_mode() != null) System.out.println("Mode of the BSTHeapTree: " + bst.find_mode() + " with frequency " + bst.find(bst.find_mode()));
        else System.out.println("BSTHeapTree is empty.");
        
        System.out.print("Mode of the arrayList: ");
        ArrayList<Integer> modeList = findModeArray(array);
        System.out.println(modeList + " with frequency " + findFreq(modeList.get(0), array));

        System.out.println("\nRemove Test:\n");

        count = 0;
        nextIndex = 100;

        while(count != 100){
            nextItem = array.get(nextIndex);
            
            try{
                System.out.println("Number of occurrences of " + nextItem + " before removeing in the BSTHeapTree: " + bst.find(nextItem));
                nextIndex = findNextItem(nextIndex, array);
                System.out.println("Number of occurrences of " + nextItem + "  after removing in the BSTHeapTree: " + bst.remove(nextItem));
            }

            catch(NoSuchElementException e){
                System.err.println(nextItem + " does not exist. You can't remove.");
                nextIndex++;
            }
            
            count ++;
            System.out.println("\n");
        }

        count = 0;
        nextItem = 5001;
        while(count != 10){
            try{
                System.out.println("Number of occurrences of " + nextItem + " before removing in the BSTHeapTree: " + bst.find(nextItem));
                nextIndex = findNextItem(nextIndex, array);
                System.out.println("Number of occurrences of " + nextItem + "  after removing in the BSTHeapTree: " + bst.remove(nextItem));
            }

            catch(NoSuchElementException e){
                System.err.println(nextItem + " does not exist. You can't remove.");
                nextIndex++;
                nextItem++;
            }
            
            count ++;
        }

    }
    
    public static void displayHeap(Heap<Integer> heap,HeapIterator<Integer> it){
        it = heap.heapIterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
    }
    public static void part1(){
        Heap<Integer> heap1 = new Heap<>();
        Heap<Integer> heap2 = new Heap<>();

        for (int i = 1; i < 51; i++) {
            heap1.add(i);
        }

        for (int i = 30; i < 71; i++) {
            heap2.add(i);
        }

        System.out.println("Numbers from 50 to 1 added to heap1.");
        System.out.println("Heap1:");
        HeapIterator<Integer> it1 = heap1.heapIterator();
        displayHeap(heap1, it1);

        System.out.println("\n\nNumbers from 70 to 30 added to heap2.");

        HeapIterator<Integer> it2 = heap2.heapIterator();
        System.out.println("Heap2:");
        displayHeap(heap2, it2);

        System.out.println("\n\nSEARCH FOR AN ELEMENT:\n");
        
        for (int i = 0; i < 100; i = i+10) {
            System.out.println("Searching for " + i + " in heap 1.");
            if(heap1.searchElement(i)) System.out.println("Element does exist.");
            else System.out.println("Element does not exist.");
            System.out.println();
        }

        System.out.println("\nMERGE 2 HEAPS:\n");
        System.out.println("Merging heap1 and heap2 in heap1.");
        try{
            heap1.merge(heap2);
        }

        catch(IllegalArgumentException e){
            System.err.println("Some property of an element of the specified Heap prevents it from being added to this queue, or if the specified Heap is this heap.");
        }

        catch(ClassCastException e){
            System.err.println("Class of an element of the specified Heap prevents it from being added to this heap.");
        }

        catch(NullPointerException e){
            System.err.println("Specified Heap contains a null element and this Heap does not permit null elements, or if the specified collection is null.");
        }

        catch(IllegalStateException e){
            System.err.println("Not all the elements can be added at this time due to insertion restrictions.");
        }

        System.out.println();
        it1 = heap1.heapIterator();
        System.out.println("Heap1:");
        displayHeap(heap1, it1);
        System.out.println();
        it2 = heap2.heapIterator();
        System.out.println("Heap2:");
        displayHeap(heap2, it2);

        System.out.println("\n");
        System.out.println("Merging heap2 with itself.");
        try{
            heap2.merge(heap2);
        }

        catch(IllegalArgumentException e){
            System.err.println("Some property of an element of the specified Heap prevents it from being added to this queue, or if the specified Heap is this heap.");
        }

        catch(ClassCastException e){
            System.err.println("Class of an element of the specified Heap prevents it from being added to this heap.");
        }

        catch(NullPointerException e){
            System.err.println("Specified Heap contains a null element and this Heap does not permit null elements, or if the specified collection is null.");
        }

        catch(IllegalStateException e){
            System.err.println("Not all the elements can be added at this time due to insertion restrictions.");
        }

        System.out.println();
        it1 = heap1.heapIterator();
        System.out.println("Heap1:");
        displayHeap(heap1, it1);
        System.out.println();
        it2 = heap2.heapIterator();
        System.out.println("Heap2:");
        displayHeap(heap2, it2);
        System.out.println("\n");

        System.out.println("\n\nREMOVE ITH LARGEST ELEMENT:\n");

        System.out.println("\nHeap1 before removing:");
        it1 = heap1.heapIterator();
        displayHeap(heap1, it1);
        System.out.println("\n");
        
        System.out.println("Removing smallest that is " + heap1.size() + "th largest element from heap1. Size: " + heap1.size());
        try{
            System.out.println(heap1.removeLargestIth(heap1.size()) + " removed from heap1. New size: " + heap1.size());
        }

        catch(IndexOutOfBoundsException e){
            System.err.println("There's no largest " + heap1.size() + "th element of this heap.");
        }
        System.out.println();
        
        System.out.println("Removing largest that is 1th largest element from heap1. Size: " + heap1.size());
        try{
            System.out.println(heap1.removeLargestIth(1) + " removed from heap1. New size: " + heap1.size());
        }

        catch(IndexOutOfBoundsException e){
            System.err.println("There's no largest " + heap1.size() + "th element of this heap.");
        }

        System.out.println();

        for (int i = 12; i < 100; i = i+12) {
            System.out.println("Removing " + i + "th largest element from heap1. Size: " + heap1.size());
            
            try{
                System.out.println(heap1.removeLargestIth(Integer.valueOf(i)) + " removed from heap1.New size: " + heap1.size());
            }

            catch(IndexOutOfBoundsException e){
                System.err.println("There's no largest " + i + "th element of this heap. The size is just " + heap1.size());
            }
            
            System.out.println();
        }

        System.out.println("\nHeap1 after removing:");
        it1 = heap1.heapIterator();
        displayHeap(heap1, it1);
        System.out.println("\n");


        System.out.println("\n\nSET METHOD OF ITERATOR:\n");

        it2 = heap2.heapIterator();
        System.out.println("Heap2 before setting any element: ");
        displayHeap(heap2, it2);
        it2 = heap2.heapIterator();
        System.out.println("\n");
        while(it2.hasNext()){
            int element = it2.next();
            if(element % 15 == 0){
                System.out.println("Setting " + (element+2) + " instead of " + element);
                it2.set(element+2);
            }
        }

        System.out.println("\n");
        it2 = heap2.heapIterator();
        System.out.println("Heap2 after setting:");
        displayHeap(heap2, it2);
        System.out.println("\n");

        

    }
    public static void main(String[] args) {
        System.out.println("------------------PART 1 TEST------------------\n\n");
        part1();
        System.out.println("------------------PART 2 TEST------------------\n\n");
        part2();
    }
}
