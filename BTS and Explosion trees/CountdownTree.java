package comp2402a4;

import java.util.Random;
import java.util.*;
import java.util.SortedSet;
import java.util.TreeSet;
import java.lang.reflect.Array;

/**
* An unfinished implementation of an Countdown tree (for exercises)
* @author morin
*
* @param <T>
*/
public class CountdownTree<T> extends
BinarySearchTree<CountdownTree.Node<T>, T> implements SSet<T> {

	// countdown delay factor
	double d;

	public static class Node<T> extends BSTNode<Node<T>,T> {
		int timer;  // the height of the node
	}

	public CountdownTree(double d) {
		this.d = d;
		sampleNode = new Node<T>();
		c = new DefaultComparator<T>();
	}

	public boolean add(T x) {
		Node<T> u = new Node<T>();
		u.timer = (int)Math.ceil(d);
		u.x = x;
		if (super.add(u)) {
			while(u.parent != null && u.parent != nil) {
				u = u.parent; 
				u.timer--;
				if (u.timer<=0) {
				explode(u); 
				}
			}
			return true;
		}
		return false;
	}
	public void splice(Node<T> u) {
		Node<T> w = u.parent;
		super.splice(u);
		
			while(u.parent != null && u.parent != nil) {
				u= u.parent; 
				u.timer--;
				if (u.timer<=0) {
				explode(u); 
				}
			}
	}


	protected void explode(Node<T> u) {
		rebuild (u); 
		u.timer = (int)Math.ceil(d);
		int v; 
		v = size (u);
		u.timer = (int)Math.ceil(d*v);
		// Write this code to explode u
		// Make sure to update u.parent and/or r (the tree root) as appropriate
	}

	public static void main(String[] args) {
		Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(1)), 1000);
		Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)), 1000);
		Testum.sortedSetSanityTests(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)), 1000);

		java.util.List<SortedSet<Integer>> ell = new java.util.ArrayList<SortedSet<Integer>>();
		ell.add(new java.util.TreeSet<Integer>());
		ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(1)));
		ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(2.5)));
		ell.add(new SortedSSet<Integer>(new CountdownTree<Integer>(0.5)));
		Testum.sortedSetSpeedTests(ell, 1000000);
	}
	 
	
	
	
	
		@SuppressWarnings("unchecked")
	protected void rebuild(Node<T> u) {
		int ns = size(u);
		Node<T> p = u.parent;
		Node<T>[] a = (Node<T>[]) Array.newInstance(Node.class, ns);
		packIntoArray(u, a, 0);
		if (p == nil) {
			r = buildBalanced(a, 0, ns);
			r.parent = nil;
		} else if (p.right == u) {
			p.right = buildBalanced(a, 0, ns);
			p.right.parent = p;
		} else {
			p.left = buildBalanced(a, 0, ns);
			p.left.parent = p;
		}
	}

	/**
	 * A recursive helper that packs the subtree rooted at u into
	 * a[i],...,a[i+size(u)-1]
	 * 
	 * @param u
	 * @param a
	 * @param i
	 * @return size(u)
	 */
	protected int packIntoArray(Node<T> u, Node<T>[] a, int i) {
		if (u == nil) {
			return i;
		}
		i = packIntoArray(u.left, a, i);
		a[i++] = u;
		return packIntoArray(u.right, a, i);
	}

	/**
	 * A recursive helper that builds a perfectly balanced subtree out of
	 * a[i],...,a[i+ns-1]
	 * 
	 * @param a
	 * @param i
	 * @param ns
	 * @return the rooted of the newly created subtree
	 */
	protected Node<T> buildBalanced(Node<T>[] a, int i, int ns) {
		if (ns == 0)
			return nil;
		int m = ns / 2;
		int v;
		ArrayList<T> temp = new ArrayList();
		a[i + m].left = buildBalanced(a, i, m);
		if (a[i + m].left != nil)
			a[i + m].left.parent = a[i + m];
			v= size(a[i+m]);
			a[i+m].timer =(int)Math.ceil(d*v);
		a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
		if (a[i + m].right != nil)
			a[i + m].right.parent = a[i + m];
			v = size(a[i+m]) ;
			a[i+m].timer =(int)Math.ceil(d*v);
		return a[i + m];
	}
	
/* void StoreNodes (Node<T> root,Vector<Node<T>> nodes) {
	if (root==null){
		return;
	}
	StoreNodes(root.left,nodes);
	nodes.add(root);
	StoreNodes(root.right,nodes);
}

Node<T> BuildHelper (Vector<Node<T>> nodes , int start, int end,Node<T> parent) {
	if (start>end) {return null;}
	int mid = (start + end) / 2; 
    Node<T> node = nodes.get(mid); 
	
	if(parent!=null) {
		node.parent =parent; 
	}
	node.left = BuildHelper(nodes, start, mid - 1,node); 
    node.right = BuildHelper(nodes, mid + 1, end,node); 
	
	return node; 
}

    Node<T> buildTree(Node<T> root){ 
        // Store nodes of given BST in sorted order 
        Vector<Node<T>> nodes = new Vector<Node<T>>(); 
        StoreNodes(root, nodes); 
  
        // Constucts BST from nodes[] 
        int n = nodes.size();
        return BuildHelper(nodes, 0, n - 1,root.parent); 
    }  */


}
