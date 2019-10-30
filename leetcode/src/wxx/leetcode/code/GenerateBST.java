package wxx.leetcode.code;

/**
 * easy-108 convert sorted array in ascending order to a height blanced BST 
 * @version 1.01 2019-10-29
 * @author Khada Jhin
 */

public class GenerateBST{
    private static int[] array;
    /**
     * successed, faster than 100% ..., memory usage less than 100% ...;
     * @param nums
     * @return
     */
    public static BTNode mine(int[] nums){
        array = nums;
        if(array.length==0) return null;
        int left = 0;
        int right = array.length - 1;
        if(left==right) return new BTNode(array[left]);
        int index = (left+right)/2;
        BTNode root = new BTNode(array[index]);
        root.left = gh(left, index-1);
        root.right = gh(index+1, right);
        return root;
    }
    public static BTNode gh(int left, int right){
        if(left>right) return null;
        int index = (left+right) / 2;
        BTNode subRoot = new BTNode(array[index]);
        subRoot.left = gh(left, index-1);
        subRoot.right = gh(index+1, right);
        return subRoot;
    }
}