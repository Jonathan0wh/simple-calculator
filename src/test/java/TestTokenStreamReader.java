import org.junit.BeforeClass;
import org.junit.Test;
import scanner.Token;
import scanner.TokenStream;
import scanner.TokenStreamReader;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jhu on 23/5/17.
 *
 * test stream reader
 */
public class TestTokenStreamReader {

    @BeforeClass
    public static void setUp() {
        String inputData = "( 5 + 8 ) * 13";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
    }

    @Test
    public void test() {
        TokenStream ts = new TokenStreamReader(System.in);
        try {
            Token token;
            while ((token = ts.getToken()).tokenType != Token.TokenType.NONE) {

                // debug
                System.out.print("{" + token.tokenType.toString() + ", " + "\"" + token.value + "\"} ");
                assertNotNull("Token not null", token);

                //ts.consumeToken();
            }
            ts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
