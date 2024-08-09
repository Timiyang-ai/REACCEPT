public ServiceCall<Void> addWord(final CustomVoiceModel model, final CustomTranslation translation) {
    Validator.notNull(model, "model cannot be null");
    Validator.notEmpty(model.getId(), "model id must not be empty");
    Validator.notNull(translation, "translation cannot be null");
    Validator.notEmpty(translation.getWord(), "translation word cannot be empty");

    final String path = String.format(PATH_WORD, model.getId(), translation.getWord());
    final RequestBody body = RequestBody.create(HttpMediaType.JSON, translation.toString());
    final Request request = RequestBuilder.put(path).body(body).build();
    return createServiceCall(request, ResponseConverterUtils.getVoid());
  }