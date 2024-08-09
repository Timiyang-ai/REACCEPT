public URL getBaseURL() throws MalformedURLException {

		/*
		 * Check for property 'mode' property in the configuration, if not
		 * found, check for 'service.EndPoint' property in the configuration and default
		 * endpoint to PayPal sandbox or live endpoints. Throw exception if the
		 * above rules fail
		 */
		if (url == null) {
			String mode = this.configurationMap.get(Constants.MODE);
			// Default to Endpoint param.
			String urlString = this.configurationMap.get(Constants.ENDPOINT);
			if (Constants.SANDBOX.equalsIgnoreCase(mode)) {
				urlString = Constants.REST_SANDBOX_ENDPOINT;
			} else if (Constants.LIVE.equalsIgnoreCase(mode)) {
				urlString = Constants.REST_LIVE_ENDPOINT;
			} else if (urlString == null || urlString.length() <= 0) {
				throw new MalformedURLException(
						"service.EndPoint not set (OR) mode not configured to sandbox/live ");
			}
			if (!urlString.endsWith("/")) {
				urlString += "/";
			}
			url = new URL(urlString);
		}
		return url;
	}