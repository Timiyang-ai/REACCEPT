public ServiceCall<CustomTranslation> getWord(final CustomVoiceModel model, final String word) {
    Validator.notNull(model, "model cannot be null");
    Validator.notEmpty(model.getId(), "model id must not be empty");
    Validator.notNull(word, "word cannot be null");

    final Request request = RequestBuilder.get(String.format(PATH_WORD, model.getId(), word)).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(CustomTranslation.class));
  }