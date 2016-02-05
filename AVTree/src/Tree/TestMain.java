package Tree;

import java.util.Iterator;

public class TestMain {

	// public static void main(String[] args) {
	// AVLTree<Integer> tree = new AVLTree<>();
	// for (int i = -5; i < 10; i++) {
	// try {
	// tree.insert(i);
	// } catch (ElementException e) {
	// e.printStackTrace();
	// }
	// }
	// try {
	// tree.remove(-14);
	// } catch (ElementException e2) {
	// // TODO Auto-generated catch block
	// e2.getMessage();
	// }
	//
	// try {
	// System.out.println(tree.size());
	// tree.remove(-5);
	// tree.insert(-3);
	//
	// } catch (Exception e1) {
	// System.out.println(e1.getMessage());
	// }
	// System.out.println(tree.size());
	// for (Integer item : tree) {
	// System.out.print(item + ",");
	// }
	// System.out.println();
	// Node<Integer> nqmago;
	// try {
	// System.out.println(tree.search(-5));
	// } catch (Exception e) {
	// System.out.println("Elementut koito tursite go nqma");
	// }
	//
	// // for (int i = -3; i < 5; i++) {
	// // tree.remove(i);
	// // if (i == 3 || i == 4) {
	// // for (Integer item : tree) {
	// // System.out.print(item + ",");
	// // }
	// // System.out.println();
	// // if (tree.search(7) == null) {
	// //
	// // // System.out.println("zagibi se pri:" + i);
	// // }
	// // // System.out.print(tree.search(7).getData() + "----");
	// // }
	// // }
	// for (int i = -3; i < 5; i++) {
	// for (Integer item : tree) {
	// System.out.print(item + ",");
	// }
	// try {
	// tree.remove(i);
	// } catch (ElementException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println();
	// }
	// // for (Integer item : tree) {
	// // System.out.print(item + ",");
	// // }
	// // System.out.println();
	// // }
	//
	// for (int i = 100; i < 110; i++) {
	// try {
	// tree.insert(i);
	// } catch (ElementException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // System.out.println("i=" + i);
	// for (int j = i * 10; j < i * 10 + 10; j++) {
	// // System.out.print("-" + j);
	// try {
	// tree.insert(j);
	// } catch (ElementException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// for (int i = 100; i < 110; i++) {
	// try {
	// tree.remove(i);
	// } catch (ElementException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// // System.out.println("i=" + i);
	// for (int j = i * 10; j < i * 10 + 10; j++) {
	// // System.out.print("-" + j);
	// try {
	// tree.remove(j);
	// } catch (ElementException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// for (Integer item : tree) {
	// System.out.print(item + ",");
	// }
	// IAVLTree<Integer> godTree = new AVLTree<Integer>();
	// for (int i = 0; i < 100; i++) {
	// int temp = ((int) (Math.random() * 10000000));
	// // System.out.println(temp);
	// try {
	// godTree.insert(temp);
	// } catch (ElementException e) {
	// // TODO Auto-generated catch block
	// System.out.println(e.getMessage() + " " + temp);
	// }
	// try {
	// godTree.insert(temp);
	// } catch (ElementException e) {
	// // TODO Auto-generated catch block
	// System.out.println(e.getMessage() + " " + temp);
	// }
	//
	// }
	// for (Integer item : godTree) {
	// System.out.println("item:" + item);
	// }
	//
	// }

	public static void main(String[] args) {

		IAVLTree<Integer> tree = new AVLTree<Integer>();
		IAVLTree<Integer> godTree = new AVLTree<Integer>();
		for (int i = 0; i < 10; i++) {
			try {
				tree.insert(i);
				godTree.insert(i);
			} catch (ElementException e) {
				e.printStackTrace();
			}
		}
		int arr[] = new int[10];
		int i = 0;
		for (Integer integer : godTree) {
			arr[i] = integer;
			i++;
			System.out.print(integer + " , ");

		}
		i = 0;
		System.out.println();
		for (Iterator<Integer> it = tree.iterator(); it.hasNext();) {
			// if (arr[i] != it.next()) {
			// flag = false;
			// break;
			// }
			System.out.print(it.next() + " , ");
		}

	}
}
