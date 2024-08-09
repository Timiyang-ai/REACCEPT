@Test
	public void testGetOAuthQueryString() throws Exception {
		final TreeMap<String, Set<CharSequence>> params = new TreeMap<String, Set<CharSequence>>();
		CoreOAuthConsumerSupport support = new CoreOAuthConsumerSupport() {
			@Override
			protected Map<String, Set<CharSequence>> loadOAuthParameters(ProtectedResourceDetails details,
					URL requestURL, OAuthConsumerToken requestToken, String httpMethod,
					Map<String, String> additionalParameters) {
				return params;
			}
		};

		URL url = new URL("https://myhost.com/somepath?with=some&query=params&too");
		OAuthConsumerToken token = new OAuthConsumerToken();

		when(details.isAcceptsAuthorizationHeader()).thenReturn(true);
		params.put("with", Collections.singleton((CharSequence) "some"));
		params.put("query", Collections.singleton((CharSequence) "params"));
		params.put("too", null);
		params.put(OAuthConsumerParameter.oauth_consumer_key.toString(), Collections.singleton((CharSequence) "mykey"));
		params.put(OAuthConsumerParameter.oauth_nonce.toString(), Collections.singleton((CharSequence) "mynonce"));
		params.put(OAuthConsumerParameter.oauth_timestamp.toString(), Collections.singleton((CharSequence) "myts"));
		assertEquals("query=params&too&with=some", support.getOAuthQueryString(details, token, url, "POST", null));

		when(details.isAcceptsAuthorizationHeader()).thenReturn(false);
		params.put("with", Collections.singleton((CharSequence) "some"));
		params.put("query", Collections.singleton((CharSequence) "params"));
		params.put("too", null);
		params.put(OAuthConsumerParameter.oauth_consumer_key.toString(), Collections.singleton((CharSequence) "mykey"));
		params.put(OAuthConsumerParameter.oauth_nonce.toString(), Collections.singleton((CharSequence) "mynonce"));
		params.put(OAuthConsumerParameter.oauth_timestamp.toString(), Collections.singleton((CharSequence) "myts"));
		assertEquals("oauth_consumer_key=mykey&oauth_nonce=mynonce&oauth_timestamp=myts&query=params&too&with=some",
				support.getOAuthQueryString(details, token, url, "POST", null));

		when(details.isAcceptsAuthorizationHeader()).thenReturn(false);
		params.put("with", Collections.singleton((CharSequence) "some"));
		String encoded_space = URLEncoder.encode(" ", "utf-8");
		params.put("query", Collections.singleton((CharSequence) ("params spaced")));
		params.put("too", null);
		params.put(OAuthConsumerParameter.oauth_consumer_key.toString(), Collections.singleton((CharSequence) "mykey"));
		params.put(OAuthConsumerParameter.oauth_nonce.toString(), Collections.singleton((CharSequence) "mynonce"));
		params.put(OAuthConsumerParameter.oauth_timestamp.toString(), Collections.singleton((CharSequence) "myts"));
		assertEquals("oauth_consumer_key=mykey&oauth_nonce=mynonce&oauth_timestamp=myts&query=params" + encoded_space
				+ "spaced&too&with=some", support.getOAuthQueryString(details, token, url, "POST", null));
	}