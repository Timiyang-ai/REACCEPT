public static Map<String, String> signProperties(
			Map<String, String> postProp, String url, String method,
			String oauth_consumer_key, String oauth_consumer_secret,
			String tool_consumer_instance_guid,
			String tool_consumer_instance_description,
			String tool_consumer_instance_url, String tool_consumer_instance_name,
			String tool_consumer_instance_contact_email) {
		postProp = BasicLTIUtil.cleanupProperties(postProp);

		if ( postProp.get(LTI_VERSION) == null ) postProp.put(LTI_VERSION, "LTI-1p0");
		if ( postProp.get(LTI_MESSAGE_TYPE) == null ) postProp.put(LTI_MESSAGE_TYPE, "basic-lti-launch-request");

		// Allow caller to internationalize this for us...
		if (postProp.get(BASICLTI_SUBMIT) == null) {
			postProp.put(BASICLTI_SUBMIT, "Launch Endpoint with BasicLTI Data");
		}
		if (tool_consumer_instance_guid != null)
			postProp.put(TOOL_CONSUMER_INSTANCE_GUID, tool_consumer_instance_guid);
		if (tool_consumer_instance_description != null)
			postProp.put(TOOL_CONSUMER_INSTANCE_DESCRIPTION,
					tool_consumer_instance_description);
		if (tool_consumer_instance_url != null)
			postProp.put(TOOL_CONSUMER_INSTANCE_URL, tool_consumer_instance_url);
		if (tool_consumer_instance_name != null)
			postProp.put(TOOL_CONSUMER_INSTANCE_NAME, tool_consumer_instance_name);
		if (tool_consumer_instance_contact_email != null)
			postProp.put(TOOL_CONSUMER_INSTANCE_CONTACT_EMAIL,
					tool_consumer_instance_contact_email);

		if (postProp.get("oauth_callback") == null)
			postProp.put("oauth_callback", "about:blank");

		if (oauth_consumer_key == null || oauth_consumer_secret == null) {
			dPrint("No signature generated in signProperties");
			return postProp;
		}

		OAuthMessage oam = new OAuthMessage(method, url, postProp.entrySet());
		OAuthConsumer cons = new OAuthConsumer("about:blank", oauth_consumer_key,
				oauth_consumer_secret, null);
		OAuthAccessor acc = new OAuthAccessor(cons);
		try {
			oam.addRequiredParameters(acc);
			// System.out.println("Base Message String\n"+OAuthSignatureMethod.getBaseString(oam)+"\n");

			List<Map.Entry<String, String>> params = oam.getParameters();

			Map<String, String> nextProp = new HashMap<String, String>();
			// Convert to Map<String, String>
			for (final Map.Entry<String, String> entry : params) {
				nextProp.put(entry.getKey(), entry.getValue());
			}
			return nextProp;
		} catch (net.oauth.OAuthException e) {
			M_log.warning("BasicLTIUtil.signProperties OAuth Exception "
					+ e.getMessage());
			throw new Error(e);
		} catch (java.io.IOException e) {
			M_log.warning("BasicLTIUtil.signProperties IO Exception "
					+ e.getMessage());
			throw new Error(e);
		} catch (java.net.URISyntaxException e) {
			M_log.warning("BasicLTIUtil.signProperties URI Syntax Exception "
					+ e.getMessage());
			throw new Error(e);
		}

	}