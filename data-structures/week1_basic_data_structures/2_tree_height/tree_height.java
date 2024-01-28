import java.util.*;
import java.io.*;

public class tree_height {
	static class Node{
		int val;
		ArrayList<Node> children = new ArrayList<>();
	}
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
			Node[] Nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				Nodes[i] = new Node();
			}
			int root = -1;
			for (int i = 0; i < parent.length; i++) {
				int parent_index = parent[i];
				if(parent_index==-1)
					root = i;
				else {
					Nodes[parent_index].children.add(Nodes[i]);
					Nodes[i].val = i;
				}
			}

			Queue<Node> queue= new ArrayDeque<Node>();
			queue.add(Nodes[root]);
			int height = 0;
			while(!queue.isEmpty()){
				ArrayList<Node> arr = new ArrayList<>();

				while (!queue.isEmpty())
					arr.add(queue.poll());

				for(Node i: arr)
					queue.addAll(i.children);

					height++;
			}

			return height;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
