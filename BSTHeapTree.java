import java.util.Collection;
import java.util.Collections;

public class BSTHeapTree<E extends Comparable<E>> {
    private BSTNode<Heap<HeapNode<E>>> root;

    private class BSTNode<T>{
        private T data = null;
        private BSTNode<T> right, left;
    
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

    /*
    public BSTHeapTree(E data,BSTHeapTree<E> leftTree, BSTHeapTree<E> rightTree) {
        
        if (leftTree != null) root.left = leftTree.root;
        else root.left = null;
        
        if (rightTree != null) root.right = rightTree.root;
        else root.right = null;
    }*/

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

    public void addNode(Heap<HeapNode<E>> data){
        root = addNode(root, data);
    }

    public void removeNode(Heap<HeapNode<E>> data){
        root = removeNode(root,data);
    }

    //T = Heap<HeapNode<E>>
    private <T extends Comparable<T>> BSTNode<T> addNode(BSTNode<T> curr, T data){
        if (curr == null) {
            return new BSTNode<>(data);
        }
    
        if (data.compareTo(curr.data) < 0) curr.left = addNode(curr.left, data);
        else if (data.compareTo(curr.data) > 0) curr.right = addNode(curr.right, data);
        return curr;
    }

    private <T extends Comparable<T>> BSTNode<T> removeNode(BSTNode<T> curr, T data){
        if(curr == null) return null;
        
        if (data.compareTo(curr.data) < 0) curr.left = removeNode(curr.left, data);
        else if (data.compareTo(curr.data) > 0) curr.right = removeNode(curr.right, data);
        
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

    private <T extends Comparable<T>> T getPeekNode(BSTNode<T> parent){
        if (parent.right.right == null) {
            T peek = parent.right.data;
            parent.right = parent.right.left;
            return peek;
        }
        
        else return getPeekNode(parent.right);
    }









    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    
      /** Perform a preorder traversal.
          @param node The local root
          @param depth The depth
          @param sb The string buffer to save the output
       */
      private <T extends Comparable<T>> void preOrderTraverse(BSTNode<T> node, int depth,
                                    StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
          sb.append("  ");
        }
        if (node == null) {
          sb.append("null\n");
        }
        else {
          sb.append(node.toString());
          sb.append("\n");
          preOrderTraverse(node.left, depth + 1, sb);
          preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    public static void main(String[] args) {
        BSTHeapTree<Integer> a = new BSTHeapTree<>();
    }
       
}
