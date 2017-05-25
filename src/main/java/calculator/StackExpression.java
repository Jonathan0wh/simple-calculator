package calculator;

import scanner.Scanner;
import scanner.Token;
import stack.Stack;

import java.util.HashMap;

/**
 * Created by jhu on 23/5/17.
 * <p>
 * Stack-based simple calculator
 */
public class StackExpression {

    /* define operand priority */
    private static final HashMap operandPriority = new HashMap<Token.TokenType, Integer>() {
        {
                    put(Token.TokenType.PLUS, 11);
                    put(Token.TokenType.MINUS, 11);
                    put(Token.TokenType.MULT, 12);
                    put(Token.TokenType.DIV, 12);
                    put(Token.TokenType.LPAR, 99);
                    put(Token.TokenType.RPAR, -99);
        }
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner((System.in));

        Stack<Integer> numbers = new Stack<>(100);
        Stack<Token> operators = new Stack<>(100);

        Token currentToken;
        try {
            while ((currentToken = scanner.getToken()).tokenType != Token.TokenType.NONE) {
                // if current token is a number
                if (currentToken.tokenType == Token.TokenType.INT)
                    numbers.push(Integer.parseInt(currentToken.value.toString()));
                    // if current token is an operand
                else {
                    // if left operand is '+' or '-', and current one is '*' or '-', the previous '+' '-' must be paused
                    // if current operand is '(', the left one also must be paused
                    if (operators.isEmpty() || preOrder(operators.getTop().tokenType, currentToken.tokenType) < 0)
                        operators.push(currentToken);
                        // if left operand is '*' or '/', or both side '+' or '-', previous '*' '/' could be calculated
                        // if current operand is ')', calculate and pop '('
                    else {
                        if (currentToken.tokenType == Token.TokenType.RPAR) {
                            while (operators.getTop().tokenType != Token.TokenType.LPAR)
                                binaryCalc(numbers, operators);
                            if (operators.pop().tokenType != Token.TokenType.LPAR)
                                throw new Exception("left and right parentheses not match");
                        } else {
                            // if the previous operand is '(', do not calculate
                            if (operators.getTop().tokenType != Token.TokenType.LPAR)
                                binaryCalc(numbers, operators);
                            operators.push(currentToken);
                        }
                    }
                }
            }

            // finally calculate by the last two numbers
            while (!operators.isEmpty())
                binaryCalc(numbers, operators);

            if (operators.top != 0)
                throw new Exception("left and right parentheses not match");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The result is " + numbers.getTop());
    }

    private static void binaryCalc(Stack<Integer> numbers, Stack<Token> operators) {
        int a = numbers.pop();
        int b = numbers.pop();

        Token oprt = operators.pop();
        int d = 0;
        if (oprt.tokenType == Token.TokenType.PLUS)
            d = b + a;
        else if (oprt.tokenType == Token.TokenType.MULT)
            d = a * b;
        else if (oprt.tokenType == Token.TokenType.MINUS)
            d = b - a;
        else if (oprt.tokenType == Token.TokenType.DIV)
            d = b / a;

        numbers.push(d);
    }

    private static int preOrder(Token.TokenType left, Token.TokenType right) {

        return (int) operandPriority.get(left) - (int) operandPriority.get(right);
    }
}
