private InputStream convertDocument(final File document, final String mediaType,
			final ConversionTarget conversionTarget) {

		if (document == null || !document.exists())
			throw new IllegalArgumentException("document cannot be null and must exist");

		String type = mediaType != null ? mediaType : ConversionUtils.getMediaTypeFromFile(document);
		if (type == null)
			throw new RuntimeException("mediaType cannot be null or empty");
		else if (!ConversionUtils.isValidMediaType(type))
			throw new IllegalArgumentException("file with the given media type is not supported");

		try {
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart(FILE, new FileBody(document, type));
			JsonObject configJson = new JsonObject();
			configJson.addProperty(CONVERSION_TARGET, conversionTarget.toString());
			String json = configJson.toString();
			reqEntity.addPart(CONFIG, new StringBody(json, MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));

			HttpRequestBase request = Request.Post(CONVERT_DOCUMENT_PATH).withEntity(reqEntity).build();

			HttpResponse response = execute(request);
			InputStream is = ResponseUtil.getInputStream(response);
			return is;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}