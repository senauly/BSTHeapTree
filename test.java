import java.util.*;

public class Test{
    public static int findFreqArray(int index, ArrayList<Integer> arr){
        if(index < 0 || index > arr.size()) throw new ArrayIndexOutOfBoundsException();
        int item = arr.get(index);
        int i = 0;
        
        //System.out.print("\nNumber of occurrences the " + item + " in the arraylist: ");
        
        while(arr.get(index+i) == item){
            i++;
        }

        //System.out.println(i);
        return(index+i);
    }

    public static int findFreqNum(int item, ArrayList<Integer> arr){
        int index = arr.indexOf(item);
        return(findFreqArray(index, arr) - index);
    }

    public static ArrayList<Integer> findModeArray(ArrayList<Integer> arr){
        int max = findFreqArray(0,arr);
        ArrayList<Integer> mode = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < arr.size(); i = i + max) {
            if(findFreqNum(arr.get(i),arr) > max){
                max = findFreqNum(arr.get(i),arr);
                temp.add(i);
            }
        }

        for (int i = 0; i < temp.size(); i = i + max) {
            if(findFreqNum(temp.get(i),arr) == max) mode.add(temp.get(i));
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
        System.out.println("3000 numbers added to BSTHeapTree and ArrayList.\n\n");
        System.out.println(bst);
        System.out.println();
        System.out.println(array);

        /*
        System.out.println("\nFrequency Test:");
        System.out.println("I will find the frequency of the same numbers both in arrayList and BSTHeapTree.\n");
        
        int count = 0;
        int nextItem = 0;
        
        while(count != 100){
            try{
                System.out.print("Number of occurrences of " + array.get(nextItem) + " in the BSTHeapTree: " + bst.find(array.get(nextItem)));
            }

            catch(NoSuchElementException e){
                System.err.print("Number of occurrences of " + array.get(nextItem) + " in the BSTHeapTree: 0. Item does not exist.");
            }
            
            try{
                nextItem = findFreqArray(nextItem, array);
            }

            catch(ArrayIndexOutOfBoundsException e){
                System.err.print("Number of occurrences of " + nextItem + " in the arraylist: 0. Item does not exist.");
                nextItem++;
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
                System.err.print("Number of occurrences of " + nextItem + " in the BSTHeapTree: 0. Item does not exist.");
            }
            
            try{
                nextItem = findFreqArray(nextItem, array);
            }

            catch(ArrayIndexOutOfBoundsException e){
                System.err.print("\nNumber of occurrences of " + nextItem + " in the arraylist: 0. Item does not exist.");
                nextItem++;
            }

            count ++;
            System.out.println("\n");
        }
        */

        System.out.println("\nMode Test:\n");

        if(bst.find_mode() != null) System.out.println("Mode of the BSTHeapTree: " + bst.find_mode() + " with frequency " + bst.find(bst.find_mode()));
        else System.out.println("BSTHeapTree is empty.");
        
        ArrayList<Integer> modeList = findModeArray(array);
        System.out.println(modeList.size());
        /*System.out.print("\n Mode of the arrayList: ");
        ArrayList<Integer> modeList = findModeArray(array);
        for (int j = 0; j < modeList.size(); j++) {
            System.out.print(modeList.get(j) + " ");
        }

        System.out.print("\n with frequency " + findFreqNum(modeList.get(0), array));*/
    }

    public static void main(String[] args) {
        part2();
    }
}
