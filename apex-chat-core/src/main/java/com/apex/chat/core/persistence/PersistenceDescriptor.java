package com.apex.chat.core.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * PersistenceDescriptor POJO abstracting how chat history and agent state should be serialized,
 * stored, compressed, and retained.
 */
public class PersistenceDescriptor implements Serializable {

    private String descriptorId;
    private StorageStrategy storageStrategy;
    private SerializationFormat serializationFormat;
    private CompressionSettings compressionSettings;
    private String targetDestination;
    private Integer autoSaveIntervalSeconds;
    private boolean encryptAtRest;
    private Integer retentionPolicyDays;
    private Map<String, String> customProperties;

    public PersistenceDescriptor() {
        this.descriptorId = UUID.randomUUID().toString();
        this.storageStrategy = StorageStrategy.MEMORY;
        this.serializationFormat = SerializationFormat.JSON;
        this.compressionSettings = new CompressionSettings();
        this.encryptAtRest = false;
        this.customProperties = new HashMap<>();
    }

    public PersistenceDescriptor(StorageStrategy storageStrategy, String targetDestination) {
        this();
        this.storageStrategy = storageStrategy;
        this.targetDestination = targetDestination;
    }

    public static PersistenceDescriptor inMemory() {
        return new PersistenceDescriptor(StorageStrategy.MEMORY, "in-memory");
    }

    public static PersistenceDescriptor fileSystem(String directoryPath) {
        return new PersistenceDescriptor(StorageStrategy.FILE_SYSTEM, directoryPath)
                .setCompressionSettings(CompressionSettings.gzip());
    }

    public static PersistenceDescriptor database(String connectionUri) {
        return new PersistenceDescriptor(StorageStrategy.RELATIONAL_DATABASE, connectionUri);
    }

    public static PersistenceDescriptor contentAddressableStorage(String casRoot) {
        return new PersistenceDescriptor(StorageStrategy.CONTENT_ADDRESSABLE_STORAGE, casRoot)
                .setCompressionSettings(CompressionSettings.zstd());
    }

    public String getDescriptorId() {
        return descriptorId;
    }

    public PersistenceDescriptor setDescriptorId(String descriptorId) {
        this.descriptorId = descriptorId;
        return this;
    }

    public StorageStrategy getStorageStrategy() {
        return storageStrategy;
    }

    public PersistenceDescriptor setStorageStrategy(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
        return this;
    }

    public SerializationFormat getSerializationFormat() {
        return serializationFormat;
    }

    public PersistenceDescriptor setSerializationFormat(SerializationFormat serializationFormat) {
        this.serializationFormat = serializationFormat;
        return this;
    }

    public CompressionSettings getCompressionSettings() {
        return compressionSettings;
    }

    public PersistenceDescriptor setCompressionSettings(CompressionSettings compressionSettings) {
        this.compressionSettings = compressionSettings != null ? compressionSettings : new CompressionSettings();
        return this;
    }

    public String getTargetDestination() {
        return targetDestination;
    }

    public PersistenceDescriptor setTargetDestination(String targetDestination) {
        this.targetDestination = targetDestination;
        return this;
    }

    public Integer getAutoSaveIntervalSeconds() {
        return autoSaveIntervalSeconds;
    }

    public PersistenceDescriptor setAutoSaveIntervalSeconds(Integer autoSaveIntervalSeconds) {
        this.autoSaveIntervalSeconds = autoSaveIntervalSeconds;
        return this;
    }

    public boolean isEncryptAtRest() {
        return encryptAtRest;
    }

    public PersistenceDescriptor setEncryptAtRest(boolean encryptAtRest) {
        this.encryptAtRest = encryptAtRest;
        return this;
    }

    public Integer getRetentionPolicyDays() {
        return retentionPolicyDays;
    }

    public PersistenceDescriptor setRetentionPolicyDays(Integer retentionPolicyDays) {
        this.retentionPolicyDays = retentionPolicyDays;
        return this;
    }

    public Map<String, String> getCustomProperties() {
        return customProperties;
    }

    public PersistenceDescriptor setCustomProperties(Map<String, String> customProperties) {
        this.customProperties = customProperties != null ? customProperties : new HashMap<>();
        return this;
    }

    public PersistenceDescriptor putCustomProperty(String key, String value) {
        if (this.customProperties == null) {
            this.customProperties = new HashMap<>();
        }
        this.customProperties.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistenceDescriptor that)) return false;
        return encryptAtRest == that.encryptAtRest &&
                Objects.equals(descriptorId, that.descriptorId) &&
                storageStrategy == that.storageStrategy &&
                serializationFormat == that.serializationFormat &&
                Objects.equals(compressionSettings, that.compressionSettings) &&
                Objects.equals(targetDestination, that.targetDestination) &&
                Objects.equals(autoSaveIntervalSeconds, that.autoSaveIntervalSeconds) &&
                Objects.equals(retentionPolicyDays, that.retentionPolicyDays) &&
                Objects.equals(customProperties, that.customProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptorId, storageStrategy, serializationFormat, compressionSettings,
                targetDestination, autoSaveIntervalSeconds, encryptAtRest, retentionPolicyDays, customProperties);
    }

    @Override
    public String toString() {
        return "PersistenceDescriptor{" +
                "descriptorId='" + descriptorId + '\'' +
                ", storageStrategy=" + storageStrategy +
                ", serializationFormat=" + serializationFormat +
                ", targetDestination='" + targetDestination + '\'' +
                ", encryptAtRest=" + encryptAtRest +
                '}';
    }
}
