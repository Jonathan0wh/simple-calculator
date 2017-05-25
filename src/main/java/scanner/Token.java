package scanner;

public class Token {
    public enum TokenType {
        LPAR, RPAR, PLUS, MINUS, MULT, DIV, INT, NONE,
    }

    public TokenType tokenType;
    public Object value;

    Token(TokenType tt, Object v) {
        this.tokenType = tt;
        this.value = v;
    }
}
