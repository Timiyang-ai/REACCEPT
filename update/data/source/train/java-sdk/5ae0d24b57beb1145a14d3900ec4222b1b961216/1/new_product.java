public ServiceCall<InputStream> synthesize(final String text, final Voice voice, final AudioFormat audioFormat,
      String customizationId) {
    Validator.isTrue((text != null) && !text.isEmpty(), "text cannot be null or empty");
    Validator.isTrue(voice != null, "voice cannot be null or empty");

    final RequestBuilder request = RequestBuilder.post(PATH_SYNTHESIZE);

    JsonObject jsonText = new JsonObject();
    jsonText.addProperty(TEXT, text);
    request.bodyJson(jsonText);
    
    request.query(VOICE, voice.getName());
    request.query(ACCEPT, audioFormat != null ? audioFormat : AudioFormat.WAV);

    if (customizationId != null) {
      request.query(CUSTOMIZATION_ID, customizationId);
    }

    return createServiceCall(request.build(), ResponseConverterUtils.getInputStream());
  }