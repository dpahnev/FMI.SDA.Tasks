package Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class AVLTree<T extends Comparable<T>> implements AVLInterface<T>, Iterable<T> {
	private int size;
	private Node<T> root;
	private T toHelpRemove;

	public AVLTree() {
		size = 0;
		root = null;
	}

	public AVLTree(Node<T> root) {
		this();
		this.root = root;
		size = 1;
	}

	@Override
	public void insert(T value) {
		if (isEmpty()) {
			root = new Node<T>(value);
			size++;
		} else {
			root = insert(value, root);
			size++;
		}
	}

	private Node<T> insert(T value, Node<T> root) {
		if (root == null) {
			return new Node<T>(value);
		}
		if (root.getData().compareTo(value) > 0) {
			root.setLeft(insert(value, root.getLeft()));
		} else {
			root.setRight(insert(value, root.getRight()));
		}
		return balance(root);
	}

	@Override
	public void remove(T key) {
		if (isEmpty()) {
			return;
		} else {
			this.root = remove(key, root);
		}
	}

	private Node<T> remove(T value, Node<T> root) {
		if (root == null) {
			return null;
		}
		if (root.getData().compareTo(value) > 0) {
			root.setLeft(remove(value, root.getLeft()));
		} else if (root.getData().compareTo(value) < 0) {
			root.setRight(remove(value, root.getRight()));
		} else {
			size--;
			if (root.getLeft() == null && root.getRight() == null) {
				return null;
			} else if (root.getRight() != null && root.getLeft() == null) {
				return balance(root.getRight());
			} else if (root.getLeft() != null && root.getRight() == null) {
				return balance(root.getLeft());

			} else {
				if (root.getRight().getLeft() == null) {
					root.getRight().setLeft(root.getLeft());
					return balance(root.getRight());
				} else {
					root.setRight(leastLeaf(root.getRight()));
					root.setData(toHelpRemove);
				}
			}
		}
		return balance(root);
	}

	private Node<T> leastLeaf(Node<T> right) {
		if (right.getLeft() == null) {
			this.toHelpRemove = right.getData();
			return right.getRight();
		} else
			right.setLeft(leastLeaf(right.getLeft()));
		return balance(right);
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
		ArrayList<T> items = new ArrayList<>();
		Stack<Node<T>> stack = new Stack<>();
		HashSet<Node<T>> checked = new HashSet<>();
		stack.push(root);
		while (stack.size() > 0) {
			Node<T> current = stack.peek();
			if (current.getLeft() == null || checked.contains(current.getLeft())) {
				items.add(current.getData());
				checked.add(current);
				stack.pop();
				if (current.getRight() != null) {
					stack.push(current.getRight());
				}
			} else {
				stack.push(current.getLeft());
			}
		}
		return items.iterator();
	}

	public boolean isEmpty() {
		if (root != null)
			return false;
		else
			return true;
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
				root.setLeft(leftRotation(root.getLeft()));
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
