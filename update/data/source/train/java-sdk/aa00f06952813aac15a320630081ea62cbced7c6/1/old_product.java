public ServiceCall<UtterancesTone> getChatTone(ToneChatInput chatInput) {
    Validator.notNull(chatInput.getUtterances(), "Input utterances cannot be null");

    RequestBuilder requestBuilder = RequestBuilder.post(PATH_CHAT_TONE).query(VERSION_DATE, versionDate);
    requestBuilder.bodyJson(GsonSingleton.getGson().toJsonTree(chatInput).getAsJsonObject());

    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(UtterancesTone.class));
  }