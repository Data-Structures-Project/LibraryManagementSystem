package utility;
/**
 * Interface for Binary Search Tree
 * @param <E> Generic Type
 */
public interface SearchTree<E> {
    /**
     * Inserts item where it belongs in the tree.
     * @param item Data to add
     * @return Returns true if item is inserted; false if it isn’t (already in tree)
     */
    boolean add(E item);

    /**
     * Checks if item is in tree
     * @param target Item to search
     * @return Returns true if target is found in the tree
     */
    boolean contains(E target);

    /**
     * Finds the item to search for.
     * @param target Item to search
     * @return Returns a reference to the data in the node that is equal to target . If no such node is found, returns null
     */
    E find(E target);

    /**
     * Delete the given item from the tree
     * @param target Item to delete
     * @return Deletes target (if found) from tree and returns it; otherwise, returns null
     */
    E delete(E target);

    /**
     * Remove the given item from the tree
     * @param target Item to remove
     * @return Removes target (if found) from tree and returns true ; otherwise, returns false
     */
    boolean remove(E target);
}
