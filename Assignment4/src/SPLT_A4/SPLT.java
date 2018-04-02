package SPLT_A4;

// edge cases: contains method (return false) and insert method (insert into tree) can happen on an empty tree. 
// Every other method cannot happen on an empty tree.

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
	}

	public BST_Node getRoot() {
		return root;
	}

	@Override
	public void insert(String s) {
		// if string is already in the tree, then there is no change to
		// the tree state (save for splaying), and return
		// edge case: duplicates
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
			return;
		}

		if (contains(s)) {
			return;
		} else {
			BST_Node temp = root.insertNode(s);
			root = temp;
			if (root.justMade) {
				size += 1;
				return; // just splayed to root
			}
			return;
		}
	}

	@Override
	public void remove(String s) {
		// edge cases: tree is empty, cannot remove node not in tree
		if (root == null) { // a.k.a. "if tree is empty"
			return;
		} else if (size == 1 && root.data == s) {
			// edge case: if removing the only node in the tree (root), then
			// then tree is empty (root is set to null)
			root = null;
			return;
		} else if (contains(s)) {
			BST_Node rightTemp = root.right;
			BST_Node leftTemp = root.left;

			if (leftTemp == null) {
				root = null;
				root = rightTemp;
				if (rightTemp != null) {
					rightTemp.parent = null;
				}
				size--;
				return;
			}

			leftTemp.parent = null; // unhook from parent
			root = leftTemp.findMax();

			if (rightTemp != null) {
				rightTemp.parent = root;
			}
			root.right = rightTemp; // hook the other way
			root.parent = null;
		}
		return;
	}

	@Override
	public String findMin() {
		// if "nothing in the tree/no tree", can't return a min value
		if (size == 0)
			return null;
		root = root.findMin();
		return root.data;
	}

	@Override
	public String findMax() {
		// if "nothing in the tree/no tree", can't return a max value
		if (size == 0)
			return null;
		root = root.findMax();
		return root.data;
	}

	@Override
	public boolean empty() {
		if (root == null) {
			return true;
		}
		// same as above if-statement, assuming insert and remove methods
		// are correct
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) { // delegating here
		// reassign root
		if (size == 0)
			return false;
		// call contains regardless, will splay to root
		root = root.containsNode(s);
		if (root.data == s) {
			return true;
		} else {
			return false; // return if s is in the tree or not
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

}