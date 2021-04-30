import java.util.PriorityQueue;

/**
 * Heap Class with extend features like searching for an element, merging heaps,
 * removing ith largest element and extended iterator class with set method.
 * @author Sena Ulukaya
 * 
 */
public class Heap<E> {
    private PriorityQueue<E> data;

    /**
     * Constructor
     */
    public Heap(){
        //TO-DO
    }
    
    public HeapIterator<E> iterator(){
        return new HeapIter();
    }

    /**
     * 
     * @param element for searching
     * @return true when element is found, false otherwise.
     */
     public Boolean searchElement(E element){
        return true;
    }

    /**
     * 
     * @param other heap for merging 2 heaps.
     */
    public void merge(PriorityQueue<E> other){
        //TO-DO
    }

    /**
     * 
     * @param index is the one which will be removed.
     * @return the removed element if it does exist.
     * @throws IndexOutOfBoundsException if parameter is out of bounds of the heap.
     */
    public E removeLargestIth(int index) throws IndexOutOfBoundsException{
        //TO-DO
        return null;
    }

    private class HeapIter implements HeapIterator<E> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public E next() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void set(E value) {
            // TODO Auto-generated method stub
            
        }

    }
}