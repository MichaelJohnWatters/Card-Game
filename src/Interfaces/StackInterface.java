package Interfaces;

/**
 * A Generic Type of Stack to be used in this application.
 * Will be used in the Deck Class.
 * @param <T> the Type of the Stack.
 */
public interface StackInterface<T> {

    /**
     * Push a new entity on to the top of the stack.
     * @param newEntry entity to add
     */
    public void push(T newEntry);

    /**
     * Remove the entity at the stop of the stack and return it.
     * @return Object at the top of the stack.
     */
    public T pop();

    /**
     * Returns the Object at the top of the stack but does not remove it.
     * @return object at the top of the stack.
     */
    public T peek();

    /**
     * Checks if the stack is empty or not.
     * @return returns true if the stack is empty
     */
    public boolean isEmpty();

    /**
     * Clears the stack
     */
    public void clear();

}
