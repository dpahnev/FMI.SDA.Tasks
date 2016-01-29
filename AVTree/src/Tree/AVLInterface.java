package Tree;

public interface AVLInterface<T extends Comparable<T>> {

	void insert(T value);

	void remove(T key);

	Node<T> search(T key);

	int size();

}
