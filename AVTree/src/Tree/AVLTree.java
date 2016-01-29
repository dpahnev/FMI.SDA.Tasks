package Tree;

import java.util.Iterator;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>> implements AVLInterface<T>, Iterable<T> {
	private int size;
	private Node<T> root;

	public AVLTree() {
		size = 0;
		root = null;
	}

	public AVLTree(Node<T> root) {
		this();
		this.root = root;
		size = 1;
	}

	@Override // ready
	public void insert(T value) {
		Node<T> current = root;
		while (current != null) {
			int compare = (value.compareTo(current.getData()));
			if (compare > 0) {
				current = current.getRight();
			} else if (compare < 0) {
				current = current.getLeft();
			} else {
				return;// exeption
			}
		}
		current = new Node<T>(value);
		size++;
		balance();
	}

	@Override // ready
	public void remove(T key) {
		if (isEmpty()) {
			return;
		}
		Node<T> current = root;
		Node<T> forRemove = search(key);
		if (forRemove != null) {
			if (forRemove.getLeft() == null && forRemove.getRight() == null) {
				T value = forRemove.getData();
				while (current.getLeft() == forRemove || current.getRight() == forRemove) {
					int compare = (value.compareTo(current.getData()));
					if (compare > 0) {
						current = current.getRight();
					} else if (compare < 0) {
						current = current.getLeft();
					}
				}
				if (current.getLeft() == forRemove) {
					current.setLeft(null);
				} else {
					current.setRight(null);
				}
			} else if (forRemove.getLeft() != null && forRemove.getRight() != null) {
				T temp = findLeastOfBrach(forRemove);
				forRemove.setData(temp);
			} else {
				if (forRemove.getLeft() != null) {
					forRemove.setData(forRemove.getData());
				}
				if (forRemove.getRight() != null) {
					forRemove.setData(forRemove.getData());
				}
			}
		} else {
			return;
		}
		size--;
		balance();
	}

	private T findLeastOfBrach(Node<T> root) {// ready
		// if (root != null ){
		Node<T> parent = root;
		// if(root.getLeft() != null) {
		Node<T> current = root.getLeft();
		// }
		// else {
		T value;
		// }
		// }

		while (current.getLeft() != null) {
			parent = current;
			current = current.getLeft();
		}
		if (current.getRight() != null) {
			value = current.getRight().getData();
			current.setRight(null);
		} else {
			value = current.getData();
			parent.setLeft(null);
		}
		return value;
	}

	@Override // ready
	public Node<T> search(T key) {
		if (isEmpty()) {
			return null;
		}
		Node<T> current = this.root;
		while (current != null) {
			int compare = current.getData().compareTo(key);
			if (compare == 0) {
				return current;
			}
			if (compare > 0) {
				current = current.getLeft();
			}
			if (compare < 0) {
				current = current.getRight();
			}
		}
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		if (root != null)
			return false;
		else
			return true;
	}

	private void iterateParents(Stack<Node<T>> parents) {
		Stack<Node<T>> children = new Stack<>();
		while (!parents.isEmpty()) {
			Node<T> temp = parents.pop();
			children.push(balance(temp));
		}
	}

	private void correctHeight(Node<T> current) {// ready
		int left = getHeight(current.getLeft());
		int right = getHeight(current.getRight());
		current.setHeight(left > right ? ++left : ++right);
	}

	private Node<T> balance(Node<T> root) {// ready
		correctHeight(root);
		int balance = balanceFactor(root);
		if (balance == -2) {// leftRotation
			if (balanceFactor(root.getRight()) > 0) {
				root.setRight(rightRotation(root.getRight()));
			}
			return leftRotation(root);

		} else if (balance == 2) {// rightRotation
			if (balanceFactor(root.getLeft()) < 0) {
				root.setLeft(rightRotation(root.getLeft()));
			}
			return rightRotation(root);
		}
		return root;
	}

	private Node<T> leftRotation(Node<T> root) {// ready
		Node<T> temp = root.getRight();
		root.setRight(temp.getLeft());
		temp.setLeft(root);
		correctHeight(root);
		correctHeight(temp);
		return temp;
	}

	private Node<T> rightRotation(Node<T> root) {// ready
		Node<T> temp = root.getLeft();
		root.setLeft(temp.getRight());
		temp.setRight(root);
		correctHeight(root);
		correctHeight(temp);
		return temp;
	}

	private int balanceFactor(Node<T> current) {// ready
		int left = getHeight(current.getLeft());
		int right = getHeight(current.getRight());
		return left - right;
	}

	private int getHeight(Node<T> node) {
		if (node == null)
			return 0;
		else
			return node.getHeight();
	}

}
