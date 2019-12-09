package wxx.leetcode.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * easy-107 
 * @version 1.01 2019-10-31
 * @author Khada Jhin
 */

public class BTLevelOrderTraversal{
    public static List<List<Integer>> mine(BTNode root){
        Queue<BTNode> queue = new LinkedList<BTNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        if(root==null) return wrapList;
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++){
                if(queue.peek().left!=null) queue.offer(queue.peek().left);
                if(queue.peek().right!=null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
        //System.out.println("先搞明白List接口和LinkedList类！！！");
    }
    public static void main(String[] args) {
        int[] test = {3,9,20,-1,-1,15,7};
        BTNode root = BTNode.toBTree(test);
        List<List<Integer>> reuslt = mine(root);
        System.out.println(reuslt);
        System.out.println(root);
        System.out.println("Hello Git!");
    }
}