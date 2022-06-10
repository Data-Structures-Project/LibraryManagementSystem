package utility;

import librarymanagementsystem.model.Material;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class SkipList<T extends Comparable<? super T>> implements SkippableList<T> {

    private final SkipNode<T> head = new SkipNode<>(null);
    private final Random rand = new Random();

    @Override
    public void insert(T data) {
        SkipNode<T> skipNode = new SkipNode<>(data);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) {
                // insert with prob = 1/(2^i)
                insert(skipNode, i);
            }
        }
    }

    @Override
    public boolean delete(T target) {
        System.out.println("Deleting " + target);
        T victim = search(target, true);
        if (victim == null)
            return false;
        victim = null;

        for (int i = 0; i < LEVELS; i++) {
            head.refreshAfterDelete(i);
        }

        System.out.println("deleted...");
        return true;
    }

    @Override
    public T search(T data) {
        return search(data, false);
    }

    @Override
    public void print() {
        for (int i = LEVELS - 1; i >= 0; i--) {
            head.print(i);
        }
        System.out.println();
    }

    /**
     * Traverses the Skip List, searches the Materials by given ID.
     * 
     * @param ID Target ID
     * @return Return the found data
     */
    public Material traverseById(int ID) {
        return (Material) head.nodeTraverseByID(ID);
    }

    /**
     * Traverses the Skip List, searches the Materials by given ID.
     * 
     * @param Category     cat
     * @param ArrayList<T> materialList
     * @return Return the found data
     */
    public ArrayList<T> traverseByCategory(librarymanagementsystem.model.Category cat, ArrayList<T> materialList) {
        return head.nodeTraverseByCategory(cat, materialList);
    }


        /**
     * Traverses the Skip List, searches the Materials by given ID.
     * 
     * @param Category     cat
     * @param ArrayList<T> materialList
     * @return Return the found data
     */
    public ArrayList<T> traverseByRate(int rate, ArrayList<T> materialList) {
        return head.nodeTraverseByRate(rate, materialList);
    }

    private void insert(SkipNode<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }

    private T search(T data, boolean print) {
        SkipNode<T> result = null;
        for (int i = LEVELS - 1; i >= 0; i--) {
            if ((result = head.search(data, i, print)) != null) {
                if (print) {
                    System.out.println("Found " + data.toString() + " at level " + i + ", so stopped");
                    System.out.println();
                }
                break;
            }
        }
        if (result == null)
            return null;
        return result.data;
    }

}
