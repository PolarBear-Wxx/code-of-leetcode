package wxx.leetcode.code;

import java.util.ArrayList;

/**
 * made for leetcode
 * 2019-11-7 
 * @author Khada Jhin
 */

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
}
public class Solution{
    /**
     * easy-141 Linked List Cycle
     */
    public boolean hasCycle(ListNode head){
        if(head == null) return false;
        ArrayList<ListNode> help = new ArrayList<ListNode>();
        help.add(head);
        ListNode curNode = head;
        while(curNode != null){
            curNode = curNode.next;
            if(help.contains(curNode)) return true;
            help.add(curNode);
        }
        return false;
    }
}