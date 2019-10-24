package wxx.leetcode.code;
import wxx.leetcode.code.BTNOde; 

/**
 * easy-101 :check whether a binary tree is a mirror of itself
 * @version 1.01 2019-10-24 Happy programmer's day!
 * @author Khada Jhin
 */

public class Easy101{
    public static boolean mine(BTNode root){
        System.out.println("I have no idea!");
        return false;
    }
    public static boolean others(BTNode root){
        System.out.println("copy from disscuss");
        return root==null || isSymmetricHelp(root.left, root.right);
    }
    private static boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if(left==null || right==null)
            return left==right;
        if(left.val!=right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
    public static void main(String[] args) {
        /**
         *int[] test = {1,5,3,8,2};
         *System.out.println("success: " + MaxProfit.others(test)); 
         *BTNOde.test();
         *System.out.println("very good!");
         */
        BTNOde node2 = new BTNOde(2, null, null);
        BTNOde node3 = new BTNOde(2, null, null);
        BTNOde root = new BTNOde(1, node2, node3);
        System.out.println(root.val + node2.val + node3.val);
        if(Easy101.others(root) == true){
            System.out.println("[" + root.val + "," + node2.val + "," + node3.val + "] is a symmetric tree.");
        }
        else
            System.out.println("[" + root.val + "," + node2.val + "," + node3.val + "] is not a symmetric tree.");
    }
}