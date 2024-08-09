	private DefaultFullHttpResponse response() {
		DefaultFullHttpResponse r =
				new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				                            HttpResponseStatus.ACCEPTED);
		r.headers()
		 .set(HttpHeaderNames.CONTENT_LENGTH, 0);
		return r;
	}