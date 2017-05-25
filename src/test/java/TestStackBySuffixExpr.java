import org.junit.BeforeClass;
import org.junit.Test;
import stack.Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by jhu on 23/5/17.
 *
 * Test Stack by suffix expression
 */
public class TestStackBySuffixExpr {

    @BeforeClass
    public static void setUp() {
        String inputData = "8 5 - 4 *";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
    }

    @Test
    public void test() {
        Stack<Integer> numStack = new Stack(20);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int charNum;
            int num1;
            int num2;
            while ((charNum = br.read()) != -1) {
                switch (charNum) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        numStack.push(Character.getNumericValue(charNum));
                        break;
                    case '+':
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        numStack.push(num1 + num2);
                        break;
                    case '-':
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        numStack.push(num2 - num1);
                        break;
                    case '*':
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        numStack.push(num1 * num2);
                        break;
                    case '/':
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        numStack.push(num2 / num1);
                        break;
                    case ' ':
                        break;
                    default:
                        throw new Exception("illegal character " + (char) charNum);
                }
            }

            int result = numStack.pop();
            if (numStack.isEmpty())
                System.out.println("The result is " + result);
            else
                throw new Exception("illegal expression");

            assertEquals("calculate", result, 12);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
