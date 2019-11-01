package wxx.leetcode.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
  * easy-110 determine a binary tree is a balancesd tree or not
  * @version 1.01 2019-11-1
  * @author Khada Jhin
  */

public class IsBalanced{
    public static boolean mine(BTNode root){
        LinkedList<BTNode> list = new LinkedList<BTNode>();
        List<Integer> levelOfAllLeaves = new ArrayList<Integer>();
        if(root==null) return true;
        list.offer(root);
        int currentNumOfLayers = 1;
        while(!list.isEmpty()){
            int levelNum = list.size();
            //int currentNumOfLayers = 1;
            for(int i=0; i<levelNum; i++){
                if(list.peek().left!=null) list.offer(list.peek().left);
                if(list.peek().right!=null) list.offer(list.peek().right);
                if(list.peek().left==null && list.peek().right==null) 
                    levelOfAllLeaves.add(currentNumOfLayers);
                list.poll();
            }
            currentNumOfLayers += 1;
        }
        int theDeepestLeaf = levelOfAllLeaves.get(0);
        int theShallowestLeaf = levelOfAllLeaves.get(0);
        for(Integer i : levelOfAllLeaves){
            if(i<theShallowestLeaf) theShallowestLeaf = i;
            if(i>theDeepestLeaf) theDeepestLeaf = i;
        }
        System.out.println("levelOfAllLeaves: " + levelOfAllLeaves);
        if(theDeepestLeaf-theShallowestLeaf>1) return false;
        return true;
    }
    public static int depth(BTNode root){
        if(root==null) return 0;
        int leftDepth = root.left==null ? 0 : depth(root.left);
        int rightDepth = root.right==null ? 0 : depth(root.right);
        return Math.max(leftDepth, rightDepth)+1;
    }
    public static boolean others(BTNode root){
        if(root==null) return true;
        int left = depth(root.left);
        int right = depth(root.right);
        boolean debug = Math.abs(left-right) <= 1;
        return debug && others(root.left) && others(root.right);
    }
    public static void main(String[] args) {
        int[] test = {1,2,2,3,3,-1,-1,4,4};
        BTNode root = BTNode.toBTree(test);
        System.out.println(root);
        System.out.println(mine(root));
        System.out.println(others(root));
    }
}