import java.io.*;
import java.util.*;

//Alexandria BenDebba
// Given a preorder traversal

public class btree {

	Node root = null;
	static int index = 0;
	static long sum = 0;

	public class Node {

		Node left;
		Node right;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	public btree(int[] arr, int n) {
		// all values will be below 500,000,000
		root = construct(arr, n, 0, 500000000);
	}

	public Node construct(int[] arr, int length, int min, int max) {
		if (index >= length) return null;
		Node root = null;
		int curr = arr[index];
		if (curr > min && curr < max) {
			root = new Node(curr);
			index++;
			if (index < length) {
				root.left = construct(arr, length, min, curr);
			}
			// check twice because index can change
			if (index < length) {
				root.right = construct(arr, length, curr, max);
			}
		}
		return root;
	}
	
	/* Testing purposes only */
	public void preOrder(Node root) {
		if (root == null) return;
		
		System.out.println(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public void compute_sums(Node root, int current_level) {
		if (root == null) return;
		
		if (current_level % 2 == 0)
			sum += root.data;
		
		compute_sums(root.left, current_level + 1);
		compute_sums(root.right, current_level + 1);
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		//System.out.println(size);
		int[] nodes = new int[size];
		for (int i = 0; i < size; i++) {
			nodes[i] = sc.nextInt();
		}
		btree tree = new btree(nodes, size);
		tree.compute_sums(tree.root, 0);
		//tree.preOrder(tree.root);
		System.out.println(sum);
	}
}
