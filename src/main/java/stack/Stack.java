package stack;

import java.util.ArrayList;

/**
 * Created by jhu on 18/5/17.
 * customized stack
 */
public class Stack<T> {

    private ArrayList<T> stack;
    public int size;
    public int top;

    public Stack(int size) {
        this.size = size;
        this.stack = new ArrayList(size);
        this.top = 0;
    }

    public void push(T element) {
        if (top + 1 > size)
            throw new FullStackException("Stack is full");

        stack.add(element);
        this.top++;
    }

    public T pop() {
        if (this.isEmpty())
            throw new EmptyStackException("Stack is empty");

        return stack.remove(--top);
    }

    public T getTop() {
        if (isEmpty())
            return null;

        return  stack.get(top-1);
    }

    public void setTop(int top) {
        this.top = top;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
