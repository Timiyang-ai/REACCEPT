private InputStream convertDocument(final File document, final String mediaType,
      final ConversionTarget conversionTarget) {

    if (document == null || !document.exists())
      throw new IllegalArgumentException("document cannot be null and must exist");

    final String type =
        mediaType != null ? mediaType : ConversionUtils.getMediaTypeFromFile(document);
    if (type == null)
      throw new RuntimeException("mediaType cannot be null or empty");
    else if (!ConversionUtils.isValidMediaType(type))
      throw new IllegalArgumentException("file with the given media type is not supported");

    final JsonObject configJson = new JsonObject();
    configJson.addProperty(CONVERSION_TARGET, conversionTarget.toString());

    final RequestBody body =
        new MultipartBuilder()
            .addFormDataPart(CONFIG, configJson.toString())
            .addFormDataPart(FILE, document.getName(),
                RequestBody.create(MediaType.parse(type), document)).build();

    final Request request = RequestBuilder.post(CONVERT_DOCUMENT_PATH).withBody(body).build();

    final Response response = execute(request);
    return ResponseUtil.getInputStream(response);
  }