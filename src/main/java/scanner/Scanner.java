/*
 * Created by Jonathan Hu on 24/05/17 9:41 AM
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 23/05/17 5:46 PM
 *
 * This scanner return string value token
 *
 */

package scanner;

import java.io.IOException;
import java.io.InputStream;

public class Scanner {

    private TokenStream ts;
    private Token currentToken;

    /* scanner */
    public Scanner(InputStream in) {
        ts = new TokenStreamReader(in);
    }

    public Token getToken() throws IOException {

        // debug
        // System.out.print("{" + currentToken.tokenType.toString() + ", " + "\"" + currentToken.value + "\"} ");

        return ts.getToken();
    }

}
