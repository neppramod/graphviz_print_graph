import java.util.*;



public class Solution {
    public static void main(String[] args) {
	    Solution sol = new Solution();

		// 1. Simple Example
        GraphPrinter gp = new GraphPrinter("simple");
        gp.addln("1 -> 2");
        gp.addln("1 -> 3");
        gp.print();

		// 2. ListNode
		ListNode l = cl(1);
        l.next = cl(2);
        l.next.next = cl(3);
        l.next.next.next = cl(4);

		sol.printList(l);

		// 3. TreeNode
		TreeNode tree = ct(1);
		tree.left = ct(2);
		tree.left.right = ct(5);
		tree.right = ct(3);
		tree.right.right = ct(4);

		sol.printTree(tree);
		
		
        // 4. Graph
        List<Integer>[] adj = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        adj[1] = new ArrayList<>(); adj[1].addAll(Arrays.asList(2, 4));
        adj[2] =  new ArrayList<>(); adj[2].addAll(Arrays.asList(1, 3, 5));
        adj[3] =  new ArrayList<>(); adj[3].addAll(Arrays.asList(2, 5));
        adj[4] =  new ArrayList<>(); adj[4].addAll(Arrays.asList(1));
        adj[5] =  new ArrayList<>(); adj[5].addAll(Arrays.asList(2, 3));


        sol.printGraphAdj(adj, 6);
	}


	// Code for ListNode
	static class ListNode {
        ListNode next;
        int val;

        public ListNode(int v) {
            val = v;
        }
    }

	static ListNode cl(int v) {
        return new ListNode(v);
	}


	void printList(ListNode l) {
		GraphPrinter gp = new GraphPrinter("list");
		
        ListNode cur = l;

        gp.addln("graph [rankdir=\"LR\"]");

        while (cur != null && cur.next != null) {
            gp.addln(String.valueOf(cur.val) + " -> " + String.valueOf(cur.next.val));
            cur = cur.next;
        }

		gp.print();
    }


	// Code for TreeNode
	static class TreeNode
    {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    static TreeNode ct(int v) {
        return new TreeNode(v);
    }

	// use this variable to handle null 
	static long nullCounter = 0;
	
	void printTree(TreeNode root) {
		GraphPrinter gp = new GraphPrinter("tree");
		nullCounter = 0;

		// Let's use DFS to go through all nodes of the tree and add it to gp GraphPrinter object
        addNodeForPrinting(root, gp);

		gp.print();
    }

	// DFS for traversing TreeNode and adding it to GraphPrinter object
	// For null nodes we will add a small double circle
	void addNodeForPrinting(TreeNode root, GraphPrinter gp) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
            gp.addln(String.valueOf(root.val) + " -> " + String.valueOf(root.left.val));
        } else {
            String nullStr = "n" + (nullCounter++);
            gp.addln(nullStr + " [shape=doublecircle, fixedsize=true, width=.3, height=.3]");
            gp.addln(String.valueOf(root.val) + " -> " + nullStr);
        }

        addNodeForPrinting(root.left, gp);

        if (root.right != null) {
            gp.addln(String.valueOf(root.val) + " -> " + String.valueOf(root.right.val));
        } else {
            String nullStr = "n" + (nullCounter++);
            gp.addln(nullStr + " [shape=doublecircle, fixedsize=true, width=.3, height=.3]");
            gp.addln(String.valueOf(root.val) + " -> " + nullStr);
        }

        addNodeForPrinting(root.right, gp);
	}


	// Print Graph
	void printGraphAdj(List[] adj, int n) {
        GraphPrinter gp = new GraphPrinter("AdjGraph");


        gp.addln("concentrate=true");  // only 1 line to represent bidirectional graphs
        gp.addln("edge[arrowhead=none]");  // remove the arrowhead for graph, we don't need for bidirectional graph

		gp.addln("node [margin=0 fontcolor=blue fontsize=32 width=0.5 shape=circle style=filled]");
		gp.addln("rankdir=LR");

        // code goes here
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsprintGraphNode(adj, i, visited, gp);
            }
        }

        gp.print();
    }

    void dfsprintGraphNode(List[] adj, int s, boolean[] visited, GraphPrinter gp) {

        visited[s] = true;

        for (Object nei : adj[s]) {
            int neighbor = (int) nei;
            gp.addln(String.valueOf(s) + " -> " + String.valueOf(neighbor));

            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfsprintGraphNode(adj, neighbor, visited, gp);
            }
        }
    }
    
}
