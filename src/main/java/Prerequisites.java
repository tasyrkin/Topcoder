

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prerequisites {
	
	public static Graph mainGraph = new Graph();
	
	public static class Node{
		String id;
		ArrayList<Node> children;		
		
		public Node(String id, ArrayList<Node> children){
			this.id = id;
			this.children = children;
		}
		
		public void addChildren(ArrayList<Node> childrenToAdd){
			ArrayList<Node> commonChildren = null;
			if(this.children != null){
				commonChildren = this.children; 
			}
			else{
				commonChildren = new ArrayList<Node>();				
			}
			for(int cnt = 0; cnt < childrenToAdd.size(); cnt++){				
				commonChildren.add(childrenToAdd.get(cnt));
			}			
		}
		
		public boolean equals(Object o){
			if(o != null && o instanceof Node){
				Node node = (Node)o;
				return this.id.equals(node.id);
			}
			return false;
		}
		
		public String getId(){
			return id;
		}
	}
	
	public static class Graph{
		List<Node> graph = null;
		
		public Graph(){
			graph = new ArrayList<Node>();
		}
		
		public void add(Node node){
			graph.add(node);
		}
		
		public Node find(Node node){
			return find(node, graph);
		}
		
		private Node find(Node node, List<Node> graph){
			for(int cnt = 0; graph != null && cnt < graph.size(); cnt++){
				Node currNode = graph.get(cnt);
				if(currNode.equals(node)){
					return graph.get(cnt);
				}
			}
			Node foundNode = null;
			for(int cnt = 0; graph != null && cnt < graph.size(); cnt++){
				foundNode = find(node, graph.get(cnt).children);
				if(foundNode != null){
					return foundNode;
				}
			}			
			return foundNode;
		}
	
		public void print(){
			print(graph);
		}

		private void print(List<Node> graph) {
			if(graph==null){
				System.out.print("|null|");
				return;
			}
			System.out.print("|");
			for(int cnt = 0; cnt < graph.size(); cnt++){
				System.out.print(graph.get(cnt).getId() + " ");
			}
			System.out.print("|");
			for(int cnt = 0; cnt < graph.size(); cnt++){
				print(graph.get(cnt).children);
			}
		}
	}
	
	public String[] orderClasses(String[] classes){
		
		
		for(int cnt = 0; cnt < classes.length; cnt++){
			String [] parsedClasses = classes[cnt].split("\\s+");
			ArrayList<Node> children = null; 
			for(int currCnt = 1; currCnt < parsedClasses.length; currCnt++){
				if(currCnt == 1){
					children = new ArrayList<Node>();
				}
				Node node = new Node(parsedClasses[cnt], null);
				Node similarNode = mainGraph.find(node);
				if(similarNode!=null){
					children.add(similarNode);					
				}
				else{
					children.add(node);
				}
			}
			String mainId = parsedClasses[0].substring(0,parsedClasses[0].indexOf(":"));
			Node mainNode = new Node(mainId, children);
			Node similarMainNode = mainGraph.find(mainNode);
			if(similarMainNode != null){
				similarMainNode.addChildren(children);
			}
			else{
				mainGraph.add(mainNode);
			}
		}
		
		return null;
	}
	
	public static void main(String[] args){
		
	}
}
