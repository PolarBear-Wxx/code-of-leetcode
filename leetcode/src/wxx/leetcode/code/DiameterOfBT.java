package wxx.leetcode.code;

//import javax.swing.tree.TreeNode;

/**
 * easy-534 learn from discuss
 */

public class DiameterOfBT{
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root){
        //int max = 0;
        maxDepth(root);
        return max;
    }
    private int maxDepth(TreeNode root){
        if(root == null) return 0;
        int left = root.left == null ? 0 : maxDepth(root.left);
        int right = root.right == null ? 0 : maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}