public Object read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		InputStream in = inputMessage.getBody();
		return JSON.parseObject(in, charset, type);
	}