package com.apex.chat.core.ui;

import com.apex.chat.core.message.InputModality;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Declares the visual, media, and streaming capabilities of a chat interface.
 */
public class InterfaceCapabilities implements Serializable {

    private boolean supportsStreaming;
    private boolean supportsMarkdownRendering;
    private boolean supportsCodeSyntaxHighlighting;
    private List<InputModality> supportedModalities;
    private boolean supportsRichMedia;
    private boolean supportsInlineConfirmation;

    public InterfaceCapabilities() {
        this.supportsStreaming = true;
        this.supportsMarkdownRendering = true;
        this.supportsCodeSyntaxHighlighting = true;
        this.supportsRichMedia = true;
        this.supportsInlineConfirmation = true;
        this.supportedModalities = new ArrayList<>();
        this.supportedModalities.add(InputModality.TEXT);
    }

    public boolean isSupportsStreaming() {
        return supportsStreaming;
    }

    public InterfaceCapabilities setSupportsStreaming(boolean supportsStreaming) {
        this.supportsStreaming = supportsStreaming;
        return this;
    }

    public boolean isSupportsMarkdownRendering() {
        return supportsMarkdownRendering;
    }

    public InterfaceCapabilities setSupportsMarkdownRendering(boolean supportsMarkdownRendering) {
        this.supportsMarkdownRendering = supportsMarkdownRendering;
        return this;
    }

    public boolean isSupportsCodeSyntaxHighlighting() {
        return supportsCodeSyntaxHighlighting;
    }

    public InterfaceCapabilities setSupportsCodeSyntaxHighlighting(boolean supportsCodeSyntaxHighlighting) {
        this.supportsCodeSyntaxHighlighting = supportsCodeSyntaxHighlighting;
        return this;
    }

    public List<InputModality> getSupportedModalities() {
        return supportedModalities;
    }

    public InterfaceCapabilities setSupportedModalities(List<InputModality> supportedModalities) {
        this.supportedModalities = supportedModalities != null ? supportedModalities : new ArrayList<>();
        return this;
    }

    public InterfaceCapabilities addSupportedModality(InputModality modality) {
        if (this.supportedModalities == null) {
            this.supportedModalities = new ArrayList<>();
        }
        this.supportedModalities.add(modality);
        return this;
    }

    public boolean isSupportsRichMedia() {
        return supportsRichMedia;
    }

    public InterfaceCapabilities setSupportsRichMedia(boolean supportsRichMedia) {
        this.supportsRichMedia = supportsRichMedia;
        return this;
    }

    public boolean isSupportsInlineConfirmation() {
        return supportsInlineConfirmation;
    }

    public InterfaceCapabilities setSupportsInlineConfirmation(boolean supportsInlineConfirmation) {
        this.supportsInlineConfirmation = supportsInlineConfirmation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterfaceCapabilities that)) return false;
        return supportsStreaming == that.supportsStreaming &&
                supportsMarkdownRendering == that.supportsMarkdownRendering &&
                supportsCodeSyntaxHighlighting == that.supportsCodeSyntaxHighlighting &&
                supportsRichMedia == that.supportsRichMedia &&
                supportsInlineConfirmation == that.supportsInlineConfirmation &&
                Objects.equals(supportedModalities, that.supportedModalities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supportsStreaming, supportsMarkdownRendering, supportsCodeSyntaxHighlighting,
                supportedModalities, supportsRichMedia, supportsInlineConfirmation);
    }

    @Override
    public String toString() {
        return "InterfaceCapabilities{" +
                "supportsStreaming=" + supportsStreaming +
                ", supportsMarkdownRendering=" + supportsMarkdownRendering +
                ", supportsRichMedia=" + supportsRichMedia +
                '}';
    }
}
