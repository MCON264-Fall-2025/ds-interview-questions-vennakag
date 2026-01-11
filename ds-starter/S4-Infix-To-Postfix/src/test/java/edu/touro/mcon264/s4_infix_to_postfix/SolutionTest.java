package edu.touro.mcon264.s4_infix_to_postfix;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void testInfix_to_postfix_SingleDigit_Expression() {
        // TODO: Add tests for Infix → Postfix + Evaluate
        Solution s = new Solution("5+9/3");
        String postfix = s.infix_to_postfix();
        assertEquals("5 9 3 / +", postfix);
        assertEquals(8, s.evaluate_postfix(postfix));
        }
    @Test
    void testInfix_to_postfix_MultiDigit_Expression(){
        Solution s = new Solution("12+5*6");
        String postfix = s.infix_to_postfix();
        assertEquals("12 5 6 * +", postfix);
        assertEquals(42, s.evaluate_postfix(postfix));
        }
    @Test
    void test_Invalid_Expression(){
        Solution s = new Solution("4/0");
        String postfix = s.infix_to_postfix();
        assertThrows(IllegalArgumentException.class, () -> {
            s.evaluate_postfix("4 0 /");
        });
    }
}