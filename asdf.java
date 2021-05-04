import java.util.*;

public class asdf {
    public static void main(String[] args) {
        test_bst();
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        BSTHeapTree<Integer> bst = new BSTHeapTree<>();
        int temp;
        for(int i = 0; i < 20; i++){
            temp = random.nextInt(60);
            bst.add(temp);
            arrayList.add(temp);

        }
        System.out.println(bst);
        Collections.sort(arrayList);
        for(int i = 0; i < 10; i++){
            temp = arrayList.get(0);
            System.out.println(temp + " try 2");

            bst.remove(arrayList.remove(0));
            System.out.println(bst);
        }
        System.out.println(bst);

        for(int i = 0; i < 20; i++){
            temp = random.nextInt(60);
            bst.add(temp);
            arrayList.add(temp);

        }
        System.out.println(bst);
        Collections.sort(arrayList);
    }


    public static void test_bst(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        BSTHeapTree<Integer> bstHeapTree = new BSTHeapTree<>();
        Random random = new Random();
        int temp;
        for(int i = 0; i < 3000; i++){
            temp = random.nextInt(5000);
            arrayList.add(temp);
            bstHeapTree.add(temp);
        }
        Collections.sort(arrayList);
        int count = 1;

        System.out.println(bstHeapTree);

        System.out.println("finding occurrences of existing elements");
        for(int i = 0, j = 0; i < 100; i++, j++){
            System.out.println("element to find occurrence: " + arrayList.get(j));
            while (arrayList.get(j).equals(arrayList.get(j + 1))){
                count++;
                j++;
            }
            System.out.println("arraylist result: " + count + "\n");
            count = 1;
        }

        for(int i = 0; i < 10; i++){
            System.out.println("element to find occurrence: " + (i + 5001));
            System.out.println("arraylist result: " + arrayList.indexOf(i + 5001) + "\n");
        }

        System.out.println("\nfinding mode");
        int mode = arrayList.get(0);
        int index = 0;
        while (index < arrayList.size()){
            temp = occurrences(arrayList, arrayList.get(index));
            if(temp > count){
                count = temp;
                mode = arrayList.get(index);
            }
            index += temp;
        }
        System.out.println("arraylist result: " + mode);

        System.out.println(arrayList);
        System.out.println("size of arraylist: " + arrayList.size());
        System.out.println(bstHeapTree);
        for(int i = 0; i < 1500; i++){
            temp = arrayList.get(0);
            System.out.println("element to remove: " + temp);
            System.out.println("occurrence before removal");
            try {
            } catch (Exception e) {
                System.out.println("__91__");
                break;
            }
            System.out.println("arraylist result: " + occurrences(arrayList, temp));
            System.out.println("occurrence after removal");
            try {
                System.out.println("tree result: " + bstHeapTree.remove(arrayList.remove(0)));
            } catch (Exception e) {
                System.out.println("__99__");
                break;
            }
            System.out.println("arraylist result: " + occurrences(arrayList, temp) + "\n");
        }

        for(int i = 0; i < 1500; i++){
            temp = random.nextInt(5000);
            arrayList.add(temp);
            bstHeapTree.add(temp);
        }
        Collections.sort(arrayList);

        for(int i = 0; i < 3000; i++){
            temp = arrayList.get(0);
            System.out.println("element to remove: " + temp);
            System.out.println("occurrence before removal");
            try {
            } catch (Exception e) {
                System.out.println(bstHeapTree);
                System.out.println("__119__");
                System.out.println(e);
                break;
            }
            System.out.println("arraylist result: " + occurrences(arrayList, temp));
            System.out.println("occurrence after removal");
            try {
                System.out.println("tree result: " + bstHeapTree.remove(arrayList.remove(0)));
            } catch (Exception e) {
                System.out.println(bstHeapTree);
                System.out.println("__129__");
                break;
            }
        }

        for(int i = 0; i < 10; i++){
            System.out.println("element to remove: " + (i + 5001));
            try {
                bstHeapTree.remove((i + 5001));
            } catch (NoSuchElementException e) {
                System.out.println("tree result: " + e);
            }
        }
    }

    public static int occurrences(ArrayList<Integer> arrayList, Integer target){
        int startIndex = arrayList.indexOf(target);
        if(startIndex == -1){
            return 0;
        }
        int count = 0;
        while (startIndex < arrayList.size() && arrayList.get(startIndex).equals(target)){
            count++;
            startIndex++;
        }
        return count;
    }
}