package com.apex.chat.core.content;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

/**
 * Structured document or source code block attached to a chat.
 */
public class FileDocumentBlock implements ContentBlock {

    private String blockId;
    private String documentTitle;
    private String mimeType;
    private String textContent;
    private URI sourceUri;

    public FileDocumentBlock() {
        this.blockId = UUID.randomUUID().toString();
    }

    public FileDocumentBlock(String documentTitle, String mimeType, String textContent) {
        this.blockId = UUID.randomUUID().toString();
        this.documentTitle = documentTitle;
        this.mimeType = mimeType;
        this.textContent = textContent;
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public FileDocumentBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public FileDocumentBlock setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public FileDocumentBlock setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public FileDocumentBlock setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public URI getSourceUri() {
        return sourceUri;
    }

    public FileDocumentBlock setSourceUri(URI sourceUri) {
        this.sourceUri = sourceUri;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.FILE_DOCUMENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileDocumentBlock that)) return false;
        return Objects.equals(blockId, that.blockId) &&
                Objects.equals(documentTitle, that.documentTitle) &&
                Objects.equals(mimeType, that.mimeType) &&
                Objects.equals(textContent, that.textContent) &&
                Objects.equals(sourceUri, that.sourceUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, documentTitle, mimeType, textContent, sourceUri);
    }

    @Override
    public String toString() {
        return "FileDocumentBlock{" +
                "blockId='" + blockId + '\'' +
                ", documentTitle='" + documentTitle + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", sourceUri=" + sourceUri +
                '}';
    }
}
