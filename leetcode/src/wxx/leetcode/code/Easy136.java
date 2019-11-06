package wxx.leetcode.code;

import java.util.Vector;
import java.util.ArrayList;

public class Easy136{
    public static int mine(int[] nums){
        //Vector<Integer> result = new Vector<>()
        ArrayList<Integer> tem = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(tem.contains(nums[i])){
                int index = tem.indexOf(nums[i]);
                tem.remove(index);}
            else
                tem.add(nums[i]);
        }
        return tem.get(0);
    }

    public static void main(String[] args) {
        int[] t ={1,1,1};
        mine(t);
        System.out.println("faster than : 5.02%");
        System.out.println("memory usage less than : 78.52%");
    }
}