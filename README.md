# oauth1-signature-builder
Java 8 minimal library to create tailor-made OAuth-1.0a signatures (weights 24.5 KB)

```java

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
```
This code will generate the value for a ready-to-be-used "Authorization" Header:

```
	
	Authorization:	OAuth oauth_nonce="1906386233", oauth_signature="sQYmuXQV2ROJS3ukvpeaNNl2Jp8=", oauth_token="myAccessKey", oauth_consumer_key="myApiKey", oauth_version="1.0", oauth_signature_method="HMAC-SHA1", oauth_timestamp="1461291933"

```
