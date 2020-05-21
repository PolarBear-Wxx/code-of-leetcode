package wxx.leetcode.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javax.lang.model.util.ElementScanner6;

import jdk.nashorn.internal.ir.ReturnNode;
import wxx.leetcode.code.Solution2.NumArray.segmentTreeNode;

public class Solution2 {


    /**
     * 2020-4-14
     * solution1 太长了  在搞一个
     */

    /**
     * easy-985. Sum of Even Numbers After Queries
     * @param A
     * @param queries
     * @return
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        
        int l = queries.length;
        int[] answers = new int[l];
        int pre_answer = 0;

        for(int i : A){
            if(i % 2 == 0)
                pre_answer += i;
        }

        for(int i = 0; i < l; i++){
            if(A[queries[i][1]] % 2 == 0){
                pre_answer = pre_answer - A[queries[i][1]];
            }
            A[queries[i][1]] += queries[i][0];
            if(A[queries[i][1]] % 2 == 0){
                pre_answer = pre_answer + A[queries[i][1]];
            }
            answers[i] = pre_answer;
        }

        return answers;
    }

    /**
     * medium-80. Remove Duplicates from Sorted Array II
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        
        int numberOfremove = 0;
        int existed = 1;
        
        for(int i = 1; i < nums.length; i ++){
            if(nums[i] == nums[i - 1] && existed == 2){
                numberOfremove ++;
            }
            else if(nums[i] == nums[i - 1] && existed == 1){
                existed ++;
                nums[i - numberOfremove] = nums[i];
            }
            else{
                existed = 1;
                nums[i - numberOfremove] = nums[i];
            }
        }

        return nums.length - numberOfremove;
    }

    /**
     * medium-75. Sort Colors
     * @param nums
     */
    public void sortColors(int[] nums) {
        int zero_index = -1, two_index = nums.length;

        for(int i = 0; i < two_index; ){
            if(nums[1] == 1)
                i ++;
            else if(nums[i] == 2){
                two_index --;
                nums[i] = nums[two_index];
                nums[two_index] = 2;
            }
            else{
                zero_index ++;
                nums[i] = nums[zero_index];
                nums[zero_index] = 0;
                i ++;
            }
        }
    }

    /**
     * medium-215. Kth Largest Element in an Array
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int pivot = partition(nums, left, right);
        int res = nums.length - k;

        while(pivot != res && left < right){
            if(pivot > res){
                right = pivot - 1;                     //通过维护 left 和 right 实现问题规模的不断缩小
                pivot = partition(nums, left, right);  //值得注意的，这里 partition（） 并没有递归地调用自己
                //right = pivot - 1;                     
            }                                          
            else{
                left = pivot + 1;
                pivot = partition(nums, left, right);
                //left = pivot + 1;
            }
        } 
        return nums[res];
    }
    public void quickSort(int[] arr, int left, int right){

        if(arr.length == 0 || left >= right)
            return;
        
        int pivot = partition(arr, left, right);  //通过 partition（）方法维护的 pivot 实现问题规模的不断缩小
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    /**
     * 在 left 和 right 之间（闭区间）随机选取基准“pivot”，确定其最终位置，使得其左边的数据均小于等于它，右边的值均大于等于他；
     * 返回这个“最终位置”。
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] arr, int left, int right){

        //加 1 是为了保证能取到right: Math.random() -> [0.0, 1.0) && (int) -> 直接去掉小数部分
        int pivot = left + (int) Math.random() * (right - left + 1);  // Pivot can be "left" or "right".
        //System.out.println( "pre pivot :" + pivot);
        //System.out.println("left :" + left + ", right: " + right);
        
        while(left < right){
            /**  写复杂了而且还不对
            if(arr[left] <= arr[pivot])    // a not bad text data : 3, 7, 2, 8, 1, '5', 6, 4, 9, 0
                left ++;
            else{
                swap(arr, pivot, left);
                pivot = left;
                left ++;
            }
            */
           while(arr[left] <= arr[pivot] && left < pivot){  //这里如果是“arr[left] < arr[pivot]”当处理存在相同值的数据时，会
               left ++;                                     //导致死循环。(两个相同的值换过来换过去)
           }
           swap(arr, pivot, left);
           pivot = left;
           //left ++;                    //交换之后，left 和 right 不能动

