package wxx.leetcode.code;

//import java.util.Queue;
//import java.util.Stack;
//import java.util.concurrent.ArrayBlockingQueue; //java中import一个类，有两种方式，具体到名和到姓.*;
import java.util.ArrayList;

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
        KJLinkedQueue<BTNode> queue = new KJLinkedQueue<BTNode>();
        queue.enqueue(root);
        BTNode top,temp;
        while(queue.isEmpty()==false && index<array.length){
            top = queue.dequeue();
            if(array[index]!=-1){
                temp = new BTNode(array[index]);
                top.left = temp;
                queue.enqueue(temp);
                index += 1;
                if(index>=array.length) break;
            }
            else{
                top.left = null;
                index += 1;
                if(index>=array.length) break;
            }
            if(array[index]!=-1){
                temp = new BTNode(array[index]);
                top.right = temp;
                queue.enqueue(temp);
                index += 1;
                if(index>=array.length) break;
            }
            else{
                top.right = null;
                index += 1;
                if(index>=array.length) break;
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
     * convert a bianry tree to a String;
     * @version 1.01 2019-10-29
     * @author Khada Jhin
     */
    
    public static StringBuilder toString(BTNode root){
        if(root==null) return null;
        ArrayList<Integer> array = new ArrayList<Integer>();  //ArrayList<E>  中的E不能是“int”？
        KJLinkedQueue<BTNode> queue =  new KJLinkedQueue<BTNode>();
        array.add(root.val);
        queue.enqueue(root);
        BTNode head;
        while(!queue.isEmpty()){
            head = queue.dequeue();
            if(head.left==null) array.add(null);
            else{
                array.add(head.left.val);
                queue.enqueue(head.left);
            }
            if(head.right==null) array.add(null);
            else{
                array.add(head.right.val);
                queue.enqueue(head.right);
            }
        }
        while(array.get(array.size()-1)==null){
            array.remove(array.size()-1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Integer i : array){
            sb.append(i); // equals to "sb.append(i.toString());"
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb;
    }
    public static void main(String[] args) {
        int[] test = {1,2,3,-1,5,6,-1,7,-1,8};
        BTNode ttest =  BTNode.toBTree(test);
        System.out.println(BTNode.toString(ttest));
    }
}