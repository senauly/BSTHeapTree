import java.util.Iterator;

/**
 * HeapIterator interface for the new extended Iterator class with set method.
 */
public interface HeapIterator<E> extends Iterator<E> {
    E set(E value);
}
