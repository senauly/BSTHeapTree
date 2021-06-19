import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * BSTHeapTree Class for keeping Heap data nodes inside of the BST.
 */
public class BSTHeapTree<E extends Comparable<E>> {
    private BSTNode<Heap<HeapNode<E>>> root;

    /**
     * BSTNode inner class representing nodes of the BSTHeapTree.
     */
    private class BSTNode<T>{
        private T data = null;
        private BSTNode<T> right;
        private BSTNode<T> left;
        
        public BSTNode(){
            this.data = null;
            right = null;
            left = null;
        }
        
        public BSTNode(T data){
            this.data = data;
            right = null;
            left = null;
        }
    
        public String toString() {
            return(data.toString());
        }
    }

    public BSTHeapTree(){
        root = null;
    }

    public BSTHeapTree(BSTNode<Heap<HeapNode<E>>> root) {
        this.root = root;
    }

     /** Return the left subtree.
     @return The left subtree or null if either the root or the left subtree is null
     */
    public BSTHeapTree<E> getLeftSubtree(){
        if (root != null && root.left != null) return new BSTHeapTree<>(root.left);
        else return null;
    }

     /** Return the left subtree.
     @return The left subtree or null if either the root or the left subtree is null 
     */
    public BSTHeapTree<E> getRightSubtree(){
        if (root != null && root.right != null) return new BSTHeapTree<>(root.right);
        else return null;
    }

    /**
     * Check whether this tree is a leaf.
     * @return true if leaf else false.
     */
    public boolean isLeafNode() {
        return (root.left == null && root.right == null);
    }

    /**
     * Add Heap nodes.
     * @param data Heap to add into BSTHeapTree.
     */
    public void addNode(Heap<HeapNode<E>> data){
        root = addNodeRec(root, data);
    }

    /**
     * Remove Heap nodes.
     * @param data Heap to remove from BSTHeapTree.
     */
    public void removeNode(Heap<HeapNode<E>> data){
        root = removeNodeRec(root,data);
    }

    /**
     *  Insert item to the BSTHeapTree.
     * @param item to add into heap of the BSTHeapTree.
     * @return the number of occurrences of the item after insertion
     */
    public int add(E item){
        return(addItemRec(root, item));
    }

    /**
     *  Remove item from the BSTHeapTree.
     * @param item to remove from heap of the BSTHeapTree.
     * @return the number of occurrences of the item after removal.
     */
    public int remove(E item){
        return(removeItemRec(root, item));
    }

    /**
     * Find the number of occurrences of the item.
     * @param item to find.
     * @return the number of occurrences of the item in the BSTHeapTree
     */
    public int find(E item){
        return(findRec(root,item));
    }

    /**
     * Find mode of this BSTHeapTree.
     * @return the mode if exist, else null.
     */
    public E find_mode(){
        HeapNode<E> result = findModeRec(root, root.data.peek());
        if(result != null) return(result.getData());
        else return null;
    }

    /**
     * Recursively Add node to the BSTHeapTree.
     * @param <T> representing Heap<HeapNode<E>>
     * @param curr The current root of the subtree
     * @param data The Heap to be inserted
     * @return The current local root that now contains the inserted item
     */
    private <T extends Comparable<T>> BSTNode<T> addNodeRec(BSTNode<T> curr, T data){
        if (curr == null) {
            return new BSTNode<>(data);
        }
    
        if (data.compareTo(curr.data) < 0) curr.left = addNodeRec(curr.left, data);
        else if (data.compareTo(curr.data) > 0) curr.right = addNodeRec(curr.right, data);
        return curr;
    }

  /** 
   * Recursively remove node.
   * @param curr The root of the current subtree
   * @param data The heap to be deleted
   * @return The modified curr local root that does not contain the item.
   */
    private <T extends Comparable<T>> BSTNode<T> removeNodeRec(BSTNode<T> curr, T data){
        if(curr == null) return null;
        
        if (data.compareTo(curr.data) < 0) curr.left = removeNodeRec(curr.left, data);
        else if (data.compareTo(curr.data) > 0) curr.right = removeNodeRec(curr.right, data);
        
        else{
            
            if(curr.left == null) return curr.right;
            if(curr.right == null) return curr.left;
            else{
                if(curr.left.right == null){
                    curr.data = curr.left.data;
                    curr.left = curr.left.left;
                }

                else{
                    curr.data = getPeekNode(curr.left);
                }
            }
        }

        return curr;
    }

    /**
     * Get the peek node without removing.
     * @param <T> representing Heap<HeapNode<E>>
     * @param parent Heap node
     * @return The current parent node.
     */
    private <T extends Comparable<T>> T getPeekNode(BSTNode<T> parent){
        if (parent.right.right == null) {
            T peek = parent.right.data;
            parent.right = parent.right.left;
            return peek;
        }
        
        else return getPeekNode(parent.right);
    }

