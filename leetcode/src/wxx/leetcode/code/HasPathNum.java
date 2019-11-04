package wxx.leetcode.code;

import java.util.LinkedList;

/**
 * easy-112
 * 
 * @version 1.01 2019-11-3
 * @author Khada Jhin
 */

public class HasPathNum{
    public static boolean mine(BTNode root, int sum){
        if(root == null || root.val > sum) return false;
        LinkedList<BTNode> list = new LinkedList<BTNode>();
        LinkedList<Integer> current_path_sum = new LinkedList<Integer>();
        list.offer(root);
        current_path_sum.offer(root.val);
        BTNode curreBtNode;
        int current_sum;
        while(list.isEmpty() == false ){
            curreBtNode = list.poll();
            current_sum = current_path_sum.poll();
            if(current_sum == sum && curreBtNode.left == null && curreBtNode.right == null) return true;
            if(curreBtNode.left != null && curreBtNode.left.val + current_sum <= sum){
                list.offer(curreBtNode.left);
                current_path_sum.offer(curreBtNode.left.val + current_sum);
            }
            if(curreBtNode.right != null && curreBtNode.right.val + current_sum <= sum){
                list.offer(curreBtNode.right);
                current_path_sum.offer(curreBtNode.right.val + current_sum);
            }
        }
        return false;
    }
    public static boolean mine2(BTNode root, int sum){
        if(root == null) return false;
        LinkedList<BTNode> list = new LinkedList<BTNode>();
        LinkedList<Integer> current_path_sum = new LinkedList<Integer>();
        list.offer(root);
        current_path_sum.offer(root.val);
        BTNode curreBtNode;
        int current_sum;
        while(list.isEmpty() == false ){
            curreBtNode = list.poll();
            current_sum = current_path_sum.poll();
            if(current_sum == sum && curreBtNode.left == null && curreBtNode.right == null) return true;
            if(curreBtNode.left != null){
                list.offer(curreBtNode.left);
                current_path_sum.offer(curreBtNode.left.val + current_sum);
            }
            if(curreBtNode.right != null){
                list.offer(curreBtNode.right);
                current_path_sum.offer(curreBtNode.right.val + current_sum);
            }
        }
        return false; 
    }
    public static boolean others(BTNode root, int sum){
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) return true;
        return others(root.left, sum - root.val) || others(root.right, sum - root.val);
    }
    public static void main(String[] args) {
        int[] test = {-2,-1,-3};
        BTNode ttest = BTNode.toBTree(test);
        System.out.println(ttest);
        System.out.println(mine(ttest, -5));
        System.out.println(mine2(ttest, -5));
        System.out.println(others(ttest, -5));
    }
}