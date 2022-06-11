package utility;

/**
 * A vertex in the graph that can store generic data.
 */
public class Vertex< T
        extends Comparable < T >> {
    private T data;

    public Vertex(T data) {
        this.data = data;
    }

    /**
     * Gets the data contained in this vertex.
     * @return data
     */
    public T getData() {
        return this.data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || !(obj instanceof Vertex))
            return false;

        return this.data.equals(((Vertex<?>) obj).getData());
    }

    public int compareTo(Vertex ver)
    {
        return data.compareTo((T) ver.data);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}