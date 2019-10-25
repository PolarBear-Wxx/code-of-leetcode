package wxx.leetcode.code;

import java.util.Arrays;
import java.util.Stack;

/**
 * easy-101 :check whether a binary tree is a mirror of itself
 * @version 1.01 2019-10-24 Happy programmer's day!
 * @author Khada Jhin
 */

public class Easy101{
    /**
     * my recursive solution, it can't solve this problem correctly!!!
     * @param root
     * @return
     */
    public static boolean mine(BTNode root){
        //System.out.println("I have no idea!");
        if(root == null)
            return true;
        else
            return sh(root.left, root.right);
    }
    public static boolean sh(BTNode left, BTNode right){
        if(left == null && right == null)
            return true;
        else if((left == null && right != null) || (left != null && right == null))
            return false;
        return (left.val == right.val) && sh(left.left, left.right) && sh(right.left,right.right) && sh(left.right, right.left);
    }
    /**
     * my non-recursive solution
     * @param root
     * @return
     */
    public static boolean my_non_recursive(BTNode root){
        if(root == null)
            return true;
        Stack<BTNode> stack = new Stack<BTNode>();
        if(root.left != null){
            if(root.right == null)
                return false;
            else{
                stack.push(root.left);
                stack.push(root.right);
            }
        }
        else if(root.right!=null)
            return false;
        BTNode left, right;
        while(!stack.empty()==true){
            //if(stack.size()%2!=0)  return false;  这行代码有必要吗？
            //good indentation habit : 小括号里的运算符就不要空格了
            right = stack.pop();
            left = stack.pop();
            if(left.val!=right.val) return false;
            if(left.left!=null){
                if(right.right==null) return false;
                stack.push(left.left);
                stack.push(right.right);
            }
            else if(right.right!=null) return false;
            if(left.right!=null){
                if(right.left==null)  return false;
                stack.push(left.right);
                stack.push(right.left);
            }
            else if(right.left==null)  return false;
        }
        return true;
    }
    /**
     * copy from discuss 
     * recursive solution
     * @param root
     * @return
     */
    public static boolean others(BTNode root){
        System.out.println("copy from disscuss");
        return root==null || isSymmetricHelp(root.left, root.right);
    }
    private static boolean isSymmetricHelp(BTNode left, BTNode right){
        if(left==null || right==null)
            return left==right; //null 也可以进行“==”运算
        if(left.val!=right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
    public static void main(String[] args) {
        /**
         *int[] test = {1,5,3,8,2};
         *System.out.println("success: " + MaxProfit.others(test)); 
         *BTNode.test();
         *System.out.println("very good!");
         */
        /* 
        BTNode node4 = new BTNode(3, null, null);
        BTNode node5 = new BTNode(4, null, null);
        BTNode node6 = new BTNode(4, null, null);
        BTNode node7 = new BTNode(3, null, null);
        BTNode node2 = new BTNode(2, node4, node5);
        BTNode node3 = new BTNode(2, node6, node7);
        BTNode root = new BTNode(1, node2, node3);
        */
        int[] data ={1,2,2,3,4,4,3};
        BTNode root = BTNode.toBTree(data);
        if(Easy101.my_non_recursive(root) == true){
            System.out.println("input: " + Arrays.toString(data) + " is a symmetric tree.");
        }
        else
            System.out.println("input: " + Arrays.toString(data) + " is not a symmetric tree.");
    }
}