package com.mgiorda.oauth;

import java.util.Objects;

/**
 * HttpParameter class corresponding to key/value pairs in HTTP requests: query parameters and 
 * www-form/urlencoded parameters. 
 * This class is used to support the possibility of having multiple valued parameters.
 */
public class HttpParameter implements Comparable<HttpParameter> {

    private final String key;
    private final String value;

    public HttpParameter(String key, String value) {
        
    	Objects.requireNonNull(key, "Key cannot be null");
    	Objects.requireNonNull(value, "Value cannot be null");
    	
    	this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof HttpParameter)) {
            return false;
        }

        final HttpParameter otherParam = (HttpParameter) other;
        return otherParam.getKey().equals(key) && otherParam.getValue().equals(value);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }

    @Override
    public int compareTo(HttpParameter parameter) {
        final int keyDiff = key.compareTo(parameter.getKey());

        return keyDiff == 0 ? value.compareTo(parameter.getValue()) : keyDiff;
    }
}
