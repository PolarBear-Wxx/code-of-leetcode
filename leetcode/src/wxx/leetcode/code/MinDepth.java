package wxx.leetcode.code;

import java.util.LinkedList;
import java.util.Queue;

/**
 * easy-111 The mininum depth is the number of nodes along 
 * the shortest path from the root node to the nearest leaf
 * node.
 * @version 1.01 2019-11-2
 * @author Khada Jhin
 */

public class MinDepth{
    // idea : the level of the first null node is the mininum depth according the definiton.
    public static int mine(BTNode root){
        if(root==null) return 0;
        Queue<BTNode> queue = new LinkedList<BTNode>();
        queue.offer(root);
        int current_level = 1;
        int output = 0;
        int flag = 0;
        while(queue.isEmpty()==false&&flag!=1){
            int level_num = queue.size();
            for(int i=0; i<level_num; i++){
                if(queue.peek().left!=null) queue.offer(queue.peek().left);
                if(queue.peek().right!=null) queue.offer(queue.peek().right);
                if(queue.peek().left==null && queue.peek().right==null){
                    output = current_level;
                    flag = 1;
                }
                queue.poll();
            }
            current_level += 1;
        }
        return output;
    }
    public static void main(String[] args) {
        int[] test = {1,-1,2,3,4,5};
        BTNode test_rode = BTNode.toBTree(test);
        System.out.println(test_rode);
        System.out.println("output: " + mine(test_rode));
    }
}