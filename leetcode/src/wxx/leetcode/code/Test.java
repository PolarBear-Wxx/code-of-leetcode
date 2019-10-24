package wxx.leetcode.code;

/**
 * made for various test
 * @version 1.01 2019-10-24   No question I am not happy now.
 * @author Khada Jhin
 */

class Test{
    public static void test1(String s){
        s = "现在我很不开心";
    }
    public static void test1(int a){
        a = 666;
    }
    public static void test1(BTNode anode){
        anode.val = 666;
    }
    public static void main(String[] args) {
        String stringTest1 = "I am not happy now.";
        int intTest1 = 999;
        BTNode nodeTest1 = new BTNode(999);
        test1(intTest1);
        test1(stringTest1);
        test1(nodeTest1);
        System.out.println(stringTest1);
        System.out.println(intTest1);
        System.out.println(nodeTest1.val);
    }
}