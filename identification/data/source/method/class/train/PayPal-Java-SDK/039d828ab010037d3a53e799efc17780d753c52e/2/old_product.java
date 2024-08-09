public HttpConnection getConnection(HttpConfiguration httpConfig) {

		if (httpConfig.isGoogleAppEngine()) {
			return new GoogleAppEngineHttpConnection();
		} else {
			return new DefaultHttpConnection();
		}
	}