           while(arr[right] >= arr[pivot] && right > pivot){
               right --;
           }
           swap(arr, pivot, right);
           pivot = right;
           //right --;
        }
        //System.out.println("after pivot: " + pivot);

        return pivot;
    }
    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * easy-167. Two Sum II - Input array is sorted
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {

        int[] result = new int[2];
        int i = 0;
        int left, right;
        boolean success = false;

        while(success == false){        //题目保证有且只有解，可以不考虑死循环情况
            left = i + 1;
            right = numbers.length - 1;
            while(left < right){        //利用二分查找方法（有序数组）
                if(target - numbers[i] < numbers[left] || target - numbers[i] > numbers[right])
                    break;
                if(target - numbers[i] == numbers[(left + right) / 2]){
                    result[0] = i + 1;
                    result[1] = (left + right) / 2 + 1;
                    success = true;
                    break;
                }
                else if(target - numbers[i] > numbers[(left + right) / 2])
                    left = (left + right) / 2 + 1;
                else 
                    right = (left + right) / 2 - 1;
            }
            i ++; 
        }
        
        return result;
    }
    public int[] twoSum2(int[] numbers, int target){
        int[] res = new int[2];
        int left = 0, right = numbers.length - 1;

        while(left < right){
            if(numbers[left] + numbers[right] == target){
                res[0] = left + 1;
                res[1] = right + 1;
                break;
            }
            else if(numbers[left] + numbers[right] > target)
                right --;
            else 
                left ++;
        }

        return res;
    }

    /**
     * 125. Valid Palindrome
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s.isEmpty())
            return true;
        
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        
        while(head < tail){
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if(!Character.isLetterOrDigit(cHead))
                head ++;
            else if(!Character.isLetterOrDigit(cTail))
                tail --;
            else if(Character.toLowerCase(cHead) != Character.toLowerCase(cTail))
                return false;
            else{
                head ++;
                tail --;
            }
        }

        return true;
    }
    //345
    public String reverseVowels(String s) {

        if(s == null || s.length() < 2)
            return s;
        
        int head = 0, tail = s.length() - 1;
        char[] chars = s.toCharArray();
        char temp;

        //char[] vowels = {a,e,i,o,u,A,E,I,O,U};

        while(head < tail){
            //cHead = s.charAt(head);
            //cTail = s.charAt(tail);
            if(!isAvowel(chars[head]))
                head ++;
            else if(!isAvowel(chars[tail]))
                tail --;
            else{
                temp = chars[head];
                chars[head] = chars[tail];
                chars[tail] = temp;
                head ++;
                tail --;
            }
        }

        return new String(chars);
    }
    private boolean isAvowel(char c){
        if(c=='a'|| c=='e' || c=='i' || c=='o' || c=='u')
            return true;
        else if(c=='A'|| c=='E' || c=='I' || c=='O' || c=='U')
            return true;
        else 
            return false;
    }

    //209. Minimum Size Subarray Sum
    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int sum = 0;

        if(nums.length == 0)
            return 0;
        sum += nums[i];
        while(i < nums.length && j < nums.length && i <= j){
            if(sum < s){
                j ++;
                if(j < nums.length)
                    sum += nums[j];
            }
            else{
                res = Math.min(res, j - i + 1);
                sum -= nums[i];
                i ++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    
    //3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int l = 0, r = -1;               //r初始值为-1，意在在frequency中维护第一个元素的值
        int res = 0;
        int[] frequency = new int[256];  //ASCII码的出现频率（8位版本ASCII码）

        while(l < length){
            if(r + 1 < length && frequency[s.charAt(r + 1)] == 0){
                frequency[s.charAt(++ r)] ++;
            }
            else {
                frequency[s.charAt(l ++)] --;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    //349
    public int[] intersection(int[] nums1, int[] nums2){
        Set<Integer> nums1set = new HashSet<>();  //使用set的原因：set不包含重复元素
        Set<Integer> res = new HashSet<>();

        for(int e : nums1)
            nums1set.add(e);
        for(int e : nums2){
            if(nums1set.contains(e))
                res.add(e);
        }

        int[] result = new int[res.size()];
        int i = 0;
        for(Integer e : res)
            result[i++] = e;
        return result;
    }

    //350
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> record = new HashMap<>();  //记录nums1中的元素及出现的次数
        ArrayList<Integer> result = new ArrayList<>();   

        for(int e : nums1){
            if(record.containsKey(e))
                record.put(e, record.get(e) + 1);
            else
                record.put(e, 1);
            //record.put(e, record.get(e) + 1);
        }
        for(int e : nums2){
            if(record.containsKey(e) && record.get(e) > 0){
                result.add(e);
                record.put(e, record.get(e) - 1);
            }
        }

        int[] res = new int[result.size()];
        int i = 0;

        for(int e : result)
            res[i++] = e;

        return res;
    }
    //804
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<String>();

        for(String s : words){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < s.length(); i ++){
                sb.append(codes[s.charAt(i) - 'a']);
            }
            set.add(sb.toString());
        }

        return set.size();
    }

    //242
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        for(int i = 0; i < t.length(); i ++){
            char c = t.charAt(i);
            if(!map.containsKey(c))
                return false;
            else if(map.get(c) == 1)
                map.remove(c);
            else 
                map.put(c, map.get(c) - 1);
        }

        return map.isEmpty();
    }

    //202
    public boolean isHappy(int n) {

        if(n <= 0)
            return false;
        
        Set<Integer> set = new HashSet<>();

        while(!set.contains(n)){
            set.add(n);
            n = replace(n);
        }

        return n == 1 ? true : false;
    }
    private int replace(int n){
        int res = 0;
        while(n >= 10){
            res += Math.pow(n % 10, 2);
            n = n / 10;
        }
        return res + (int)Math.pow(n, 2);
    }

    //290
    public boolean wordPattern(String pattern, String str) {

        String[] s = str.split(" ");      //这个属实好用

        if(s.length != pattern.length())
            return false;

        Map<Character, String> map = new HashMap<>();

        for(int i = 0; i < s.length; i ++){
            if(map.containsKey(pattern.charAt(i)) && !map.get(pattern.charAt(i)).equals(s[i]))
                return false;
            if(!map.containsKey(pattern.charAt(i)) && map.containsValue(s[i]))
                return false;
            if(!map.containsKey(pattern.charAt(i)) && !map.containsValue(s[i]))
                map.put(pattern.charAt(i), s[i]);
        }

        return true;
    }

    //205
    public boolean isIsomorphic(String s, String t) {
        
        int sl = s.length(), tl = t.length();

        if(sl != tl)
            return false;

        Map<Character, Character> map = new HashMap<>();

        for(int i = 0; i < sl; i ++){
            if(map.containsKey(s.charAt(i)) && !map.get(s.charAt(i)).equals(t.charAt(i)))
                return false;
            if(!map.containsKey(s.charAt(i)) && map.containsValue(t.charAt(i)))
                return false;
            if(!map.containsKey(s.charAt(i)) && !map.containsValue(t.charAt(i)))
                map.put(s.charAt(i), t.charAt(i));
        }

        return true;
    }

    //451
    public String frequencySort(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        for( char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        /**
         * 使用一个元素为List类型的数组 bucket 
         * 相同 fre 的 char 被放在了下标为 “fre“ 的 list 中
         */
        List<Character>[] bucket = new List[s.length() + 1];  //极端的情况是s中只有一种char
        for(char key : map.keySet()){
            int fre = map.get(key);
            if(bucket[fre] == null)
                bucket[fre] = new ArrayList<>();
            bucket[fre].add(key);
        }

        StringBuilder res = new StringBuilder();
        for(int i = s.length(); i > 0; i --){
            if(bucket[i] != null){
                for(char c : bucket[i]){
                    for(int count = 0; count < i; count ++)
                        res.append(c);
                }
            }
        }

        return res.toString();
    }

    //347
    public int[] topKFrequent(int[] nums, int k) {

        class Freq implements Comparable<Freq>{
            Integer fre;
            Integer key;

            public Freq(Integer fre, Integer key){
                this.fre = fre;
                this.key = key;
            }

            @Override
            public int compareTo(Freq another){
                if(this.fre > another.fre)
                    return 1;
                else if(this.fre < another.fre)
                    return -1;
                else 
                    return 0;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        Set<Integer> keysSet = map.keySet();
        
        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for(Integer key : keysSet){
            if(priorityQueue.size() < k)
                priorityQueue.add(new Freq(map.get(key), key));
            else{
                if(map.get(key) > priorityQueue.peek().fre){
                    priorityQueue.remove();
                    priorityQueue.add(new Freq(map.get(key), key));
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        while(!priorityQueue.isEmpty()){
            res.add(priorityQueue.remove().key);
        }

        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++)
            result[i] = res.get(i);

        return result;
    }
    
    //1
    public int[] twoSum(int[] nums, int target) {
        
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i ++){
            if(map.containsKey(target - nums[i])){
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
            else{
                map.put(nums[i], i);
            }
        }

        return res;
    }

    //15-3sum
    public List<List<Integer>> threeSum(int[] nums) {
        
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if(nums.length < 3)
            return res;

        for(int i = 0; i <= nums.length - 3; i ++){
            if(!set.contains(nums[i])){
                set.add(nums[i]); // ensure no duplicate triplets
                int target = 0 - nums[i];
                Map<Integer, Boolean> map_twoSum = new HashMap<>();
                for(int j = i + 1; j < nums.length; j ++){
                    if(!map_twoSum.containsKey(target - nums[j]))
                        map_twoSum.put(nums[j], true);
                    else if(map_twoSum.get(target - nums[j])){
                        List<Integer> a = new ArrayList<>();
                        a.add(nums[i]);
                        a.add(nums[j]);
                        a.add(target - nums[j]);
                        map_twoSum.put(target - nums[j], false);
                        map_twoSum.put(nums[j], false); // ensure no duplicate triplets
                        //a.sort(c);
                        Collections.sort(a);
                        boolean isDuplicated = false;
                        for(List l : res){
                            if(l.get(0) == a.get(0) && l.get(1) == a.get(1) && l.get(2) == a.get(2)){
                                isDuplicated = true;
                                break;
                            }
                        }
                        if(!isDuplicated)
                            res.add(a);
                    }
                }
            }
        }

        return res;
    }
    
    //303
    class NumArray {

        class segmentTreeNode{
            int value;
            int lIndex, rIndex;
            segmentTreeNode left, right;
        }

        private segmentTreeNode segmentTree;
        private int[] data;

        public NumArray(int[] nums) {
            data = new int[nums.length];
            for(int i = 0; i < data.length; i ++)
                data[i] = nums[i];
            segmentTree = build(0, nums.length - 1, nums);
        }
        
        public int sumRange(int i, int j) {
            if(data.length == 0)
                return 0;
            if(i < 0 || i >= data.length || j < 0 || j >= data.length || i > j)
                throw new IllegalArgumentException("Index is illegal.");
            return sumRange(segmentTree, i, j);
        }

        private int sumRange(segmentTreeNode aNode, int i, int j){
            if(i == aNode.lIndex && j == aNode.rIndex)
                return aNode.value;
            
            int mid = aNode.lIndex + (aNode.rIndex - aNode.lIndex) / 2;
            if(j <= mid)
                return sumRange(aNode.left, i, j);
            else if(i >= mid + 1)
                return sumRange(aNode.right, i, j);
            
            int leftresult = sumRange(aNode.left, i, mid);
            int rightresult = sumRange(aNode.right, mid + 1, j);
            return leftresult + rightresult;
        }

        private segmentTreeNode build(int l, int r, int[] nums){

            if(l > r)
             return null;

            segmentTreeNode newNode = new segmentTreeNode();
            newNode.lIndex = l;
            newNode.rIndex = r;

            if(l == r){
                newNode.value = nums[l];
                return newNode;
            }

            int mid = l + (r - l) / 2;
            newNode.left = build(l, mid, nums);
            newNode.right = build(mid + 1, r, nums);
            newNode.value = newNode.left.value + newNode.right.value;

            return newNode;
        }

        public void update(int i, int val){
            update(segmentTree, i, val);
        }
        private void update(segmentTreeNode aNode, int i, int val){
            if(i == aNode.lIndex && i == aNode.rIndex){
                aNode.value = val;
                return;
            }

            aNode.value = aNode.value + val - data[i];

            int mid = aNode.lIndex + (aNode.rIndex - aNode.lIndex) / 2;

            if(i <= mid)
                update(aNode.left, i, val);
            else if(i >= mid + 1)
                update(aNode.right, i, val);
        }
    }

    //208
    class Trie {

        private class Node{
            boolean isWord;
            HashMap<Character, Node> childs; 

            public Node(boolean isWord){
                this.isWord = isWord;
                childs = new HashMap<>();
            }

            public Node(){
                this(false);
            }
        }

        private Node root;
        private int size;

        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
            size = 0;
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            
            Node cur = root;

            for(int i = 0; i < word.length(); i ++){
                Character c = word.charAt(i);
                if(cur.childs.get(c) == null)
                    cur.childs.put(c, new Node());
                cur = cur.childs.get(c);
            }

            if(cur.isWord == false){
                cur.isWord = true;
                size ++;
            }
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
           
            Node cur = root;

            for(int i = 0; i < word.length(); i ++){
                Character c = word.charAt(i);
                if(cur.childs.get(c) == null)
                    return false;
                cur = cur.childs.get(c);
            } 

            return cur.isWord;
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {

            Node cur = root;

            for(int i = 0; i < prefix.length(); i ++){
                Character c = prefix.charAt(i);
                if(cur.childs.get(c) == null)
                    return false;
                cur = cur.childs.get(c);
            }
            
            return true;
        }
    }
    
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    //easy-387
    public int firstUniqChar(String s) {

        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i ++){
            freq[s.charAt(i) - 'a'] ++;
        }

        for(int i = 0; i < s.length(); i ++){
            if(freq[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }
}