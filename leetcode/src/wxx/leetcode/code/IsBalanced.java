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
    public static void main(String[] args) {
        int[] test = {1,1,1,1,1,1,1,-1,-1,1,-1,1,1,1,-1,-1,-1,1};
        BTNode root = BTNode.toBTree(test);
        System.out.println(root);
        System.out.println(mine(root));
    }
}