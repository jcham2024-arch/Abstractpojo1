package com.apex.chat.core.content;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Binary media block representing an image, audio clip, or video attachment.
 */
public class BinaryMediaBlock implements ContentBlock {

    private String blockId;
    private String mimeType;
    private byte[] data;
    private URI sourceUri;
    private String fileName;
    private long fileSizeBytes;

    public BinaryMediaBlock() {
        this.blockId = UUID.randomUUID().toString();
    }

    public BinaryMediaBlock(String mimeType, byte[] data, String fileName) {
        this.blockId = UUID.randomUUID().toString();
        this.mimeType = mimeType;
        this.data = data;
        this.fileName = fileName;
        this.fileSizeBytes = data != null ? data.length : 0L;
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public BinaryMediaBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public BinaryMediaBlock setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public BinaryMediaBlock setData(byte[] data) {
        this.data = data;
        if (data != null && this.fileSizeBytes == 0L) {
            this.fileSizeBytes = data.length;
        }
        return this;
    }

    public URI getSourceUri() {
        return sourceUri;
    }

    public BinaryMediaBlock setSourceUri(URI sourceUri) {
        this.sourceUri = sourceUri;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public BinaryMediaBlock setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public long getFileSizeBytes() {
        return fileSizeBytes;
    }

    public BinaryMediaBlock setFileSizeBytes(long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.BINARY_MEDIA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryMediaBlock that)) return false;
        return fileSizeBytes == that.fileSizeBytes &&
                Objects.equals(blockId, that.blockId) &&
                Objects.equals(mimeType, that.mimeType) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(sourceUri, that.sourceUri) &&
                Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(blockId, mimeType, sourceUri, fileName, fileSizeBytes);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "BinaryMediaBlock{" +
                "blockId='" + blockId + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSizeBytes=" + fileSizeBytes +
                ", sourceUri=" + sourceUri +
                '}';
    }
}
