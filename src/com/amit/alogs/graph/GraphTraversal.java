package com.amit.alogs.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphTraversal {
	
	public class Vertex {
		public String c;
		public Vertex(String c) {
			this.c = c;
		}
		
		@Override
		public boolean equals(Object o) {
			if(this == o) {
				return true;
			}else if(o instanceof Vertex) {
				Vertex v = (Vertex) o;
				if(this.c.equals(v.c)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		@Override
		public int hashCode() {
			return Objects.hash(this.c);
		}
	}
	
	public class Graph{
		Map<Vertex, List<Vertex>> adjecencyList = new HashMap<>();
		
		public void addVertex(String c) {
			adjecencyList.putIfAbsent(new Vertex(c), new ArrayList<>());
		}
		
		public void removeVertex(String c) {
			Vertex v = new Vertex(c);
			adjecencyList.remove(v);
			for(List<Vertex> vertexList:adjecencyList.values()) {
				vertexList.remove(v);
			}
		}
		
		public void addEdge(String c1, String c2) {
			Vertex v1 = new Vertex(c1);
			Vertex v2 = new Vertex(c2);
			List<Vertex> v1List = adjecencyList.get(v1);
			if(v1List!=null) {
				v1List.add(v2);
			}else {
				v1List = new ArrayList<>();
				v1List.add(v2);
				adjecencyList.put(v1, v1List);
			}
			List<Vertex> v2List = adjecencyList.get(v2);
			if(v2List!=null) {
				v2List.add(v1);
			}else {
				v2List = new ArrayList<>();
				v2List.add(v1);
				adjecencyList.put(v2, v2List);
			}
		}
		
		public void removeEdge(String c1, String c2) {
			Vertex v1 = new Vertex(c1);
			Vertex v2 = new Vertex(c2);
			List<Vertex> v1List = adjecencyList.get(v1);
			if(v1List!=null) {
				v1List.remove(v2);
			}
			List<Vertex> v2List = adjecencyList.get(v2);
			if(v2List!=null) {
				v1List.remove(v1);
			}
		}
		
		public List<Vertex> getEdges(String c){
			return adjecencyList.get(new Vertex(c));
		}
		
		public Vertex getVertex(String c) {
			return new Vertex(c);
		}
	}
	
	
	public Graph buildGraph() {
		Graph graph = new Graph();
	    graph.addVertex("Bob");
	    graph.addVertex("Alice");
	    graph.addVertex("Mark");
	    graph.addVertex("Rob");
	    graph.addVertex("Maria");
	    graph.addEdge("Bob", "Alice");
	    graph.addEdge("Bob", "Rob");
	    graph.addEdge("Alice", "Mark");
	    graph.addEdge("Rob", "Mark");
	    graph.addEdge("Alice", "Maria");
	    graph.addEdge("Rob", "Maria");
	    return graph;		
	}
	
	public Set<Vertex> graphBFS(Graph g, Vertex root) {
		if(root == null)
			return null;
		Set<Vertex> visitedSet = new LinkedHashSet<>();
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Vertex v = queue.remove();
			if(!visitedSet.contains(v)) {
				visitedSet.add(v);
				List<Vertex> vList = g.getEdges(v.c);
				if(vList!=null && vList.size()>0) {
					for(Vertex av : vList) {
						queue.add(av);
					}
				}
			}
		}
		return visitedSet;
	}
	
	public Set<Vertex> graphDFS(Graph g, Vertex root) {
		if(root == null)
			return null;
		Set<Vertex> visitedSet = new LinkedHashSet<>();
		Stack<Vertex> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Vertex v = stack.pop();
			if(!visitedSet.contains(v)) {
				visitedSet.add(v);
				List<Vertex> vList = g.getEdges(v.c);
				if(vList!=null && vList.size()>0) {
					for(Vertex av : vList) {
						stack.push(av);
					}
				}
			}
		}
		return visitedSet;
	}
	
	
	public static void main(String[] args) {
		GraphTraversal gt = new GraphTraversal();
		Graph g = gt.buildGraph();
		Vertex root = g.getVertex("Bob");
		
		Set<Vertex> bfsSet = gt.graphBFS(g, root);
		if(bfsSet!=null) {
			for(Vertex v:bfsSet) {
				System.out.print(v.c + " ");
			}
			System.out.println();
		}
		
		Set<Vertex> dfsSet = gt.graphDFS(g, root);
		if(dfsSet!=null) {
			for(Vertex v:dfsSet) {
				System.out.print(v.c + " ");
			}
			System.out.println();
		}
	}
	
}
