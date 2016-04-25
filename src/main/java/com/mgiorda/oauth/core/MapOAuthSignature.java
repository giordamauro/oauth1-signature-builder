package com.mgiorda.oauth.core;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mgiorda.oauth.OAuthField;
import com.mgiorda.oauth.OAuthSignature;

class MapOAuthSignature implements OAuthSignature {

    private final Map<OAuthField, String> oauthParameters;

    public MapOAuthSignature(Map<OAuthField, String> oauthParameters) {
    	
    	Objects.requireNonNull(oauthParameters);
        this.oauthParameters = Collections.unmodifiableMap(oauthParameters);
    }
    
    public Map<OAuthField, String> getOauthParameters() {
    	return oauthParameters;
    }

    @Override
    public String getSignature() {
        return oauthParameters.get(OAuthField.SIGNATURE);
    }

    @Override
    public String getConsumerKey() {
        return oauthParameters.get(OAuthField.CONSUMER_KEY);
    }

    @Override
    public String getTimestamp() {
        return oauthParameters.get(OAuthField.TIMESTAMP);
    }

    @Override
    public String getNonce() {
        return oauthParameters.get(OAuthField.NONCE);
    }

    @Override
    public String getSignatureMethod() {
        return oauthParameters.get(OAuthField.SIGNATURE_METHOD);
    }

    @Override
    public String getVersion() {
        return oauthParameters.get(OAuthField.VERSION);
    }
    
    @Override
    public Optional<String> getScope() {
        return Optional.ofNullable(oauthParameters.get(OAuthField.SCOPE));
    }

    @Override
    public Optional<String> getCallback() {
        return Optional.ofNullable(oauthParameters.get(OAuthField.CALLBACK));
    }
    
    @Override
    public Optional<String> getToken() {
        return Optional.ofNullable(oauthParameters.get(OAuthField.TOKEN));
    }

    @Override
    public Optional<String> getVerifier() {
        return Optional.ofNullable(oauthParameters.get(OAuthField.VERIFIER));
    }
    
    @Override
	public Optional<String> getRealm() {
    	return Optional.ofNullable(oauthParameters.get(OAuthField.REALM));
	}
    
    public String getAsHeader() {

        String oauthParams = oauthParameters.entrySet().stream()
                .map(entry -> String.format("%s=\"%s\"", entry.getKey().fieldName(), entry.getValue()))
                .collect(Collectors.joining(", "));

        return "OAuth " + oauthParams;
    }
    
    public String getAsQueryString() {
    	
    	String queryString = oauthParameters.entrySet().stream()
                .map(entry -> String.format("%s=%s", entry.getKey().fieldName(), entry.getValue()))
                .collect(Collectors.joining("&"));

    	return queryString;
    }
    
    @Override
    public String toString() {
    	return "Authorization: " + getAsHeader();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oauthParameters == null) ? 0 : oauthParameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapOAuthSignature other = (MapOAuthSignature) obj;
		if (oauthParameters == null) {
			if (other.oauthParameters != null)
				return false;
		} else if (!oauthParameters.equals(other.oauthParameters))
			return false;
		return true;
	}
}
