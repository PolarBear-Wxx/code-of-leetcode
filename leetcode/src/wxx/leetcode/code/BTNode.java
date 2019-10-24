package wxx.leetcode.code;

/**
 * binary tree node
 * only use in this package
 * @version 1.01 2019-10-24
 * @author Khada Jhin
 */

class BTNode{
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
}