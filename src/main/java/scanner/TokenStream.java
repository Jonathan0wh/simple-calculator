package scanner;

import java.io.IOException;

public interface TokenStream {
	Token getToken()  throws IOException;
    void consumeToken();
	void close() throws IOException;
}
