package com.apex.chat.core.persistence;

/**
 * Data serialization formats supported for serializing chat entities and state.
 */
public enum SerializationFormat {
    JSON,
    BINARY_PROTOBUF,
    JAVA_NATIVE,
    YAML,
    CBOR
}
