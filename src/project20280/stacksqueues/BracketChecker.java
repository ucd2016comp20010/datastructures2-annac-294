package project20280.stacksqueues;

import java.util.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        // TODO
    	
    	Stack<Character> stack = new Stack<>();
    	
    	for(char c : input.toCharArray()) {
    		if(c == '(' || c == '{' || c == '[') {
    			stack.push(c);
    		}
    		else if(c == ')' || c == '}' || c == ']') {
    			if(stack.isEmpty()) {
    				System.out.println("Not Balanced");
    				return;
    			}
    			
    			char top = stack.peek();
    			if((c == ')' && top != '(') || 
    					(c == '}' && top != '{') || 
    					(c == ']' && top != '[')) {
    				System.out.println("Not Balanced");
    				return;
    			}
    			
    			stack.pop();
    		}
    	}
    	
    	if(stack.isEmpty()) {
    		System.out.println("Balanced");
    	}
    	else {
    		System.out.println("Not Balanced");
    	}
    
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}