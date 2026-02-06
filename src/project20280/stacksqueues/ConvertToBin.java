package project20280.stacksqueues;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;
public class ConvertToBin {

	
	static String convertToBinary(long dec_num) {
		
		if(dec_num == 0) {
			return "0";
		}
		
		Stack<Long> stack = new Stack<>();
		
		
		while(dec_num > 0) {
			stack.push(dec_num % 2);
			dec_num = dec_num / 2;
		}
		
		StringBuilder bin = new StringBuilder();
		while(!stack.isEmpty()) {
			bin.append(stack.pop());
		}
		
		return bin.toString();
		
	}
	
	@Test
	void testConvertToBinary() {
		assertEquals("10111", convertToBinary(23));
		assertEquals("111001000000101011000010011101010110110001100010000000000000", convertToBinary(1027010000000000000L));
	}
	

}
