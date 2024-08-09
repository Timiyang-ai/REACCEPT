	@Test
	public void checkProperties() {
		Map<String, String> props = new HashMap<>();
		boolean checkedProperties = BasicLTIUtil.checkProperties(props, "https://www.sakailms.org/", "POST",
				"key", "secret");
		assertFalse(checkedProperties);

		String url = "https://www.sakailms.org/";
		String key = "key";
		String secret = "secret";
		Map<String, String> signedParams = BasicLTIUtil.signProperties(props, url, OAuthMessage.POST, key, secret,
				"guid", "desc", "tool_url",
				"name", "email", null);
		checkedProperties = BasicLTIUtil.checkProperties(signedParams, url, OAuthMessage.POST,
				key, secret);
		assertTrue(checkedProperties);
	}