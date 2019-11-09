package wxx.leetcode.code;

import java.util.ArrayList;
import java.util.Arrays;

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

    /**
     * easy-160 Intersection of Two Linked List
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode a_cur = headA;
        ListNode b_cur = headB;
        while(a_cur != null){
            while(b_cur != null){
                if(a_cur == b_cur) return a_cur;
                else b_cur = b_cur.next;
            }
            b_cur = headB;
            a_cur = a_cur.next;
        }
        return null;
    }
    /**
     * easy-160 a smart solution from discuss
     * You can prove that: say A length = a + c, B length = b + c, after switching pointer, 
     * pointer A will move another b + c steps, pointer B will move a + c more steps, 
     * since a + c + b + c = b + c + a + c, it does not matter what value c is. 
     * Pointer A and B must meet after a + c + b (b + c + a) steps. If c == 0, they meet at NULL.
     * Thanks, hpplayer. This solution is very smart.
     */
    public ListNode getIntersectionNode_others(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;
        
        ListNode a = headA;
        ListNode b = headB;
        
        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }
        
        return a;
    }

    /**
     * easy-167 Two Sum II - input array is sorted
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for(int i = 0; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                if(numbers[i] + numbers[j] == target){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * easy-169 Majority Element
     */
    public int majorityElement(int[] nums) {
        //int len = nums.length;
        //int map[] = new int[len];
        Arrays.sort(nums);
        int len = nums.length;
        return nums[len / 2];
    }

    /**
     * easy-189 Rotate Array (tag:Array)
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        for(int i = 0; i < k; i++){
            int pre = nums[0];
            int tem;
            for(int j = 0; j < n-1; j++){
                tem = nums[j+1];
                nums[j+1] = pre;
                pre = tem;
            }
            nums[0] = pre;
        }
    }
}