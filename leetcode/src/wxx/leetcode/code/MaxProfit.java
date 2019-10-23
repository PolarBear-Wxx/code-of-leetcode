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
    public static int mine(int[] prices){
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
    /**
     * from discuss, faster than mine
     * Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, 
     * and find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
     * @param prices
     * @return
     */
    public static int others(int[] prices){
        int maxCur = 0, maxSofar = 0;
        for(int i = 1; i < prices.length; i++){
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSofar = Math.max(maxCur, maxSofar);
        }
        return maxSofar;
    }
    public static void main(String[] args) {
        int[] test = {1,2,3,6,2};
        System.out.println("input: " + test);
        System.out.println("output: " + MaxProfit.mine(test));
        System.out.println("output: " + MaxProfit.others(test));
    }
}