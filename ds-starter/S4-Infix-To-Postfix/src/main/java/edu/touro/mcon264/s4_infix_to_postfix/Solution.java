package edu.touro.mcon264.s4_infix_to_postfix;

import java.util.Stack;

public class Solution {
    // TODO: Implement solution for: Infix → Postfix + Evaluate
    private String expression;

    public Solution(String expression){
        this.expression = expression;
    }

    
    public int operandPrecedence(char expChar){
        switch (expChar) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public String infix_to_postfix(){
        StringBuilder token = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        if(expression==null){
            throw new IllegalStateException("Expression is missing");
        }else{
            char[] expressionCharacters = expression.toCharArray();
            for(int i=0; i<expressionCharacters.length;i++) {
                if (Character.isWhitespace(expressionCharacters[i])) continue;
                if(Character.isDigit(expressionCharacters[i])){
                    while (i < expressionCharacters.length && Character.isDigit(expressionCharacters[i])) {
                        token.append(expressionCharacters[i]);
                        i++;
                    }
                    token.append(' ');
                    i--;
                }else if (expressionCharacters[i] == '+' || expressionCharacters[i] == '-'
                        || expressionCharacters[i] == '/' || expressionCharacters[i] == '*') {
                    if(!operatorStack.isEmpty()&& operandPrecedence(operatorStack.peek()) >= operandPrecedence(expressionCharacters[i])){
                        token.append(operatorStack.pop()).append(' ');
                    }
                    operatorStack.push(expressionCharacters[i]);
                    }
            }
            while (!operatorStack.isEmpty()) {
                token.append(operatorStack.pop()).append(' ');
            }
        }
        return token.toString().trim();
    }

    public int evaluate_postfix(String expression){
        String[] tokens = expression.trim().split("\\s+");
        Stack<Integer> s = new Stack<>();
        if(tokens.length==0){
            throw new IllegalStateException("Expression is missing");
        }else{
            for(int i = 0; i < tokens.length;i++){
                String token = tokens[i];
                if (token.matches("\\d+")){
                    s.push(Integer.parseInt(token));
                }else{
                    if(token.equals("+")){
                        if (s.size() < 2) {
                            throw new IllegalArgumentException("Malformed postfix expression: not enough operands for '+'");
                        }
                        s.push(s.pop() + s.pop());
                    }else if(token.equals("-")){
                        if (s.size() < 2) {
                            throw new IllegalArgumentException("Malformed postfix expression: not enough operands for '-'");
                        }
                        int b = s.pop();
                        int a = s.pop();
                        s.push(a-b) ;
                    }else if(token.equals("*")){
                        if (s.size() < 2) {
                            throw new IllegalArgumentException("Malformed postfix expression: not enough operands for '/'");
                        }
                        int b = s.pop();
                        int a = s.pop();
                        s.push(a*b) ;
                    }else if(token.equals("/")){
                        if (s.size() < 2) {
                            throw new IllegalArgumentException("Malformed postfix expression: not enough operands for '*'");
                        }
                        int b = s.pop();
                        int a = s.pop();
                        if (b == 0) {
                            throw new IllegalArgumentException("Division by zero is not allowed.");
                        }
                        s.push(a/b) ;
                    }
                }
            }
        }
        return s.pop();
    }


    public static void main(String[] args) {
        Solution s = new Solution("3+9+5");
        System.out.println(s.infix_to_postfix());
        Solution s2 = new Solution("4+12");
        System.out.println(s2.infix_to_postfix());
    }
}