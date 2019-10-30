package wxx.leetcode.code;

/**
 * my own Queue interface;
 * @version 1.01 2019-10-30
 * @author Khada Jhin
 */

public interface KJQueue<E>{
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}