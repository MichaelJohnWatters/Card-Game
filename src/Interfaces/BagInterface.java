package Interfaces;

import main.Card;

/**
 * Generic Type of Bag Interface, for this application.
 * Will be Used in the CardSlotsBag class.
 *
 * @param <T> the Type of the Bag
 */
public interface BagInterface<T> {

    /**
     * get the current Size of the Bag.
     *
     * @return size of the bag
     */
    public int getCurrentSize();

    /**
     * Check if the bag is empty or not
     *
     * @return returns true if empty false if not
     */
    public boolean isEmpty();

    /**
     * Add a new entity to the bag
     *
     * @param anEntry the entity to add.
     * @return returns true if added or false if not
     */
    public boolean addNewEntry(T anEntry);

    /**
     * Removes the last entity in the bag.
     *
     * @return
     */
    public T remove();

    /**
     * Removes an entity if it can find the input entity
     *
     * @param anEntry the entity to remove if can be found.
     * @return the Card found and removed.
     */
    public Card remove(T anEntry);

    /**
     * Clear the Bag.
     */
    public void clear();

    /**
     * Check if the bag contains the given entity.
     *
     * @param anEntry the entity to find
     * @return returns true if found, false if not.
     */
    public boolean contains(T anEntry);

    /**
     * Convert the Bag to an array.
     *
     * @return converts the bag into any array.
     */
    public T[] toArrayCopy();

}
