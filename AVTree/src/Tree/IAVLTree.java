package Tree;

public interface IAVLTree<T extends Comparable<T>> extends Iterable<T> {

	void insert(T value) throws ElementException;

	void remove(T key) throws ElementException;

	boolean search(T key);

	int size();

}
