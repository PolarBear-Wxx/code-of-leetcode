package wxx.leetcode.code;

import java.util.Arrays;

/**
 * easy-122 
 * @version 1.01 2019-10-23
 * @author Khada Jhin
 */
public class Easy122{
    /* 太麻烦了
    private static int[] test1 = {7,1,5,3,6,4};
    excepted output: 7
    private static int[] test2 = {1,2,3,4,5};
    excepted output: 4
    */
    public static int mine(int[] prices){
        System.out.println("I have no idea!");
        return 0;
    }
    /**
     * copy from discuss
     * the author said: the question is a joke!
     * His solution can solve this problem, but I don't know why!
     * @param prices
     * @return
     */
    public static int others(int[] prices){
        int total = 0;
        for(int i = 0; i < prices.length - 1; i++)
        {
            if(prices[i+1] - prices[i] > 0)
                total += prices[i+1] - prices[i];
        }
        return total;
    }
    public static void main(String[] args) {
        int[] test = {1,2,3,4,5};
        System.out.println("intput: " + Arrays.toString(test));
        System.out.println("output: " + Easy122.others(test));
        /* 太麻烦了
        System.out.println("input: " + test1);
        System.out.println("output:" + Easy122.mine(test1));
        System.out.println("input: " + test2);
        System.out.println("output:" + Easy122.mine(test2));
        */
    }
}