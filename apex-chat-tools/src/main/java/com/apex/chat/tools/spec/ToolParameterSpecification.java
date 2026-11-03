package com.apex.chat.tools.spec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Schema definition for an individual argument/parameter accepted by a tool.
 */
public class ToolParameterSpecification implements Serializable {

    private String parameterName;
    private String dataType;
    private String description;
    private boolean required;
    private List<String> enumValues;
    private String defaultValue;

    public ToolParameterSpecification() {
        this.dataType = "string";
        this.required = true;
        this.enumValues = new ArrayList<>();
    }

    public ToolParameterSpecification(String parameterName, String dataType, String description, boolean required) {
        this();
        this.parameterName = parameterName;
        this.dataType = dataType;
        this.description = description;
        this.required = required;
    }

    public String getParameterName() {
        return parameterName;
    }

    public ToolParameterSpecification setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public ToolParameterSpecification setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToolParameterSpecification setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public ToolParameterSpecification setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    public ToolParameterSpecification setEnumValues(List<String> enumValues) {
        this.enumValues = enumValues != null ? enumValues : new ArrayList<>();
        return this;
    }

    public ToolParameterSpecification addEnumValue(String value) {
        if (this.enumValues == null) {
            this.enumValues = new ArrayList<>();
        }
        this.enumValues.add(value);
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public ToolParameterSpecification setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolParameterSpecification that)) return false;
        return required == that.required &&
                Objects.equals(parameterName, that.parameterName) &&
                Objects.equals(dataType, that.dataType) &&
                Objects.equals(description, that.description) &&
                Objects.equals(enumValues, that.enumValues) &&
                Objects.equals(defaultValue, that.defaultValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterName, dataType, description, required, enumValues, defaultValue);
    }

    @Override
    public String toString() {
        return "ToolParameterSpecification{" +
                "parameterName='" + parameterName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", required=" + required +
                '}';
    }
}