    /**
     * Add item recursively to the Heap Node of the BSTHeapTree.
     * @param curr Heap node
     * @param item to add
     * @return frequency of the item after adding.
     */
    private int addItemRec(BSTNode<Heap<HeapNode<E>>> curr,E item){
        if(curr == null){
            HeapNode<E> temp = new HeapNode<>(item);
            Heap<HeapNode<E>> newHeap = new Heap<>(Collections.reverseOrder());
            newHeap.add(temp);
            addNode(newHeap);
            return(1);
        }

        if(item.compareTo(curr.data.peek().getData()) <= 0){
            Iterator<HeapNode<E>> it = curr.data.iterator();
            while(it.hasNext()){
                HeapNode<E> temp = it.next();
                if(temp.getData().equals(item)){
                    temp.changeFrequecy(temp.getFrequency()+1);
                    return temp.getFrequency();
                } 
            }
        }

        if(curr.data.size() < 7){
            curr.data.add(new HeapNode<>(item));
            return 1;
        }

        if(item.compareTo(curr.data.peek().getData()) > 0) return(addItemRec(curr.right, item));
        else return(addItemRec(curr.left,item));
    }

    /**
     * Remove item recursively to the Heap Node of the BSTHeapTree.
     * @param curr Heap node
     * @param item to remove
     * @return frequency of the item after removing
     */
    private int removeItemRec(BSTNode<Heap<HeapNode<E>>> curr,E item){
        if(curr == null){
            throw new NoSuchElementException();
        }

        if(item.compareTo(curr.data.peek().getData()) <= 0){
            E peek = curr.data.peek().getData();
            Boolean removed = false;
            int freq = 0;
            Iterator<HeapNode<E>> it = curr.data.iterator();
            while(it.hasNext()){
                HeapNode<E> temp = it.next();
                freq = temp.getFrequency();
                if(temp.getData().equals(item) && freq > 1){
                    temp.changeFrequecy(--freq);
                    removed = true;
                }

                else if(temp.getData().equals(item) && freq == 1){
                    temp.changeFrequecy(--freq);
                    curr.data.remove(temp);
                    removed = true;
                }

                if(removed) break;
            }

            if(curr.data.isEmpty()){
                curr.data.add(new HeapNode<>(peek));
                removeNode(curr.data);
                return(freq);
            }

            if(removed){
                Heap<HeapNode<E>> backup = curr.data;
                removeNode(curr.data);
                Iterator<HeapNode<E>> i = backup.iterator();
                while(i.hasNext()){
                    HeapNode<E> hNode = i.next();
                    for(int j = 0; j < hNode.getFrequency(); ++j){
                        add(hNode.getData());
                    }
                }
                return(freq);
            }
        }

        if(item.compareTo(curr.data.peek().getData()) > 0) return(removeItemRec(curr.right, item));
        else return(removeItemRec(curr.left,item));

    }

    /**
     * Find the frequency of a item recursively.
     * @param curr Heap node.
     * @param item to find
     * @return frequency of the item.
     */
    private int findRec(BSTNode<Heap<HeapNode<E>>> curr,E item){
        if(curr == null){
            throw new NoSuchElementException();
        }

        if(item.compareTo(curr.data.peek().getData()) <= 0){
            Iterator<HeapNode<E>> it = curr.data.iterator();
            while(it.hasNext()){
                HeapNode<E> temp = it.next();
                if(temp.getData().equals(item)){
                    return temp.getFrequency();
                } 
            }
        }

        if(item.compareTo(curr.data.peek().getData()) > 0) return(findRec(curr.right, item));
        else return(findRec(curr.left,item));
    }

    /**
     * Find mode of the BSTHeapTree.
     * @param curr Heap node.
     * @param largestFreq of the current.
     * @return mode of the BSTHeapTree.
     */
    private HeapNode<E> findModeRec(BSTNode<Heap<HeapNode<E>>> curr, HeapNode<E> largestFreq){
        Iterator<HeapNode<E>> it = curr.data.iterator();
        while(it.hasNext()){
            HeapNode<E> temp = it.next();
            if(temp.getFrequency() > largestFreq.getFrequency()) largestFreq = temp;
        }
        
        if(curr.left != null){
            HeapNode<E> leftFreq = findModeRec(curr.left, largestFreq);
            if(leftFreq.getFrequency() > largestFreq.getFrequency()) largestFreq = leftFreq;
        }

        if(curr.right != null){
            HeapNode<E> rightFreq = findModeRec(curr.right, largestFreq);
            if(rightFreq.getFrequency() > largestFreq.getFrequency()) largestFreq = rightFreq;
        }

        return largestFreq;
    }

    /**
     * Display the tree while traversing preorder.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    
    /** Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
    */
    private <T extends Comparable<T>> void preOrderTraverse(BSTNode<T> node, int depth, StringBuilder sb){
        for (int i = 1; i < depth; i++) sb.append("  ");
        if (node == null) sb.append("null\n");
        else{
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }  
}
