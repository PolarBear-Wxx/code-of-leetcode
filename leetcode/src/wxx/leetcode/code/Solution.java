package wxx.leetcode.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.lang.model.util.ElementScanner6;

/**
 * made for leetcode
 * 2019-11-7 
 * @author Khada Jhin
 */

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

    /**
     * easy-217 Contains Duplicate
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * easy-203 Remove Linked List Elements
     */
    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val){
            head = head.next;
        }
        ListNode p = head;
        while(p != null && p.next != null){
            if(p.next.val == val) p.next = p.next.next;
            else p = p.next;
        }
        return head;
    }

    /**
     * easy-206 Reverse Linked List
     */
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ListNode p = head;
        ListNode newHead = null,generate;
        while(p != null){
             generate = new ListNode(p.val);
             generate.next = newHead;
             newHead = generate;
             p = p.next;
        }
        /* !! loss the old one !!
        ListNode newHead = null;
        ListNode old_head;
        while(head != null){
            old_head = head.next;
            head.next = newHead;
            newHead = head;
            head = old_head;
        }*/
        System.out.println(head);
        return newHead;
    }

    /**
     * easy-268
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        if(nums[nums.length-1] == nums.length - 1) return nums.length;
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i){ 
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * easy-94 Binary Tree Inorder Traversal
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return null;
        inorder_help(root, result);
        /*
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode cur_node = null;
        while(!stack.isEmpty()){
            cur_node = stack.peek();
            if(cur_node.left != null){
                stack.push(cur_node.left);
            }
            else{
                result.add(cur_node.val);
                stack.pop();
                if(cur_node.right != null) stack.push(cur_node.right);
            }
            //stack.pop();
        }
        */
        return result;
    }
    private void inorder_help(TreeNode aroot, ArrayList<Integer> alist){
        if(aroot == null) return;
        if(aroot.left != null) inorder_help(aroot.left, alist);
        alist.add(aroot.val);
        if(aroot.right != null) inorder_help(aroot.right, alist);
    }

    /**
     * easy-283 Move Zeros
     */
    public void moveZeroes(int[] nums) {
        int nonzero_count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[nonzero_count] = nums[i];
                nonzero_count += 1;
            }
        }
        for(int i = nonzero_count; i < nums.length; i++){
            nums[i] = 0;
        }
    }

    /**
     * easy-448 Find All Numbers Disappeared in an Array
     */
    public ArrayList<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int n = nums.length;
        int help[] = new int[n];
        for(int i = 0; i < n; i++){
            help[nums[i] - 1] = -1;
        }
        for(int i = 0; i < n; i++){
            if(help[i] != -1){
                result.add(i+1);
            }
        }
        return result;
    }

    /**
     * easy-234 Plindrome Linked List
     */
    public boolean isPalindrome(ListNode head) {
        ListNode faster = head, slower = head;
        while(faster != null && faster.next != null){
            faster = faster.next.next;
            slower = slower.next;
        }
        if(faster != null) slower = slower.next;
        slower = reverseList(slower);
        while(slower != null){
            if(slower.val != head.val) return false;
            slower = slower.next;
            head = head.next;
        }
        return true;
    }

    /**
     * easy-485 Max Consecutive Ones
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                count += 1;
            }
            else{
                if(count > result) result = count;
                count = 0;
            }
        }
        if(count > result) result = count; //处理数组中没有“0”的情况。
        return result;
    }

    /**
     * tag: array
     * easy-509 Fibonacci Number
     * @param N
     * @return
     */
    public int fib(int N) {
        if(N == 0 || N == 1)
            return N;
        return fib(N-1) + fib(N-2);
    }
    public int fib_iterative(int N){
        if(N <= 1)
            return N;
        int a = 0, b = 1;
        while(N > 1){
            int sum = a + b;
            a = b;
            b = sum;
            N--;
        }
        return b;
    }

    /**
     * tag: array
     * easy-532  K-diff Pairs in an Array
     * unaccepted!!!
     */
    public int findPairs(int[] nums, int k) {
        int length = nums.length;
        int pairs = 0;
        for(int i = 0; i < length - 1; i++){
            for(int j = i + 1; j < length; j++){
                if(nums[i] - nums[j] == k){
                    pairs++;
                    System.out.println(i + "," + j);
                    break;
                }
            }
            if(k == 0)
                break;
            for(int j = i + 1; j < length; j++){
                if(nums[j] - nums[i] == k){
                    pairs++;
                    System.out.println(i + "," + j);
                    break;
                }
            }
        }
        return pairs;
    }

    /**
     * tag: array
     * easy-849 Maximize Distance to Closest Person
     */
    public int maxDistToClosest(int[] seats) {
        int head = 0, tail = seats.length - 1;
        int forepart = 0, afterbody = 0, center_section = 0;
        while(seats[head] == 0){
            head++;
            forepart++;
        }
        while(seats[tail] == 0){
            tail--;
            afterbody++;
        }
        if(head == tail){
            return Math.max(forepart, afterbody);
        }
        int head_section = 0, max_center = 0;
        while(head != tail){
            head++;
            while(seats[head] == 0){
                head++;
                head_section++;
            }
            max_center = Math.max(max_center, head_section);
            head_section = 0;
        }
        /*
        if(max_center == 0)
            center_section = max_center;
        else if(max_center % 2 == 1)
            center_section = max_center / 2 + 1;
        else
            center_section = max_center / 2;
        */
        center_section = (max_center + 1) / 2;
        int temp = Math.max(forepart, afterbody);
        return Math.max(temp, center_section);
    }

    /**
     * tag: array
     * easy-561 Array Partition I
     */
    public int arrayPairSum(int[] nums) {
        bubbleSort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i += 2){
            sum += nums[i];
        }
        return sum;
    }
    public void bubbleSort(int[] array){
        if(array == null){
            System.out.println("The array is null!");
            return;
        }
        if(array.length <= 1)
            return;
        int len = array.length;
        boolean changed = false;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len - 1 - i; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    changed = true;
                }
            }
            if(changed == false)
                break;
            else
                changed = false;
            //Arrays.sort(array);
        }
    }
    
    /**
     * tag: array
     * easy-566 Reshape the Matrix
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length, column = nums[0].length;
        if(row * column != r * c)
            return nums;
        int[] temp = new int[row * column];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < column; j++){
                temp[i * column + j] = nums[i][j];
            }
        }
        int[][] res = new int[r][c];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                res[i][j] = temp[i * c + j];
            }
        }
        return res;
    }
}