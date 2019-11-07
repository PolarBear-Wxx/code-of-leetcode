package wxx.leetcode.code;

//import java.util.ArrayList;
import java.util.Stack;

/**
 * easy-155 
 */

class MinStack {

    /** initialize your data structure here. */
    /*
    ArrayList<Integer> array = new ArrayList<Integer>();
    ArrayList<Integer> min_array = new ArrayList<Integer>();
    int min = Integer.MAX_VALUE;
    public MinStack() {
        
    }
    
    public void push(int x) {
      if(x <= min){
          min = x;
          min_array.add(x);
      }
      array.add(x);
    }
    
    public void pop() {
        if(array.get(array.size() - 1 ) == min){
            min_array.remove(min_array.size() - 1);
            array.remove(array.size() - 1);
        }
        else array.remove(array.size() - 1);
    }
    
    public int top() {
        int index = array.size() - 1;
        return array.get(index);
    }
    
    public int getMin() {
        return min_array.get(min_array.size() - 1);
    }
    */
    /*
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }*/
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        //Stack<Integer> test = new Stack<>();
        //System.out.println(test);
        minStack.push(1);
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.push(1);
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.push(3);
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.push(3);
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.push(1);
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.pop();
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.pop();
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.pop();
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.pop();
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
        minStack.pop();
        System.out.println(minStack.stack + "  " + minStack.min + "  " + minStack.min_stack);
    }
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> min_stack = new Stack<Integer>();
    public MinStack() {
        
    }
    
    public void push(int x) {
        if(x <= min){
            min_stack.push(x);
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if(stack.peek() == min){
            min_stack.pop();
            if(!min_stack.isEmpty()) min = min_stack.peek();
            else min = Integer.MAX_VALUE;
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
        //return min_stack.peek();
    }
}