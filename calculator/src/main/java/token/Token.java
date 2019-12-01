package token;

import visitor.TokenVititor;

public interface Token {
    void accept(TokenVititor visitor);
}
