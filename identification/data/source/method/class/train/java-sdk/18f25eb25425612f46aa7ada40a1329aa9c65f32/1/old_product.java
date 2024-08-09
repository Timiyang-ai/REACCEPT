public ServiceCall<UtterancesTone> getChatTone(String jsonText) {
    Validator.notNull(jsonText, "text cannot be null");

    RequestBuilder requestBuilder = RequestBuilder.post(PATH_CHAT_TONE).query(VERSION_DATE, versionDate);
    JsonObject contentJson = new JsonParser().parse(jsonText).getAsJsonObject();
    requestBuilder.bodyJson(contentJson);

    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(UtterancesTone.class));
  }