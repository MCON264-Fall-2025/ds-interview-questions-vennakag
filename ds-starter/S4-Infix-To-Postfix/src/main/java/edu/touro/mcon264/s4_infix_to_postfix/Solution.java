package edu.touro.mcon264.s4_infix_to_postfix;

import java.util.Stack;

public class Solution {
    // TODO: Implement solution for: Infix → Postfix + Evaluate
    private StringBuilder token = new StringBuilder();
    Stack<Character> operatorStack = new Stack<>();
    private String expression;

    public Solution(String expression){
        this.expression = expression;
    }

    
    public int operand(char expChar){
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
        if(expression==null){
            throw new IllegalStateException("Expression is missing");
        }else{
            char[] expressionCharacters = expression.toCharArray();
            for(int i=0; i<expressionCharacters.length;i++) {
                if (Character.isWhitespace(expressionCharacters[i])) continue;
                if(Character.isDigit(expressionCharacters[i])){
                    token.append(expressionCharacters[i]);
                }else if (expressionCharacters[i] == '+' || expressionCharacters[i] == '-'
                        || expressionCharacters[i] == '/' || expressionCharacters[i] == '*') {
                    if(!operatorStack.isEmpty()&&operand(operatorStack.peek()) >= operand(expressionCharacters[i])){
                        token.append(operatorStack.pop());
                    }
                    operatorStack.push(expressionCharacters[i]);
                    }
            }
            while (!operatorStack.isEmpty()) {
                token.append(operatorStack.pop());
            }
        }
        return token.toString();
    }

    public int evaluate_postfix(String expression){
        char[] tokens = expression.toCharArray();
        Stack<Integer> s = new Stack<>();
        if(tokens.length==0){
            throw new IllegalStateException("Expression is missing");
        }else{
            for(int i = 0; i < tokens.length;i++){
                if (Character.isDigit(tokens[i])){
                    s.push(tokens[i] - '0');
                }else{
                    if(tokens[i]=='+'){
                      s.push(s.pop()+s.pop()) ;
                    }else if(tokens[i]=='-'){
                        int b = s.pop();
                        int a = s.pop();
                        s.push(a-b) ;
                    }else if(tokens[i]=='/'){
                        int b = s.pop();
                        int a = s.pop();
                        s.push(a/b) ;
                    }else if(tokens[i]=='*'){
                        s.push(s.pop()*s.pop()) ;
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