package utility;

import java.util.ArrayList;
import java.util.Locale.Category;

import librarymanagementsystem.model.Material;

class SkipNode<N extends Comparable<? super N>> {

    N data;
    @SuppressWarnings("unchecked")
    SkipNode<N>[] next = (SkipNode<N>[]) new SkipNode[SkippableList.LEVELS];

    SkipNode(N data) {
        this.data = data;
    }

    void refreshAfterDelete(int level) {
        SkipNode<N> current = this;
        while (current != null && current.getNext(level) != null) {
            if (current.getNext(level).data == null) {
                SkipNode<N> successor = current.getNext(level).getNext(level);
                current.setNext(successor, level);
                return;
            }

            current = current.getNext(level);
        }
    }

    void setNext(SkipNode<N> next, int level) {
        this.next[level] = next;
    }

    SkipNode<N> getNext(int level) {
        return this.next[level];
    }

    SkipNode<N> search(N data, int level, boolean print) {
        if (print) {
            System.out.print("Searching for: " + data + " at ");
            print(level);
        }

        SkipNode<N> result = null;
        SkipNode<N> current = this.getNext(level);
        while (current != null && current.data.compareTo(data) < 1) {
            if (current.data.equals(data)) {
                result = current;
                break;
            }

            current = current.getNext(level);
        }

        return result;
    }

    void insert(SkipNode<N> skipNode, int level) {
        SkipNode<N> current = this.getNext(level);
        if (current == null) {
            this.setNext(skipNode, level);
            return;
        }

        if (skipNode.data.compareTo(current.data) < 1) {
            this.setNext(skipNode, level);
            skipNode.setNext(current, level);
            return;
        }

        while (current.getNext(level) != null && current.data.compareTo(skipNode.data) < 1 &&
                current.getNext(level).data.compareTo(skipNode.data) < 1) {

            current = current.getNext(level);
        }

        SkipNode<N> successor = current.getNext(level);
        current.setNext(skipNode, level);
        skipNode.setNext(successor, level);
    }

    void print(int level) {
        System.out.print("level " + level + ": [ ");
        int length = 0;
        SkipNode<N> current = this.getNext(level);
        while (current != null) {
            length++;
            System.out.print(current.data + " ");
            current = current.getNext(level);
        }

        System.out.println("], length: " + length);
    }

    /**
     *
     * @param ID
     * @return
     */
    public boolean compareTo(int ID) {
        if (((Material) data).getId() == ID)
            return true;

        else
            return false;
    }

    /**
     *
     * @param Category category
     * @return
     */
    public boolean compareTo(librarymanagementsystem.model.Category category) {
        if (((Material) data).getCategory().compareTo(category) == 0)
            return true;

        else
            return false;
    }

    /**
     *
     * @param Category category
     * @return
     */
    public boolean compareByRate(int rate) {
        if (((Material) data).getRateAve() > rate)
            return true;
        else
            return false;
    }

    ArrayList<N> nodeTraverseByRate(int rate, ArrayList rateList) {
        SkipNode<N> current = this.getNext(0); // levela dikat.

        while (current != null) {
            if ((current.compareTo(rate) == true))
                rateList.add(current.data);

            current = current.getNext(0);
        }
        return rateList;
    }

    /**
     * Traverses the Skip List, searches the Materials by given ID.
     * 
     * @param ID Target ID
     * @return Return the found data
     */
    N nodeTraverseByID(int ID) {
        SkipNode<N> current = this.getNext(0); // levela dikat.
        while (current != null) {
            if ((current.compareTo(ID) == true))
                return current.data;
            current = current.getNext(0);
        }
        return null;
    }

    ArrayList<N> nodeTraverseByCategory(librarymanagementsystem.model.Category category, ArrayList<N> materialList) {
        SkipNode<N> current = this.getNext(0); // levela dikat.

        while (current != null) {
            if ((current.compareTo(category) == true))
                materialList.add(current.data);

            current = current.getNext(0);
        }
        return materialList;
    }

    ArrayList<N> nodeTraverseByCategory(int rate, ArrayList<N> materialList) {
        SkipNode<N> current = this.getNext(0); // levela dikat.

        while (current != null) {
            if ((current.compareTo(rate) == true))
                materialList.add(current.data);

            current = current.getNext(0);
        }
        return materialList;
    }

}
