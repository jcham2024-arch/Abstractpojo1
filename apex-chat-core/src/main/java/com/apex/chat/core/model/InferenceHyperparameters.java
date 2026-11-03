package com.apex.chat.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sampling parameters and generation controls for an LLM model invocation.
 */
public class InferenceHyperparameters implements Serializable {

    private Double temperature;
    private Double topP;
    private Integer topK;
    private Double frequencyPenalty;
    private Double presencePenalty;
    private List<String> stopSequences;
    private Long seed;

    public InferenceHyperparameters() {
        this.temperature = 0.7;
        this.topP = 0.95;
        this.stopSequences = new ArrayList<>();
    }

    public Double getTemperature() {
        return temperature;
    }

    public InferenceHyperparameters setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Double getTopP() {
        return topP;
    }

    public InferenceHyperparameters setTopP(Double topP) {
        this.topP = topP;
        return this;
    }

    public Integer getTopK() {
        return topK;
    }

    public InferenceHyperparameters setTopK(Integer topK) {
        this.topK = topK;
        return this;
    }

    public Double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public InferenceHyperparameters setFrequencyPenalty(Double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }

    public Double getPresencePenalty() {
        return presencePenalty;
    }

    public InferenceHyperparameters setPresencePenalty(Double presencePenalty) {
        this.presencePenalty = presencePenalty;
        return this;
    }

    public List<String> getStopSequences() {
        return stopSequences;
    }

    public InferenceHyperparameters setStopSequences(List<String> stopSequences) {
        this.stopSequences = stopSequences != null ? stopSequences : new ArrayList<>();
        return this;
    }

    public InferenceHyperparameters addStopSequence(String stopSequence) {
        if (this.stopSequences == null) {
            this.stopSequences = new ArrayList<>();
        }
        this.stopSequences.add(stopSequence);
        return this;
    }

    public Long getSeed() {
        return seed;
    }

    public InferenceHyperparameters setSeed(Long seed) {
        this.seed = seed;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InferenceHyperparameters that)) return false;
        return Objects.equals(temperature, that.temperature) &&
                Objects.equals(topP, that.topP) &&
                Objects.equals(topK, that.topK) &&
                Objects.equals(frequencyPenalty, that.frequencyPenalty) &&
                Objects.equals(presencePenalty, that.presencePenalty) &&
                Objects.equals(stopSequences, that.stopSequences) &&
                Objects.equals(seed, that.seed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, topP, topK, frequencyPenalty, presencePenalty, stopSequences, seed);
    }

    @Override
    public String toString() {
        return "InferenceHyperparameters{" +
                "temperature=" + temperature +
                ", topP=" + topP +
                ", topK=" + topK +
                '}';
    }
}
