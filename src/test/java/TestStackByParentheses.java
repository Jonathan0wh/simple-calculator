import org.junit.BeforeClass;
import org.junit.Test;
import stack.Stack;

import java.io.IOException;

/**
 * Created by jhu on 23/5/17.
 *
 * test stack by parentheses
 *
 */
public class TestStackByParentheses {

    @BeforeClass
    public static void setUp() {
        String inputData = "(())[]{}";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
    }

    @Test
    public void test() throws IOException {
        byte[] buf = new byte[128];
        int length = System.in.read(buf);
        boolean moreRightParError = false;

        Stack<Byte> s = new Stack<>(128);

        for (int i = 0; i < length; i++) {
            switch (buf[i]) {
                case '(':
                case '[':
                case '{':
                    s.push(buf[i]);
                    break;
                case ')':
                    if (s.isEmpty()) {
                        moreRightParError = true;
                        System.out.println("more right parenthesis");
                    } else {
                        if (s.getTop() == '(')
                            s.pop();
                        else
                            System.out.println("unmatched " + (char) s.getTop().byteValue() + " detected");
                    }
                    break;
                case ']':
                    if (s.isEmpty()) {
                        moreRightParError = true;
                        System.out.println("more right parenthesis");
                    } else {
                        if (s.getTop() == '[')
                            s.pop();
                        else
                            System.out.println("unmatched " + (char) s.getTop().byteValue() + " detected");
                    }
                    break;
                case '}':
                    if (s.isEmpty()) {
                        moreRightParError = true;
                        System.out.println("more right parenthesis");
                    } else {
                        if (s.getTop() == '{')
                            s.pop();
                        else
                            System.out.println("unmatched " + (char) s.getTop().byteValue() + " detected");
                    }
                    break;
                default:
                    break;
            }
        }

        if (s.isEmpty() && !moreRightParError)
            System.out.println("all match");
        else if (!moreRightParError) {
            // ( [ or { left in stack
            System.out.println("more left parenthesis");
        }
    }
}
