package wxx.leetcode.code;
/**
 * easy-121
 * @version 1.01 2019-10-23
 * @author Khada Jhin
 */
public class MaxProfit{
    /**
     * Two-layer loop
     * accept but Runtime is too long
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices){
        int mp = 0;
        for(int i = 0; i < prices.length; i++){
            for(int j = 0; j < i; j++){
                if(prices[i] - prices[j] > mp)
                {
                    mp = prices[i] - prices[j];
                }
            }
        }
        return mp;
    }
    public static void main(String[] args) {
        int[] test = {1,2,3,6,2};
        System.out.println("input: " + test);
        System.out.println("output: " + MaxProfit.maxProfit(test));
    }
}