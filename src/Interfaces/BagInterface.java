package Interfaces;

public interface BagInterface<T> {

    public int getCurrentSize();

    public boolean isEmpty();

    public boolean addNewEntry(T anEntry);

    public T remove();

    public boolean remove (T anEntry);

    public void clear();

    public int getFrequencyOf(T anEntry);

    public boolean contains(T anEntry);

    public T[] toArray();

    public void display();

}
