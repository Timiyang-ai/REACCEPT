public static boolean checkProperties(
			Map<String, String> postProp, String url, String method,
			String oauth_consumer_key, String oauth_consumer_secret) {

		OAuthMessage oam = new OAuthMessage(method, url, postProp.entrySet());
		OAuthConsumer cons = new OAuthConsumer("about:blank", oauth_consumer_key,
				oauth_consumer_secret, null);
		OAuthValidator oav = new SimpleOAuthValidator();


		OAuthAccessor acc = new OAuthAccessor(cons);

		String base_string = null;
		try {
			base_string = OAuthSignatureMethod.getBaseString(oam);
		} catch (Exception e) {
			log.warn(e.getLocalizedMessage());
			base_string = null;
			return false;
		}

		try {
			oav.validateMessage(oam, acc);
		} catch (Exception e) {
			log.warn("Provider failed to validate message");
			log.warn(e.getLocalizedMessage());
			if (base_string != null) {
				log.warn(base_string);
			}
			return false;
		}
		return true;
	}