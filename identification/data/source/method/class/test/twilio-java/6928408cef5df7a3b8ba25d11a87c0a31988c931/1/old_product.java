public TwilioRestResponse request(String path, String method,
			Map<String, String> vars) throws TwilioRestException {

		HttpUriRequest request = setupRequest(path, method, vars);

		HttpResponse response;
		try {
			response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			Header[] contentTypeHeaders = response.getHeaders("Content-Type");
			String responseBody = "";

			if (entity != null) {
				responseBody = EntityUtils.toString(entity);
			}

			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();

			TwilioRestResponse restResponse = new TwilioRestResponse(request
					.getURI().toString(), responseBody, statusCode);

			// For now we only set the first content type seen
			for (Header h : contentTypeHeaders) {
				restResponse.setContentType(h.getValue());
				break;
			}

			return restResponse;

		} catch (ClientProtocolException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}