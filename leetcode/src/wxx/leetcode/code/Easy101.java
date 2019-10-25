package wxx.leetcode.code;

/**
 * easy-101 :check whether a binary tree is a mirror of itself
 * @version 1.01 2019-10-24 Happy programmer's day!
 * @author Khada Jhin
 */

public class Easy101{
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
    public static boolean others(BTNode root){
        System.out.println("copy from disscuss");
        return root==null || isSymmetricHelp(root.left, root.right);
    }
    private static boolean isSymmetricHelp(BTNode left, BTNode right){
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
         *BTNode.test();
         *System.out.println("very good!");
         */
        BTNode node4 = new BTNode(3, null, null);
        BTNode node5 = new BTNode(4, null, null);
        BTNode node6 = new BTNode(4, null, null);
        BTNode node7 = new BTNode(3, null, null);
        BTNode node2 = new BTNode(2, node4, node5);
        BTNode node3 = new BTNode(2, node6, node7);
        BTNode root = new BTNode(1, node2, node3);
        if(Easy101.mine(root) == true){
            System.out.println("[" + root.val + "," + node2.val + "," + node3.val + "] is a symmetric tree.");
        }
        else
            System.out.println("[" + root.val + "," + node2.val + "," + node3.val + "] is not a symmetric tree.");
    }
}