package com.apex.chat.core.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Token capacity and context window boundaries of an LLM.
 */
public class ContextWindowSpecification implements Serializable {

    private int maxTotalTokens;
    private int maxOutputTokens;
    private int reservedSystemTokens;
    private String tokenizerName;

    public ContextWindowSpecification() {
        this.maxTotalTokens = 1048576; // 1M default for modern Gemini
        this.maxOutputTokens = 8192;
        this.reservedSystemTokens = 2048;
        this.tokenizerName = "cl100k_base";
    }

    public ContextWindowSpecification(int maxTotalTokens, int maxOutputTokens) {
        this();
        this.maxTotalTokens = maxTotalTokens;
        this.maxOutputTokens = maxOutputTokens;
    }

    public int getMaxTotalTokens() {
        return maxTotalTokens;
    }

    public ContextWindowSpecification setMaxTotalTokens(int maxTotalTokens) {
        this.maxTotalTokens = maxTotalTokens;
        return this;
    }

    public int getMaxOutputTokens() {
        return maxOutputTokens;
    }

    public ContextWindowSpecification setMaxOutputTokens(int maxOutputTokens) {
        this.maxOutputTokens = maxOutputTokens;
        return this;
    }

    public int getReservedSystemTokens() {
        return reservedSystemTokens;
    }

    public ContextWindowSpecification setReservedSystemTokens(int reservedSystemTokens) {
        this.reservedSystemTokens = reservedSystemTokens;
        return this;
    }

    public String getTokenizerName() {
        return tokenizerName;
    }

    public ContextWindowSpecification setTokenizerName(String tokenizerName) {
        this.tokenizerName = tokenizerName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContextWindowSpecification that)) return false;
        return maxTotalTokens == that.maxTotalTokens &&
                maxOutputTokens == that.maxOutputTokens &&
                reservedSystemTokens == that.reservedSystemTokens &&
                Objects.equals(tokenizerName, that.tokenizerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxTotalTokens, maxOutputTokens, reservedSystemTokens, tokenizerName);
    }

    @Override
    public String toString() {
        return "ContextWindowSpecification{" +
                "maxTotalTokens=" + maxTotalTokens +
                ", maxOutputTokens=" + maxOutputTokens +
                ", reservedSystemTokens=" + reservedSystemTokens +
                '}';
    }
}
