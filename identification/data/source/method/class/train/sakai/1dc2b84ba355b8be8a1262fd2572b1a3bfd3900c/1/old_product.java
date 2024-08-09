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
			M_log.warning(e.getLocalizedMessage());
			base_string = null;
			return false;
		}

		try {
			oav.validateMessage(oam, acc);
		} catch (Exception e) {
			M_log.warning("Provider failed to validate message");
			M_log.warning(e.getLocalizedMessage());
			if (base_string != null) {
				M_log.warning(base_string);
			}
			return false;
		}
		return true;
	}