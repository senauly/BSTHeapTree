/**
 * HeapNode Class representing node of the Heap with data and frequency fields.
 */
public class HeapNode<E extends Comparable<E>> implements Comparable<HeapNode<E>>{
    private E data = null;
    private int frequency = 0;

    public HeapNode(){
        data = null;
        frequency = 0;
    }
    
    public HeapNode(E item){
        data = item;
        frequency = 1;
    }

    /**
     * change frequency of the node.
     * @param freq of the node.
     */
    public void changeFrequecy(int freq){
        frequency = freq;
    }
    
    /**
     * display node with its data and frequency.
     */
    public String toString() {
        return(data.toString() + "." + frequency);
    }

    /**
     * get frequency.
     * @return frequency
     */
    public int getFrequency(){
        return frequency;
    }

    /**
     * Get data.
     * @return data
     */
    public E getData(){
        return data;
    }

    /**
     * Compare datas of the HeapNodes.
     * @param o other HeapNode to compare.
     * @return 1 if data is bigger, -1 if smaller and 0 if they're equal.
     */
    @Override
    public int compareTo(HeapNode<E> o) {
        if(data.compareTo(o.data) < 0) return -1;
        if(data.compareTo(o.data) > 0) return 1;
        else return 0;
    }
}
