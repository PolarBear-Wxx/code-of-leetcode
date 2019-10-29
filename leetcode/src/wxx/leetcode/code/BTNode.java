package wxx.leetcode.code;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue; //java中import一个类，有两种方式，具体到名和到姓.*;

/**
 * binary tree node;
 * only use in this package -- 2019-10-25修改为public类
 * @version 1.01 2019-10-24
 * @author Khada Jhin
 */

public class BTNode{
    int val;
    BTNode left;
    BTNode right;
    BTNode(int v){
        val = v;
    }
    BTNode(int v, BTNode l, BTNode r){
        val = v;
        left = l;
        right = r;
    }
    public static void test(){
        System.out.println("BTNode import successfully!");
    }
    /**
     * convert a integer array to a binary tree;
     * "-1" indicates a empty node in the integer array!!!
     * @version 1.01 2019-10-25
     * @author Khada Jhin 
     */
    public static BTNode toBTree(int[] array){
        if(array[0]==-1){
            System.out.println("warning: your input is an empty tree!");
            return null;   // "-1" indicates a empty node in the integer array!!! 
        }
        BTNode root = new BTNode(array[0]);
        int index = 1;
        //Stack<BTNode> stack = new Stack<BTNode>();
        //stack.push(root);
        ArrayBlockingQueue<BTNode> queue = new ArrayBlockingQueue<BTNode>(20);
        queue.add(root);
        BTNode top,temp;
        while(queue.isEmpty()==false && index<array.length){
            top = queue.poll();
            if(array[index]!=-1){
                temp = new BTNode(array[index]);
                top.left = temp;
                queue.add(temp);
                index += 1;
            }
            else{
                top.left = null;
                index += 1;
            }
            if(array[index]!=-1){
                temp = new BTNode(array[index]);
                top.right = temp;
                queue.add(temp);
                index += 1;
            }
            else{
                top.right = null;
                index += 1;
            }
        }
        return root;
    }
    /**
     * convert a string to a binary tree;
     * establishe on 2019-10-25
     * @version 1.01 accomphlished on ???
     * @author Khada Jhin
     */
    /*
    public static BTNode toBTree(String s){

    }
    */
    /**
     * convert a bianry tree to a integer array;
     * use "-1" to show null node
     * @version 1.01 2019-10-29
     * @author Khada Jhin
     */
    /*
    public static int[] toArray(BTNode root){

    }
    */
}