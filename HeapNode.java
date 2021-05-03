public class HeapNode<E extends Comparable<E>> implements Comparable<HeapNode<E>>{
    private E data = null;
    private int frequency = 0;

    public HeapNode(E item){
        data = item;
        frequency = 1;
    }

    public void changeFrequecy(int freq){
        frequency = freq;
    }

    public String toString() {
        return(data.toString() + "," + frequency);
    }

    /**
     * Compares datas of the HeapNodes.
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
