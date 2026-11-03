package com.apex.chat.core.persistence;

/**
 * Supported payload compression algorithms.
 */
public enum CompressionAlgorithm {
    NONE,
    GZIP,
    ZSTD,
    LZ4,
    SNAPPY
}
