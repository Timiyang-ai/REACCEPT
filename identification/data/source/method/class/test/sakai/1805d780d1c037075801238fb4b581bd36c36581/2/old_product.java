public static Properties signProperties(Properties postProp, String url,
			String method, String oauth_consumer_key, String oauth_consumer_secret,
			String org_id, String org_desc, String org_url) {
		final Map<String, String> signedMap = signProperties(
				convertToMap(postProp), url, method, oauth_consumer_key,
				oauth_consumer_secret, org_id, org_desc, org_url, null, null);
		return convertToProperties(signedMap);
	}