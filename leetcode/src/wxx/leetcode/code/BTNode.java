package wxx.leetcode.code;

/**
 * binary tree node
 * only use in this package
 * @version 1.01 2019-10-24
 * @author Khada Jhin
 */

class BTNOde{
    int val;
    BTNOde left;
    BTNOde right;
    BTNOde(int v){
        val = v;
    }
    BTNOde(int v, BTNOde l, BTNOde r){
        val = v;
        left = l;
        right = r;
    }
    public static void test(){
        System.out.println("BTNode import successfully!");
    }
}