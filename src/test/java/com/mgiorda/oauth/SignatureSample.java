package com.mgiorda.oauth;

import com.mgiorda.oauth.HttpMethod;
import com.mgiorda.oauth.OAuthConfig;
import com.mgiorda.oauth.OAuthConfigBuilder;
import com.mgiorda.oauth.OAuthSignature;

public class SignatureSample {

    public static void main(String[] args) {

		OAuthConfig oauthConfig = new OAuthConfigBuilder("myApiKey", "myApiSecret")
				.setTokenKeys("myAccessKey", "myAccessSecret")
				.build();

		OAuthSignature signature = oauthConfig.buildSignature(HttpMethod.GET, "http://serviceUrl")
				.addQueryParam("aParam", "aValue")
				.addFormUrlEncodedParam("myParam", "anotherValue")
				.create();

		System.out.println(signature.getAsHeader());
    }
}