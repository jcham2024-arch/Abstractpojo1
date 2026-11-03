package com.apex.chat.core.thinking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Explicit intent extracted from model thinking before an action or tool is invoked.
 */
public class IntentTag implements Serializable {

    private IntentType intentType;
    private String targetEntity;
    private String intentRationale;
    private Map<String, String> extractedParameters;

    public IntentTag() {
        this.intentType = IntentType.DIRECT_RESPONSE;
        this.extractedParameters = new HashMap<>();
    }

    public IntentTag(IntentType intentType, String targetEntity, String intentRationale) {
        this.intentType = intentType;
        this.targetEntity = targetEntity;
        this.intentRationale = intentRationale;
        this.extractedParameters = new HashMap<>();
    }

    public IntentType getIntentType() {
        return intentType;
    }

    public IntentTag setIntentType(IntentType intentType) {
        this.intentType = intentType;
        return this;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public IntentTag setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
        return this;
    }

    public String getIntentRationale() {
        return intentRationale;
    }

    public IntentTag setIntentRationale(String intentRationale) {
        this.intentRationale = intentRationale;
        return this;
    }

    public Map<String, String> getExtractedParameters() {
        return extractedParameters;
    }

    public IntentTag setExtractedParameters(Map<String, String> extractedParameters) {
        this.extractedParameters = extractedParameters != null ? extractedParameters : new HashMap<>();
        return this;
    }

    public IntentTag addParameter(String key, String value) {
        if (this.extractedParameters == null) {
            this.extractedParameters = new HashMap<>();
        }
        this.extractedParameters.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntentTag intentTag)) return false;
        return intentType == intentTag.intentType &&
                Objects.equals(targetEntity, intentTag.targetEntity) &&
                Objects.equals(intentRationale, intentTag.intentRationale) &&
                Objects.equals(extractedParameters, intentTag.extractedParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intentType, targetEntity, intentRationale, extractedParameters);
    }

    @Override
    public String toString() {
        return "IntentTag{" +
                "intentType=" + intentType +
                ", targetEntity='" + targetEntity + '\'' +
                ", intentRationale='" + intentRationale + '\'' +
                '}';
    }
}
