package com.apex.chat.core.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

/**
 * Provider, family, and identification details of an LLM.
 */
public class ModelSpecification implements Serializable {

    private String providerId;
    private String modelFamily;
    private String modelName;
    private String modelVersion;
    private URI apiEndpoint;

    public ModelSpecification() {
        this.providerId = "google";
        this.modelFamily = "gemini";
        this.modelName = "gemini-2.5-flash";
        this.modelVersion = "latest";
    }

    public ModelSpecification(String providerId, String modelName) {
        this();
        this.providerId = providerId;
        this.modelName = modelName;
    }

    public String getProviderId() {
        return providerId;
    }

    public ModelSpecification setProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public String getModelFamily() {
        return modelFamily;
    }

    public ModelSpecification setModelFamily(String modelFamily) {
        this.modelFamily = modelFamily;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public ModelSpecification setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public ModelSpecification setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
        return this;
    }

    public URI getApiEndpoint() {
        return apiEndpoint;
    }

    public ModelSpecification setApiEndpoint(URI apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelSpecification that)) return false;
        return Objects.equals(providerId, that.providerId) &&
                Objects.equals(modelFamily, that.modelFamily) &&
                Objects.equals(modelName, that.modelName) &&
                Objects.equals(modelVersion, that.modelVersion) &&
                Objects.equals(apiEndpoint, that.apiEndpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerId, modelFamily, modelName, modelVersion, apiEndpoint);
    }

    @Override
    public String toString() {
        return "ModelSpecification{" +
                "providerId='" + providerId + '\'' +
                ", modelName='" + modelName + '\'' +
                ", modelVersion='" + modelVersion + '\'' +
                '}';
    }
}
