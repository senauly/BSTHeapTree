import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Heap Class with extend features like searching for an element, merging heaps,
 * removing ith largest element and extended iterator class with set method.
 * @author Sena Ulukaya
 * 
 */
public class Heap<E extends Comparable<E>> extends PriorityQueue<E> implements Comparable<Heap<E>>{
    /**
     * Constructor is calling PriorityQueue's constructor as its base class.
     */
    public Heap(){
        super();
    }

    /**
     * Creates a Heap with the capacity 7 that orders its elements according to the specified comparator.
     * @param comp the comparator that will be used to order this heap.
     */
    public Heap(Comparator<? super E> comp){
        super(7, comp);
    }
    
    /**
     * Initialize a new heapIterator for the heap.
     * @return the iterator.
     */
    public HeapIterator<E> heapIterator(){
        return new HeapIter();
    }

    /**
     * Search for an element.
     * @param element for searching
     * @return true when element is found, false otherwise.
     */
     public Boolean searchElement(E element){
        HeapIterator<E> it = heapIterator();
        
        while(it.hasNext()){
            if(it.next().equals(element)) return true;
        }

        return false;
    }
  
    /**
     * Merge this heap with another heap.
     * @param other heap for merging 2 heaps.
     * @throws ClassCastException if the class of an element of the specified Heap prevents it from being added to this heap.
     * @throws NullPointerException - if the specified Heap contains a null element and this Heap does not permit null elements, 
     * or if the specified collection is null.
     * @throws IllegalArgumentException - if some property of an element of the specified Heap prevents it from being added to this queue, 
     * or if the specified collection is this queue
     * @throws IllegalStateException - if not all the elements can be added at this time due to insertion restrictions
     */
    public void merge(Heap<E> other) throws ClassCastException,NullPointerException,IllegalArgumentException,IllegalStateException {
        this.addAll(other);
    }

    /**
     * Remove ith largest element from the Heap.
     * @param index is the ith element order which will be removed from the heap.
     * @return the removed element if it does exist.
     * @throws IndexOutOfBoundsException if parameter is out of bounds of the heap.
     */
    @SuppressWarnings("unchecked")
    public E removeLargestIth(int index) throws IndexOutOfBoundsException{
        if(index <= 0 || index > this.size()) throw new IndexOutOfBoundsException();
        Object[] arr = this.toArray();
        Arrays.sort(arr);
        E element = (E) arr[this.size()-index];
        if(this.remove(element)) return element;
        return null;
    }
    
    /**
     * display the heap.
     */
    @SuppressWarnings("unchecked")
    public void display(){
        E[] arr = (E[])this.toArray();
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));      
    }

    /**
     * Compares peek values of the heaps.
     * @param o other heap to compare.
     * @return 1 if heap's peek is bigger, -1 if smaller and 0 if they're equal.
     */
    @Override
    public int compareTo(Heap<E> o) {
        if(this.peek().compareTo(o.peek()) < 0) return -1;
        if(this.peek().compareTo(o.peek()) > 0) return 1;
        else return 0;
    }
    
    /**
     * HeapIter class with set method. 
     */
    private class HeapIter implements HeapIterator<E> {
        private Iterator<E> it;
        private E lastItem = null;
        private int nextCount = 0;

        public HeapIter(){
            it = iterator();
            lastItem = null;
        }
        
        /**
         * Indicate whether movement forward is defined.
         * @return true if call to next will not throw an exception
         */
        @Override
        public boolean hasNext() {
            return(it.hasNext());
        }

        /** Move the iterator forward and return the next item.
         * @return The next item in the list
         * @throws NoSuchElementException if there is no such object
         */
        @Override
        public E next() throws NoSuchElementException{
            lastItem = it.next();
            nextCount++;
            return(lastItem);
        }

         /** Remove the last item returned. This can only be done once per call to next.
         *  @throws IllegalStateException if next was not called prior to calling this method.
         */
        @Override
        public void remove() throws IllegalStateException{
            it.remove();
        }

        /** Set the last element returned by next() with the value.
         * @param value which to replace the last element returned by next.
         * @throws IllegalStateException if next() has not called yet.
         */
        @Override
        public E set(E value) throws IllegalStateException{
            if(lastItem == null) throw new IllegalStateException();
            it.remove();
            offer(value);

            it = iterator();
            for (int i = 0; i < nextCount; i++) {
               it.next();
            }

            E item = lastItem;
            lastItem = null;
            return item;
        }

    }
}