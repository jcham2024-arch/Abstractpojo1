package com.apex.chat.core.persistence;

import java.io.Serializable;
import java.util.Objects;

/**
 * Configuration settings dictating payload compression during serialization and storage.
 */
public class CompressionSettings implements Serializable {

    private CompressionAlgorithm algorithm;
    private int compressionLevel;
    private int minSizeBytesToCompress;

    public CompressionSettings() {
        this.algorithm = CompressionAlgorithm.NONE;
        this.compressionLevel = 6;
        this.minSizeBytesToCompress = 1024; // 1 KB threshold
    }

    public CompressionSettings(CompressionAlgorithm algorithm, int compressionLevel) {
        this();
        this.algorithm = algorithm;
        this.compressionLevel = compressionLevel;
    }

    public static CompressionSettings disabled() {
        return new CompressionSettings(CompressionAlgorithm.NONE, 0);
    }

    public static CompressionSettings gzip() {
        return new CompressionSettings(CompressionAlgorithm.GZIP, 6);
    }

    public static CompressionSettings zstd() {
        return new CompressionSettings(CompressionAlgorithm.ZSTD, 3);
    }

    public CompressionAlgorithm getAlgorithm() {
        return algorithm;
    }

    public CompressionSettings setAlgorithm(CompressionAlgorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public int getCompressionLevel() {
        return compressionLevel;
    }

    public CompressionSettings setCompressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
        return this;
    }

    public int getMinSizeBytesToCompress() {
        return minSizeBytesToCompress;
    }

    public CompressionSettings setMinSizeBytesToCompress(int minSizeBytesToCompress) {
        this.minSizeBytesToCompress = minSizeBytesToCompress;
        return this;
    }

    public boolean isCompressionEnabled() {
        return this.algorithm != null && this.algorithm != CompressionAlgorithm.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompressionSettings that)) return false;
        return compressionLevel == that.compressionLevel &&
                minSizeBytesToCompress == that.minSizeBytesToCompress &&
                algorithm == that.algorithm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(algorithm, compressionLevel, minSizeBytesToCompress);
    }

    @Override
    public String toString() {
        return "CompressionSettings{" +
                "algorithm=" + algorithm +
                ", compressionLevel=" + compressionLevel +
                ", minSizeBytesToCompress=" + minSizeBytesToCompress +
                '}';
    }
}
