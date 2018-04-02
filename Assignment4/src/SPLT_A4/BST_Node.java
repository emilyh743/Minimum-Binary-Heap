package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node parent;
	boolean justMade = true;

	BST_Node(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	public BST_Node containsNode(String s) {
		// checks for duplicates
		if (data.equals(s)) {
			splay(this);
			return this;
		}
		if (data.compareTo(s) > 0) {
			if (left == null) {
				splay(this);
				return this;
			}
			return left.containsNode(s);
		}
		if (data.compareTo(s) < 0) {
			if (right == null) {
				splay(this);
				return this;
			}
			return right.containsNode(s);
		}
		return null; // shouldn't hit
	}

	public BST_Node insertNode(String s) {
		// 1st case: no left
		if (data.compareTo(s) > 0) {
			if (left == null) {
				BST_Node temp = new BST_Node(s);
				left = temp;
				temp.parent = this;
				splay(temp);
				return temp;
			}
			return left.insertNode(s);

		}
		if (data.compareTo(s) < 0) {
			// 2nd case: no right
			if (right == null) {
				BST_Node temp = new BST_Node(s);
				right = temp;
				temp.parent = this;
				splay(temp);
				return temp;
			}
			return right.insertNode(s);
		} else {
			// 3rd case: duplicate
			this.justMade = false;
			return this;
		}
	}

	public boolean removeNode(String s) {
		return false;
	}

	public BST_Node findMin() {
		if (left != null)
			return left.findMin();
		splay(this); // added in to splay the min to the root
		return this;
	}

	public BST_Node findMax() {
		if (right != null)
			return right.findMax();
		splay(this); // splay max to the root
		return this;
	}

	public int getHeight() {
		int l = 0;
		int r = 0;
		if (left != null)
			l += left.getHeight() + 1;
		if (right != null)
			r += right.getHeight() + 1;
		return Integer.max(l, r);
	}

	public void splay(BST_Node x) {
		// Contains and insert methods take care of the root.
		// Don't need root in this method.
		// CONSIDER 3 CASES (6 mini cases) HERE:
		while (x.parent != null) {
			BST_Node p = x.parent;
			BST_Node Grandparent = x.parent.parent;
			// 1) ZIG - left (rotate left), right (rotate right)
			if (Grandparent == null) {
				if (x == p.left) {
					// left ZIG
					rotateLeft(x);
				} else {
					// right ZIG
					rotateRight(x);
				}
			} else {
				// 2) ZIG-ZIG - left-left, right-right
				if (x == p.left) {
					if (p == Grandparent.left) {
						// Left-left ZIG-ZIG
						rotateLeft(x.parent);
						rotateLeft(x);
					} else {
						// Right-right ZIG-ZAG
						rotateLeft(x); // zig zag
						rotateRight(x);
					}
				} else {
					// 3) ZIG-ZAG - right-left, left-right
					if (p == Grandparent.left) {
						// Right-left ZIG-ZAG
						rotateRight(x);
						rotateLeft(x);
					} else {
						// Left-right ZAG-ZIG
						rotateRight(x.parent);
						rotateRight(x);
					}
				}
			}
		}
		return;
	}

	public void rotateRight(BST_Node x) {
		BST_Node Grandparent = x.parent.parent;

		if (x == null || x.parent == null) {
			return;
		}
		BST_Node p = x.parent;
		BST_Node temp = x.left;

		x.left = p; // assignments work from right to left
		p.parent = x;
		p.right = temp; // B in this case

		if (temp != null) {
			temp.parent = p;
		}
		x.parent = Grandparent;

		if (Grandparent != null) {
			BST_Node grandpar = Grandparent;
			if (p == Grandparent.left) {
				// sets x's field to point to grandparent
				grandpar.left = x;
			} else {
				grandpar.right = x;
			}
		}
	}

	public void rotateLeft(BST_Node x) {
		BST_Node Grandparent = x.parent.parent;

		if (x == null || x.parent == null) {
			return;
		}
		BST_Node p = x.parent;
		BST_Node temp = x.right;

		x.right = p;
		p.parent = x;
		p.left = temp; // B in this case

		if (temp != null) {
			temp.parent = p;
		}
		x.parent = Grandparent;

		if (Grandparent != null) {
			BST_Node grandpar = Grandparent;
			if (p == Grandparent.left) {
				grandpar.left = x;
			} else {
				grandpar.right = x;
			}
		}
	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

}
