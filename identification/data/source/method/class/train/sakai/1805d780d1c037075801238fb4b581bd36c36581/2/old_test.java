	@Test
	public void signProperties() {
		Map<String, String> props = new HashMap<>();
		Map<String, String> extra = new HashMap<>();

		String url = "https://www.sakailms.org/";
		String key = "key";
		String secret = "secret";
		Map<String, String> signedParams = BasicLTIUtil.signProperties(props, url, OAuthMessage.POST, key, secret,
				"guid", "desc", "tool_url",
				"name", "email", extra);
		assertNotNull(signedParams);

		signedParams = BasicLTIUtil.signProperties(props, url, OAuthMessage.GET, null, secret,
				"guid", "desc", "tool_url",
				"name", "email", extra);
		assertNotNull(signedParams);
	}