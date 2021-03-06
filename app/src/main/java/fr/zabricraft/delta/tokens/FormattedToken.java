package fr.zabricraft.delta.tokens;

import java.util.Map;

import fr.zabricraft.delta.utils.ComputeMode;
import fr.zabricraft.delta.utils.Operation;

public class FormattedToken extends Token {

    private Token token;

    public FormattedToken(Token token) {
        this.token = token;
    }

    public String toString() {
        return token.toString();
    }

    public Token compute(Map<String, Token> inputs, ComputeMode mode) {
        return token.compute(inputs, ComputeMode.formatted);
    }

    public Token apply(Operation operation, Token right, Map<String, Token> inputs, ComputeMode mode) {
        return token.apply(operation, right, inputs, ComputeMode.formatted);
    }

    public boolean needBrackets(Operation operation) {
        return token.needBrackets(operation);
    }

    public int getMultiplicationPriority() {
        return token.getMultiplicationPriority();
    }

    public Token opposite() {
        return token.opposite();
    }

    public Token inverse() {
        return token.inverse();
    }

    public boolean equals(Token right) {
        return token.defaultEquals(right);
    }

    public Double asDouble() {
        return token.asDouble();
    }

}
