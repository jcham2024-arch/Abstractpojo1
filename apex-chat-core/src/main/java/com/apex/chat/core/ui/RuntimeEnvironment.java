package com.apex.chat.core.ui;

import java.io.Serializable;
import java.util.Objects;

/**
 * Host operating environment context for the chat interface.
 */
public class RuntimeEnvironment implements Serializable {

    private String operatingSystem;
    private Integer terminalWidthCols;
    private String viewportResolution;
    private String locale;
    private String timezone;

    public RuntimeEnvironment() {
        this.locale = "en-US";
        this.timezone = "UTC";
    }

    public RuntimeEnvironment(String operatingSystem, String viewportResolution) {
        this();
        this.operatingSystem = operatingSystem;
        this.viewportResolution = viewportResolution;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public RuntimeEnvironment setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public Integer getTerminalWidthCols() {
        return terminalWidthCols;
    }

    public RuntimeEnvironment setTerminalWidthCols(Integer terminalWidthCols) {
        this.terminalWidthCols = terminalWidthCols;
        return this;
    }

    public String getViewportResolution() {
        return viewportResolution;
    }

    public RuntimeEnvironment setViewportResolution(String viewportResolution) {
        this.viewportResolution = viewportResolution;
        return this;
    }

    public String getLocale() {
        return locale;
    }

    public RuntimeEnvironment setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public RuntimeEnvironment setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuntimeEnvironment that)) return false;
        return Objects.equals(operatingSystem, that.operatingSystem) &&
                Objects.equals(terminalWidthCols, that.terminalWidthCols) &&
                Objects.equals(viewportResolution, that.viewportResolution) &&
                Objects.equals(locale, that.locale) &&
                Objects.equals(timezone, that.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatingSystem, terminalWidthCols, viewportResolution, locale, timezone);
    }

    @Override
    public String toString() {
        return "RuntimeEnvironment{" +
                "operatingSystem='" + operatingSystem + '\'' +
                ", viewportResolution='" + viewportResolution + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
