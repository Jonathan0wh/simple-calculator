/*
 * Created by Jonathan Hu on 24/05/17 9:40 AM
 * Copyright (c) 2017. All rights reserved.
 *
 * Last modified 24/05/17 9:39 AM
 *
 * Return string value token
 *
 */

package scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TokenStreamReader implements TokenStream {
	
	private BufferedReader br;
	private int columnNum;

	public TokenStreamReader(InputStream in) {
		br = new BufferedReader(new InputStreamReader(in));
	}

	@Override
	public Token getToken() throws IOException {

		int v = br.read();
		columnNum++;

		switch (v) {
			case '(':
				return new Token(Token.TokenType.LPAR, Character.toString((char) v));
			case ')':
				return new Token(Token.TokenType.RPAR, Character.toString((char) v));
			case '+':
				return new Token(Token.TokenType.PLUS, Character.toString((char) v));
			case '-':
				return new Token(Token.TokenType.MINUS, Character.toString((char) v));
			case '*':
				return new Token(Token.TokenType.MULT, Character.toString((char) v));
			case '/':
				return new Token(Token.TokenType.DIV, Character.toString((char) v));
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
				ArrayList<Character> charList = new ArrayList<>();
				charList.add((char) v);
				while (true) {
					br.mark(1);
					int charNum = br.read();
					if (Character.isDigit(charNum))
						charList.add((char) charNum);
					else {
						br.reset();
						StringBuilder builder = new StringBuilder(charList.size());
						for (Character c: charList)
							builder.append(c);
						return new Token(Token.TokenType.INT, builder.toString());
					}
				}
			// the current space already read, get next directly
			case ' ':
				return getToken();
			// return eof, CR or LF directly
			case '\n':
			case '\r':
			case -1:
			case 26: // EOF
				return new Token(Token.TokenType.NONE, "EOF");
			default:
				throw new IOException("illegal character " + v + " at column " + columnNum);
		}
	}

	@Override
	public void consumeToken() {
		try {
			br.skip(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		br.close();
	}

}
