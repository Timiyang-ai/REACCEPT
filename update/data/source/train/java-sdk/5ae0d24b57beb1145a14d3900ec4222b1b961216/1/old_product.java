public ServiceCall<InputStream> synthesize(final String text, final Voice voice, final AudioFormat audioFormat,
      String customizationId) {
    Validator.isTrue((text != null) && !text.isEmpty(), "text cannot be null or empty");
    Validator.isTrue(voice != null, "voice cannot be null or empty");

    String modifiedText = text.replace(";", "%3B");
    final RequestBuilder request = RequestBuilder.get(PATH_SYNTHESIZE);
    request.query(TEXT, modifiedText);
    request.query(VOICE, voice.getName());
    request.query(ACCEPT, audioFormat != null ? audioFormat : AudioFormat.WAV);

    if (customizationId != null) {
      request.query(CUSTOMIZATION_ID, customizationId);
    }

    return createServiceCall(request.build(), ResponseConverterUtils.getInputStream());
  }