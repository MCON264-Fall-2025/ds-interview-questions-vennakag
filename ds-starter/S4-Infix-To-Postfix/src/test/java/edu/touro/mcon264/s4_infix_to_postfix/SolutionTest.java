package edu.touro.mcon264.s4_infix_to_postfix;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SolutionTest {
    @Test
    void testInfix_to_postfix() {
        // TODO: Add tests for Infix → Postfix + Evaluate
        Solution s = new Solution("3+9+5");
        String postfix = s.infix_to_postfix();
        assertEquals("39+5+", postfix);
        assertEquals(17, s.evaluate_postfix(postfix));
        }
}