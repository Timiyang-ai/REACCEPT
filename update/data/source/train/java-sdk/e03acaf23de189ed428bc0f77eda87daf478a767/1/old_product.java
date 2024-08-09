public ServiceCall<CustomTranslation> getWord(CustomVoiceModel model, String word) {
    Validator.notNull(model.getId(), "model id cannot be null");
    Validator.notNull(word, "word cannot be null");

    final Request request = RequestBuilder.get(String.format(PATH_WORD, model.getId(), word)).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(CustomTranslation.class));
  }