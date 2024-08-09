public ServiceCall<UtterancesTone> getChatTone(ToneChatRequest chatInput) {
    Validator.notNull(chatInput.utterances(), "chatInput.utterances cannot be null");

    RequestBuilder requestBuilder = RequestBuilder.post(PATH_CHAT_TONE).query(VERSION_DATE, versionDate);
    requestBuilder.bodyJson(GsonSingleton.getGson().toJsonTree(chatInput).getAsJsonObject());

    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(UtterancesTone.class));
  }