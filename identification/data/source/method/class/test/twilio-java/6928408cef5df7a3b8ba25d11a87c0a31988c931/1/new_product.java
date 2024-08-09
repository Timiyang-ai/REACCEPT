public TwilioRestResponse request(String path, String method,
			Map<String, String> paramMap) throws TwilioRestException {

		List<NameValuePair> paramList = generateParameters(paramMap);
		return request(path, method, paramList);
	}