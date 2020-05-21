package wxx.leetcode.code;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Delayed;

import javax.lang.model.util.ElementScanner6;

import sun.launcher.resources.launcher;
import sun.security.util.Length;

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

    /**
     * tag: stack
     * easy-682. Baseball Game
     */
    public int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> points = new Stack<>();
        for(int i = 0; i < ops.length; i++){
            if(ops[i].equals("C")){
                Integer invalidPoint = points.pop();
                sum -= invalidPoint;
            }
            else if(ops[i].equals("D")){
                Integer last_valid_point = points.peek();
                points.push(2 * last_valid_point);
                sum += 2 * last_valid_point;
            }
            else if(ops[i].equals("+")){
                Integer the_first = points.pop();
                Integer the_second = points.peek();
                points.push(the_first);
                points.push(the_first + the_second);
                sum += the_first + the_second;
            }
            else{
                Integer point = Integer.parseInt(ops[i]);
                points.push(point);
                sum += point;
            }
        }
        return sum;
    }
    public int calPoints2(String[] ops) {
        int sum = 0;
        //Stack<Integer> points = new Stack<>();
        Integer a = 0, b = 0, c = 0; // 连续出现两个“C”是没有意义的
        for(int i = 0; i < ops.length; i++){
            if(ops[i].equals("C")){
                //Integer invalidPoint = points.pop();
                sum -= c;
                c = b;
                b = a;
                System.out.println("char = " + ops[i] + " : a = " + a + ", b = " + b + ", c = " + c + ", sum = " + sum);
            }
            else if(ops[i].equals("D")){
                //Integer last_valid_point = points.peek();
                //points.push(2 * last_valid_point);
                sum += 2 * c;
                a = b;
                b = c;
                c *= 2;
                System.out.println("char = " + ops[i] + " : a = " + a + ", b = " + b + ", c = " + c + ", sum = " + sum);
            }
            else if(ops[i].equals("+")){
                //Integer the_first = points.pop();
                //Integer the_second = points.peek();
                //points.push(the_first);
                //points.push(the_first + the_second);
                sum = sum + b + c;
                int temp = b;
                a = b;
                b = c;
                c = temp + c;
                //a = b;
                //b = c;
                //c = b + c;
                System.out.println("char = " + ops[i] + " : a = " + a + ", b = " + b + ", c = " + c + ", sum = " + sum);
            }
            else{
                Integer point = Integer.parseInt(ops[i]);
                //points.push(point);
                sum += point;
                a = b;
                b = c;
                c = point;
                System.out.println("char = " + ops[i] + " : a = " + a + ", b = " + b + ", c = " + c + ", sum = " + sum);
            }
        }
        return sum;
    }

    /**
     * tag: stack
     * easy-844. Backspace String Compare
     */
    public boolean backspaceCompare(String S, String T) {
        int s_length = S.length();
        int t_length = T.length();
        Stack<Character> sq = new Stack<>();
        Stack<Character> tq = new Stack<>();
        for(int i = 0; i < s_length; i++){
            if(S.charAt(i) == '#'){
                if(!sq.empty())
                    sq.pop();
            }
                //sq.pop();
            else
                sq.push(S.charAt(i));
        }
        for(int j = 0; j < t_length; j++){
            if(T.charAt(j) == '#'){
                if(!tq.empty())
                    tq.pop();
            }
                //tq.pop();
            else
                tq.push(T.charAt(j));
        }
        return sq.equals(tq);
    }
    public boolean backspaceCompare2(String S, String T) {
        int S_size = 0;
        int T_size = 0;
        char[] s = new char[S.length()];
        char[] t = new char[T.length()];
        for(char c : S.toCharArray()){
            if(c == '#' && S_size != 0){
                S_size--;
            }
            else if(c != '#'){
                s[S_size] = c;
                S_size++;
            }
            //System.out.println(Arrays.toString(s));
        }
        for(char c : T.toCharArray()){
            if(c == '#' && T_size != 0)
                T_size--;
            else if(c != '#'){
                t[T_size] = c;
                T_size++;
            }
            //System.out.println(Arrays.toString(t));
        }
        System.out.println(Arrays.toString(s));
        System.out.println(S_size);
        System.out.println(Arrays.toString(t));
        System.out.println(T_size);
        if(S_size != T_size)
            return false;
        for(int i = 0; i < S_size; i++){
            if(s[i] != t[i])
                return false;
        }
        return true;
    }
    public boolean backspaceCompare3(String S, String T){
        int i = S.length() - 1;
        int j = T.length() - 1;
        int s_skip = 0, t_skip = 0;
        while(i >= 0 || j >=0){
            while(i >= 0){
                if(S.charAt(i) == '#'){
                    s_skip++;
                    i--;
                }
                else if(s_skip != 0){
                    s_skip--;
                    i--;
                }
                else break;
            }
            while(j >= 0){
                if(T.charAt(j) == '#'){
                    t_skip++;
                    j--;
                }
                else if(t_skip != 0){
                    t_skip--;
                    i--;
                }
                else break;
            }
            //if( (i <= 0) != (j <= 0)) return false;
            if( i >=0 && j >= 0){
                if(S.charAt(i) != T.charAt(j)) return false;
            }
            if( (i >= 0) != (j >= 0)) return false;
            i--;
            j--;
        }
        return true;
    }

    /**
     * tag: Linked List
     * easy-1290. Convert Binary Number in a Linked List to Integer
     */
    public int getDecimalValue(ListNode head) {
        int res = 0;
        while(head.next != null){
            res = (res + head.val) * 2;
            head = head.next;
        }
        return res + head.val;
    }

    /**
     * Pick One
     * medium-1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
     */
    public int maxSideLength(int[][] mat, int threshold) {
        //int res = 0;
        //int m = mat.length, n = mat[0].length; // m,n 可根据 curSideLength 动态缩小；
        int curSideLength =1;

        for(int i = 0; i + curSideLength - 1 < mat.length; i++){
            for(int j = 0; j + curSideLength - 1 < mat[0].length; j ++){
                curSideLength = curMaxSideLength(mat, i, j, threshold) > curSideLength ? curMaxSideLength(mat, i, j, threshold): curSideLength;
            }
        }

        return curSideLength == 1 ? 0: curSideLength;
    }
    private int sumOfSquare(int[][] mat, int i, int j, int sideLength){
        int m = mat.length, n = mat[0].length;
        if(sideLength < 1)
            throw new IllegalArgumentException("Invalid sideLength.");
        if(i + sideLength - 1 >= m || j + sideLength - 1 >= n)
            throw new IllegalArgumentException("IndexOutOf error.");
        int sum = 0;
        for(int x = i; x <= i + sideLength - 1; x++){
            for(int y = j; y <= j + sideLength - 1; y++){
                sum += mat[x][y];
            }
        }
        return sum;
    }
    //返回以当前坐标点为左上角的满足sumOfSquare <= threshold的最大边长
    private int curMaxSideLength(int[][] mat, int i, int j, int threshold){

        int sideLength = 1, sum = mat[i][j];

        while(sum <= threshold){
            if(i + sideLength >= mat.length || j + sideLength >= mat[0].length)
                break;
            for(int x = i; x < i + sideLength; x++){
                sum += mat[x][j + sideLength];
            }
            if(sum > threshold) break;
            for(int y = j; y <= j + sideLength; y++){
                sum += mat[i + sideLength][y];
            }
            if(sum > threshold) break;
            else sideLength++;
        }

        return sideLength;
    }

    /**
     * tag: BT
     * easy-671. Second Minimum Node In a Binary Tree
     */
    //Wrong answer
    public int findSecondMinimumValue_wrong(TreeNode root) {

        int res = -1;
        LinkedList<TreeNode> list = new LinkedList<>();

        if(root.left == null) return res;
        else if(root.left.val == root.right.val){
            list.addLast(root.left);
            list.addLast(root.right);
        }else if(root.left.val > root.val){
            return root.left.val;
        }
        else
            return root.right.val;

        //TreeNode curNode = list.getFirst();
        while(!list.isEmpty()){
            if(list.getFirst().left == null){
                list.removeFirst();
                break;
            }
            else if(list.getFirst().left.val == list.getFirst().right.val){
                list.addLast(list.getFirst().left);
                list.addLast(list.getFirst().right);
            }
            else if(list.getFirst().left.val > root.val)
                return list.getFirst().left.val;
            else
                return list.getFirst().right.val;
        }

        return res;
    }
    public int findSecondMinimumValue(TreeNode root) {

        if(root.left == null) return -1;

        int secondMinimumValue = root.val;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root.left);
        list.addLast(root.right);

        TreeNode remove;
        boolean flag = true;

        while(!list.isEmpty() && flag){
            if(list.getFirst().val > root.val){
                secondMinimumValue = list.getFirst().val;
                flag = false;
            }
            if(list.getFirst().left != null){
                list.addLast(list.getFirst().left);
                list.addLast(list.getFirst().right); 
            }
            remove = list.removeFirst();
        }

        while(!list.isEmpty()){
            if(list.getFirst().val > root.val) 
                secondMinimumValue = Math.min(list.getFirst().val, secondMinimumValue);
            if(list.getFirst().left != null){
                list.addLast(list.getFirst().left);
                list.addLast(list.getFirst().right);
            }
            remove = list.removeFirst();
        }

        if(secondMinimumValue == root.val)
            return -1;
        return secondMinimumValue;
    }

    /**
     * tag: array
     * easy-581. Shortest Unsorted Continuous Subarray
     */
    public int findUnsortedSubarray_wrong(int[] nums) {

        int front = 0, tail = nums.length - 1;

        while( tail - front > 1 ){
            if(nums[front] <= nums[front+1] && nums[front+1] <= nums[tail-1] && nums[tail-1] <= nums[tail]){
                front++;
                tail--;
            }
            else if(nums[front] <= nums[front+1] && nums[front+1] <= nums[tail] && nums[tail - 1] > nums[tail]){
                front++;
            }
            else if(nums[front] > nums[front+1] && nums[front] <= nums[tail - 1] && nums[tail - 1] <= nums[tail]){
                tail--;
            }
            else break;
        }

        return tail - front <=1 ? 0 : tail - front - 1;
    }
    public int findUnsortedSubarray(int[] nums) {

        int length = nums.length;
        int front = 0, tail = length - 1;
        int[] newArray = new int[length];

        for(int i = 0; i < length; i++){
            newArray[i] = nums[i];
        }

        Arrays.sort(newArray);

        while(front < tail){
            if(newArray[front] != nums[front] && newArray[tail] != nums[tail])
                break;
            else if(newArray[front] == nums[front] && newArray[tail] != nums[tail])
                front ++;
            else if(newArray[front] != nums[front] && newArray[tail] == nums[tail])
                tail --;
            else{
                front ++;
                tail --;
            }
        }

        return front >= tail ? 0 : tail - front + 1;
    }

    /**
     * tag: array
     * medium-11. Container With Most Water
     */
    public int maxArea(int[] height) {
        
        int res = 0;
        int left = 0, right = height.length - 1;
        res = (right - left) * Math.min(height[left], height[right]);
        
        while(left < right){
            if(height[left] <= height[right]){
                left ++;
                res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            }
            else{
                right --;
                res = Math.max(res, (right - left) * Math.min(height[left], height[right])); 
            }
        }
        
        return res;
    }

    /**
     * tag: Pick One
     * medium-593. Valid Square
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        double sl_12 = Math.pow((p2[0] - p1[0]), 2)  + Math.pow((p2[1] - p1[1]), 2);
        double sl_23 = Math.pow((p3[0] - p2[0]), 2)  + Math.pow((p3[1] - p2[1]), 2);
        double sl_34 = Math.pow((p4[0] - p3[0]), 2)  + Math.pow((p4[1] - p3[1]), 2);
        double sl_41 = Math.pow((p4[0] - p1[0]), 2)  + Math.pow((p4[1] - p1[1]), 2);
        double sl_24 = Math.pow((p2[0] - p4[0]), 2)  + Math.pow((p2[1] - p4[1]), 2);
        double sl_13 = Math.pow((p3[0] - p1[0]), 2)  + Math.pow((p3[1] - p1[1]), 2);
        System.out.println(sl_12 +  ", " + sl_23 + ", " + sl_34 + ", " + sl_41 + ", " + sl_24 + ", " + sl_13);

        if(sl_12 == 0 || sl_23 == 0 || sl_34 == 0 || sl_41 == 0)
            return false;

        if(sl_12 == sl_23 && sl_23 == sl_34 && sl_34 == sl_41){
            if((p2[0] - p1[0]) * (p3[0] - p2[0]) + (p2[1] - p1[1]) * (p3[1] - p2[1]) == 0)
                return true;
        }

        if(sl_12 == sl_34 && sl_23 == sl_41){
            if(sl_12 == 2 * sl_23 || sl_23 == 2 * sl_12){
                if(sl_24 == sl_13)
                    return true;
            }
        }

        return false;

    }

    /**
     * tag: Pick One
     * easy-349. Intersection of Two Arrays
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        if(nums1 == null || nums2 == null) return null;

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int index1 = 0, index2 = 0;
        int[] temp = new int[nums1.length];
        int count = 0;

        while(index1 < nums1.length && index2 < nums2.length){
            if(nums1[index1] < nums2[index2]) 
                index1 ++;
            else if(nums1[index1] > nums2[index2])
                index2 ++;
            else{
                temp[count] = nums1[index1];
                count ++;
                index1 ++;
                index2 ++;
            }
        }

        if(count == 0) return null;
        int same = 0;

        for(int i = 0; i < count - 1; i ++){
            if(temp[i] == temp[i + 1]) 
                same ++;
        }

        int resLength = count - same;
        int[] res = new int[resLength];

        for(int i = 0; i < count; i++){
            for(int j = 0; j < resLength; j++){
                while(temp[i] == temp[i + 1]){
                    i ++;
                } 
                res[j] = temp[i];
            }
        }

        return res;
    }

    /**
     * tag: Pick One
     * medium-861. Score After Flipping Matrix
     * idea of my approach: 先依次处理每一行，如果行首元素为0，则“move”该行；再处理依次每一列，该列“0”的个数大于
     * “1”的个数，则“move“该列(事实上并没有真的执行“move”)；进行列处理的同时把该列对结果的贡献加进去。
     */
    public int matrixScore(int[][] A) {
        int res = 0;

        for(int[] a_row : A){
            if(a_row[0] == 0){
                for(int i = 0; i < a_row.length; i ++){
                    switch_a_point(a_row, i);
                }
            }
        }

        for(int j = 0; j < A[0].length; j++){
            int countOf1 = 0;
            for(int i = 0; i < A.length; i++){
                if(A[i][j] == 1) countOf1 ++;
            }
            if(countOf1 > A.length / 2){
                res = res * 2 + countOf1;
                System.out.println(res);
            }
            else{
                res = res * 2 + A.length - countOf1;
                System.out.println(res);
            }
        }

        return res;
    }
    private void switch_a_point(int[] a_row, int i){
        if(a_row[i] == 0)
            a_row[i] = 1;
        else
            a_row[i] = 0;
    }

    /**
     * tag: Pick One
     * medium-991. Broken Calculator
     * analysis: 一旦 X 大于了 Y 就要减 X — Y 次
     */
    /*  失败的尝试
    public int brokenCalc(int X, int Y) {
        if(X >= Y)
            return X - Y;
        
    }  */

    /**
     * tag: Pick One
     * easy-392. Is Subsequence
     */
    public boolean isSubsequence(String s, String t) {

        int sl = s.length();
        int tl = t.length();

        if(sl > tl)  return false;

        int sp = 0, tp = 0;
        while(tp < tl && sp < sl){
            if(t.charAt(tp) == s.charAt(sp)){
                tp ++;
                sp ++;
            }
            else tp ++;
        }

        return sp == sl;
    }
}