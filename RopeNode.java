package rope;

public class RopeNode {
	
	// Operation performed on RopeNodes, includes recursive traversals.
	
	int length; 
			// length of the string in this rope 
	String data; 	
			// string data, NULL for interior nodes 
	RopeNode parent;
			// parent of RopeNode
	RopeNode left; 
			// pointer to left child, NULL for leaves 
	RopeNode right; 
			// pointer to right child, NULL for leaves 
	RopeNode twin;
	static int i_current_pos;
	static int i_last_pos;


	public RopeNode(String string){
		length = string.length();
		data = string;
		parent = null;
		left = null;
		right = null;
	}
	
	public RopeNode(){
		length = 0;
		parent = null;
		data = null;
		left = null;
		right = null;
	}
	
	public void deleteLeftNode(){
		this.left = null;
	}
	
	public void deleteRightNode(){
		this.right = null;
	}
	
	public void splitNodeRight(int index){
		// Splits a RopeNode on an index and returns the right node.	
		RopeNode new_right = new RopeNode(this.data.substring(index));
		this.right = new_right;
	}
	
	public void splitNodeLeft(int index){
		// Splits a RopeNode on an index and returns a Rope and returns the left node.	
		RopeNode new_left  = new RopeNode(this.data.substring(0,index));
		this.left = new_left;
	}
	
	 
	public RopeNode trimLeft(int start, int end, int from){
		int current_pos = from;
		int last_pos = 0;
		if(this.left == null && this.right == null){
			last_pos = current_pos;
			current_pos += this.length;
			System.out.println("lastpost = "+last_pos);
			System.out.println("currentpos = "+current_pos);
			if(start >= last_pos && start <= current_pos && this.data != null) {
				this.splitNodeRight(start-last_pos);
				this.parent.deleteLeftNode();
				return this.root();
			}
			else{
				this.parent.dropNextNode();
				RopeNode next = this.next_traversable_parent();
				return next.trimLeft(start, end, current_pos);			
			}
		} 
		else if(this.left == null && this.right != null){
			 return right.trimLeft(start, end, from);
		} 
		else{
			  return left.trimLeft(start, end, from);
		} 
	}
	
	public RopeNode trimRight(int end, int from){
		int current_pos = from;
		int last_pos = 0;
		if(this.left == null && this.right == null){
			last_pos = current_pos;
			System.out.println("last_pos: "+last_pos);
			current_pos += this.length;
			System.out.println("current_pos: "+current_pos);
			System.out.println("may delete:"+this.data);
			if(end >= last_pos && end <= current_pos && this.data != null) {
				System.out.println("going to return something");
				System.out.println("going to delete:"+this.data.substring(end-last_pos));
				this.splitNodeLeft(end-last_pos);
//				this.parent.deleteLeftNode();
				this.parent.parent.right = null;
//				System.out.println("this.parent.right: "+this.parent.right.data);
//				System.out.println("this.left.data: "+this.left.data);
//				Rope new_rope = new Rope();
//				System.out.println("this.next_traversable_parent(): "+this.next_traversable_parent().left.data);
//				new_rope.root = this.parent.parent;
//				new_rope.root.parent = null;
//				return new_rope.root;
//				this.splitNodeRight(start-last_pos);
//				this.parent.deleteLeftNode();
				return this.root();
			}
			else{
				System.out.println("or else");
				this.dropNextNode();
				RopeNode next = this.next_traversable_parent();
				System.out.println(next.left);
				System.out.println(next.right);
				System.out.println("nxt: ");next.printNode();
				return next.trimRight(end, current_pos);			
			}
		} 
		else if(this.left == null && this.right != null){
			System.out.println("elseif");
			 return right.trimRight(end, from);
		} 
		else{
			System.out.println("else");
			 return left.trimRight(end, from);
		} 
	}
	
