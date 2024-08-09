public ServiceCall<Void> addWords(final CustomVoiceModel model, final CustomTranslation... translations) {
    Validator.notNull(model, "model cannot be null");
    Validator.notEmpty(model.getId(), "model id must not be empty");
    Validator.notNull(translations, "translations cannot be null");

    final String json = GSON.toJson(Collections.singletonMap("words", translations));
    final String path = String.format(PATH_WORDS, model.getId());
    final RequestBody body = RequestBody.create(HttpMediaType.JSON, json);
    final Request request = RequestBuilder.post(path).body(body).build();
    return createServiceCall(request, ResponseConverterUtils.getVoid());
  }