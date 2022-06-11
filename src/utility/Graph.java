package utility;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A generic implementation of an undirected graph.
 * @invariant forall u, adjLists.get(u).contains(v) <=> adjLists.get(v).contains(u)
 */
public class Graph< T
        extends Comparable < T >>{
    private Map<Vertex<T>, ArrayList<Vertex<T>>> adjLists;
    private ArrayList<Vertex<T>> verList;

    public Graph() {
        this.adjLists = new HashMap<Vertex<T>, ArrayList<Vertex<T>>>();
    }

    /**
     * Adds a vertex to the graph, if it doesn't already exist.
     * @param u the vertex to add
     */
    public void addVertex(Vertex<T> u) {
        if (!this.hasVertex(u)) {
            this.adjLists.put(u, new ArrayList<Vertex<T>>());
        }
        verList.add(u);

    }

    /**
     * Removes a vertex from the graph, if it exists.
     * @param u the vertex to remove
     */
    public void removeVertex(Vertex<T> u) {
        if (this.hasVertex(u)) {
            this.adjLists.remove(u);
            for (Vertex<T> v : this.getVertices()) {
                ArrayList<Vertex<T>> vAdjList = this.getAdjListOf(v);
                vAdjList.remove(u);
            }
        }
        verList.remove(u);

        for(int i=0;i<verList.size();++i)
        {
            if(verList.get(i).equals(u));
            else
            {
                addEdge(u, verList.get(i));
            }
        }
    }

    public ArrayList<Vertex<T>> getVertexList(){return verList;}

    /**
     * Determines if a vertex (of any type) is in this graph.
     * @param u the vertex to find
     * @return true if u is in the graph, or false otherwise
     */
    public boolean hasVertex(Vertex<?> u) {
        for (Vertex<T> v : this.adjLists.keySet())
            if (u.compareTo(v)==0)
                return true;
        return false;
    }

    /**
     * Adds an edge bidirectionally connecting two vertices, if one doesn't already exist.
     * @param u the first vertex
     * @param v the second vertex
     */
    public void addEdge(Vertex<T> u, Vertex<T> v) {
        if (!this.hasVertex(u) || !this.hasVertex(v))
            return;

        if (!this.hasEdge(u, v)) {
            ArrayList<Vertex<T>> uAdjList = this.getAdjListOf(u);
            uAdjList.add(v);
            if (!u.equals(v)) {
                ArrayList<Vertex<T>> vAdjList = this.getAdjListOf(v);
                vAdjList.add(u);
            }
            System.out.printf("Added edge between vertices %s and %s\n", u, v);
        }
    }

    /**
     * Removes an edge bidirectionally connecting two vertices, if one exists.
     * @param u the first vertex
     * @param v the second vertex
     */
    public void removeEdge(Vertex<T> u, Vertex<T> v) {
        if (this.hasVertex(u) && this.hasVertex(v)) {
            ArrayList<Vertex<T>> uAdjList = this.getAdjListOf(u);
            ArrayList<Vertex<T>> vAdjList = this.getAdjListOf(v);
            uAdjList.remove(v);
            vAdjList.remove(u);
            System.out.printf("Removed edge between vertices %s and %s\n", u, v);
        }
    }

    /**
     * Determines if there is an edge between two vertices of any type.
     * @param u the first vertex
     * @param v the second vertex
     * @return true if there is an edge from u to v, or false otherwise
     */
    public boolean hasEdge(Vertex<?> u, Vertex<?> v) {
        return (this.hasVertex(u) && this.hasVertex(v))
                ? this.getAdjListOf(u).contains(v)
                : false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || !(obj instanceof Graph))
            return false;

        Graph<?> graph = (Graph<?>) obj;
        return this.isSubgraph(graph) && graph.isSubgraph(this);
    }

    /**
     * Returns a set containing all of the vertices in this graph.
     * @return the vertex set of this graph
     */
    private Set<Vertex<T>> getVertices() {
        return this.adjLists.keySet();
    }

    /**
     * Returns the adjacency list for a vertex of any type, if it exists.
     * @param u the vertex to get the adjacency list of
     * @return the adjacency list of u, or null if u is not in the graph
     */
    private ArrayList<Vertex<T>> getAdjListOf(Vertex<?> u) {
        for (Vertex<T> v : this.getVertices())
            if (u.equals(v))
                return this.adjLists.get(v);
        return null;
    }

    /**
     * Determines if this graph is a subgraph of another graph.
     * @param graph the other graph
     * @return true if it is a subgraph, or false otherwise
     */
    private boolean isSubgraph(Graph<?> graph) {
        for (Vertex<T> u : this.getVertices()) {
            if (!graph.hasVertex(u))
                return false;
            for (Vertex<T> v : this.getVertices())
                if (this.hasEdge(u, v) != graph.hasEdge(u, v))
                    return false;
        }
        return true;
    }
}