	public void insertOnRopeNode(RopeNode text, int index, int from, boolean break_now){
		if(!break_now){
		if(this.left == null && this.right == null){
			i_last_pos = i_current_pos;
			i_current_pos = i_current_pos + this.length;
			if(index >= i_last_pos && index < i_current_pos && this.data != null) {
				RopeNode new_right = new RopeNode(this.data.substring(index-i_last_pos));
				RopeNode new_left_left  = new RopeNode(this.data.substring(0,index-i_last_pos));
				RopeNode new_left = new RopeNode();
				this.right = new_right;
				this.left = new_left;
				new_left.left = new_left_left;
				new_left.right = text;
				break_now = true;
			}
		} else if(this.left == null){
			right.insertOnRopeNode(text, index, i_current_pos, false);
		} else if(this.right == null){
			left.insertOnRopeNode(text, index, i_current_pos, false);
		} else 	{		
			this.left.insertOnRopeNode(text, index, i_current_pos, false);
			this.right.insertOnRopeNode(text, index, i_current_pos, false);
		}
		}
	}
	
	public void subNode(int start, int end, int from){
		if(this.left == null && this.right == null){
			i_last_pos = i_current_pos;
			i_current_pos = i_current_pos + this.length;
			if(start >= i_last_pos && end < i_current_pos && this.data != null) {
				if(start >= i_last_pos && start < i_current_pos && this.data != null){
					
				}
			}
		} else if(this.left == null){
			right.subNode(start, end, i_current_pos);
		} else if(this.right == null){
			left.subNode(start, end, i_current_pos);
		} else 	{		
			this.left.subNode(start, end, i_current_pos);
			this.right.subNode(start, end, i_current_pos);
		}
	}
	
	public RopeNode root(){
		if(this.parent == null){
			return this;
		}
		else{
			return this.parent.root();
		}
	}
	
	public void deleteParentsRighties(){
		this.deleteRightNode();
		if(this != this.root()){
			 this.parent.deleteParentsRighties();
		}
	}
	
	public RopeNode next_traversable_parent(){
		// helps split() operations
		if (this.parent == null){ 		// catches root
			return this.right;
		}
		else if(this.parent != this.root() && this.parent.left == null){
			return this.parent.next_traversable_parent();
		}
		else if (this.parent == this.root() && this.parent.left == null){
			return this.root().right;
		}
		else if (this.parent == this.root() && this.left != null){
			return this.left;
		}
		else{
			return this;
		}
	}
	
	public void dropNextNode(){
		if(this.left == null){
			this.right = null;
		}
		else{
			this.left = null;
		}
	}
	
	public boolean hasParent(){
		if (this.parent != null)	
			return true;
		else						
			return false;
	}
	
	public RopeNode node_append(RopeNode new_node){
		RopeNode new_root = new RopeNode();
		new_root.left = this;
		new_root.right = new_node;
		return new_root;
	}
	
	public RopeNode string_append(String new_string){
		// Creates a new Rope from `new_string' and appends.
		RopeNode new_node = new RopeNode(new_string);
		RopeNode root_node = new RopeNode();
		root_node.left = this;
		root_node.right = new_node;
		this.parent = root_node;
		new_node.parent = root_node;
		return root_node;
	}
	
	public RopeNode string_prepend(String new_string){
		RopeNode new_node = new RopeNode(new_string);
		RopeNode l_node = new RopeNode();
		l_node.left = new_node;
		l_node.right = l_node;
		return l_node;
	}
	
	public RopeNode beginning_of_rope(){
		if (this.left != null){
			return this.left.beginning_of_rope();
		}
		else{
			return this.parent.right;
		}
	}
	
	public String nodeToString(){
		if(this.left == null && this.right == null){
			if(this.data!=null)
				return this.data;
			else
				return "";
		} else if(this.left == null){
			return this.right.nodeToString();
		} else if(this.right == null){
			return left.nodeToString();
		} else 	{		
			return this.left.nodeToString() + this.right.nodeToString();
		}
	}
	
	public void printNode(){
		if(this.left == null && this.right == null){
			if(this.data != null)
			System.out.print(this.data);
		} else if(this.left == null){
			right.printNode();
		} else if(this.right == null){
			left.printNode();
		} else 	{		
			this.left.printNode();
			this.right.printNode();
		}
	}
	
	public void explainNode(){
		if(this.left == null & this.right == null){
			System.out.print("\nNode:"+
							"\n\tData: "+this.data+
							"\n\tData Length: "+this.length+
							"\n\tHash\t:    "+this+
							"\n\tParent Hash: "+this.parent
							);
		} else if(this.left == null){
			right.explainNode();
		} else 	{		
			this.left.explainNode();
			this.right.explainNode();
		}
	}
	
	public void resetIterators(){
		i_current_pos = 0;
		i_last_pos = 0;
	}
	
	
} // end of class
