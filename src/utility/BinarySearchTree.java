package utility;
import java.util.Arrays;

/**
 * Binary Search class with array implementation
 * @param <E> Generic Type
 */
public class BinarySearchTree<E extends Comparable<E>> implements SearchTree<E> {

    /**
     * Array of data
     */
    protected E [] arr;

    /**
     * Depth of tree
     */
    private int depth;

    /**
     * Capacity of array
     */
    private int capacity;

    /**
     * Default Constructor
     */
    public BinarySearchTree(){
        depth = 1;
        capacity = 1 + (int) Math.pow (2,depth) ;
        arr = (E[]) new Comparable[capacity];
    }

    /** Reallocate the array */
    private void reallocate(){
        capacity += (int) Math.pow(2, ++depth);;
        arr = Arrays.copyOf(arr, capacity);
    }


    /**
     * Inserts item where it belongs in the tree.
     * @param item Data to add
     * @return Returns true if item is inserted; false if it isn’t (already in tree)
     */
    @Override
    public boolean add(E item) {
        try {
            if (item == null)
                throw new NullPointerException();
            return add(0, item);
        } catch (NullPointerException e){
            System.out.println("Target is a null");
        }
        return false;
    }


    /**
     * Inserts item where it belongs in the tree.
     * @param index of array
     * @param item Data to add
     * @return Returns true if item is inserted; false if it isn’t (already in tree)
     */
    private boolean add(int index, E item){
        if(index < 0)
            return false;

        if(index * 2 + 2 >= capacity){
            reallocate();
        }


        if(arr[index] == null){
            arr[index] = item;
            return true;
        }

        else if(item.compareTo(arr[index]) == 0){
            return false;
        }

        else if(item.compareTo(arr[index]) < 0) {
            return add(2 * index + 1, item);
        }

        else{
            return add(2 * index + 2, item);
        }
    }

    /**
     * Checks if item is in tree
     * @param target Item to search
     * @return Returns true if target is found in the tree
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /**
     * Finds the item to search for.
     * @param target Item to search
     * @return Returns a reference to the data in the node that is equal to target . If no such node is found, returns null
     */
    @Override
    public E find(E target) {
        try {
            if (target == null)
                throw new NullPointerException();
            return find(0, target);
        } catch (NullPointerException e){
            System.out.println("Target is a null");
        }
        return null;
    }

    /**
     * Finds the item to search for.
     * @param index index of array
     * @param item Item to search
     * @return Returns a reference to the data in the node that is equal to target . If no such node is found, returns null
     */
    private E find(int index, E item){
        if(index < 0 || index >= capacity || arr[index] == null)
            return null;


        if(item.compareTo(arr[index]) == 0){
            return arr[index];
        }

        else if(item.compareTo(arr[index]) < 0) {
            return find(2 * index + 1, item);
        }

        else{
            return find(2 * index + 2, item);
        }
    }

    /**
     * Delete the given item from the tree
     * @param target Item to delete
     * @return Deletes target (if found) from tree and returns it; otherwise, returns null
     */
    @Override
    public E delete(E target) {
        try {
            if (target == null)
                throw new NullPointerException();
            return delete(0, target);
        } catch (NullPointerException e){
            System.out.println("Target is a null");
        }
        return null;
    }

    /**
     * Delete the given item from the tree
     * @param index Index of array
     * @param target Item to delete
     * @return Deletes target (if found) from tree and returns it; otherwise, returns null
     */
    private E delete(int index, E target){
        if(index < 0 || index >= capacity || arr[index] == null)
            return null;

        E returnValue = null;

        if(target.compareTo(arr[index]) < 0){
            return delete(2 * index + 1, target);
        }

        else if(target.compareTo(arr[index]) > 0){
            return delete(2 * index + 2, target);
        }

        else {
            returnValue = arr[index];

            if(2 * index + 1 < capacity && arr[2 * index + 1] == null){
                shiftR(index);
            } else if(2 * index + 2 < capacity && arr[2 * index + 2] == null){
                shiftL(index);
            } else {
                if(2 * (2 * index + 1) + 2 < capacity && arr[2 * (2 * index + 1) + 2] == null){
                    shiftL(index);
                } else {
                    E temp = arr[findLargestChild(index * 2 + 1)];
                    delete(arr[findLargestChild(index * 2 + 1)]);
                    arr[index] = temp;
                }
            }
        }

        return returnValue;
    }

    /**
     * Find the node that is the inorder predecessor and replace it with its left child (if any). post: The inorder predecessor is removed from the tree.
     * @param index index of array
     * @return The index of founded data in the array
     */
    private int findLargestChild(int index){
        if((2 * (index * 2 + 2) + 2 < capacity && arr[2 * (index * 2 + 2) + 2] == null)  ) {
            return (index * 2 + 2);
        } else {
            return findLargestChild(index * 2 + 2);
        }
    }

    /**
     * It shifts the array from the left child
     * @param index Index of array
     */
    private void shiftL(int index){
        if(index >= capacity)
            return;
        if(index * 2 + 1 >= capacity) {
            arr[index] = null;
        }
        else{
            arr[index] = arr[index * 2 + 1];
            shiftL(index * 2 + 1);
            shiftR(index * 2 + 2);
        }
    }

    /**
     * It shifts the array from the right child
     * @param index Index of array
     */
    private void shiftR(int index){
        if(index >= capacity)
            return;
        if(index * 2 + 2 >= capacity) {
            arr[index] = null;
        }
        else{
            arr[index] = arr[index * 2 + 2];
            shiftL(index * 2 + 1);
            shiftR(index * 2 + 2);
        }
    }


    /**
     * Remove the given item from the tree
     * @param target Item to remove
     * @return Removes target (if found) from tree and returns true ; otherwise, returns false
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /**
     * Override toString method
     * @return nodes of tree as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(0, 0, sb);
        return sb.toString();
    }

    /** Converts a sub‐tree to a string.
     Performs a preorder traversal.
     @param index index of current node
     @param depth The depth
     @param sb The StringBuilder to save the output
     */
    private void toString(int index, int depth,
                          StringBuilder sb) {
        if(depth == this.depth)
            return;

        for (int i = 0; i < depth; i++) {
            sb.append(" ");
        }
        if (arr[index] == null) {
            sb.append("null\n");
        } else {
            sb.append(arr[index].toString());
            sb.append("\n");
            toString(index * 2 + 1, depth + 1, sb);
            toString(index * 2 + 2, depth + 1, sb);
        }
    }
}