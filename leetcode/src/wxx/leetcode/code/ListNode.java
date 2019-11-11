package wxx.leetcode.code;
import java.util.ArrayList;
 /** 
 * 2019-11-11
 */

public class ListNode{
    int val;
    ListNode next;
    ListNode(int x){
        val = x;
        next = null;
    }
    public static ListNode toListNode(int[] array){
        if(array.length == 0) return null;
        ListNode head = new ListNode(array[0]);
        ListNode cur_generate;
        ListNode p = head;
        for(int i = 1; i < array.length; i++){
            cur_generate = new ListNode(array[i]);
            p.next = cur_generate;
            p = p.next;
        }
        return head;
    }
    public String toString(){
        /*
        if(this == null){
            System.out.println("The List is null!");
            return null;
        }*/
        ArrayList<Integer> result = new ArrayList<Integer>();
        ListNode p = this;
        while(p != null){
            result.add(p.val);
            p = p.next;
        }
        return result.toString();
    }
}