public ServiceCall<Void> deleteWord(DeleteWordOptions deleteWordOptions) {
    Validator.notNull(deleteWordOptions, "deleteWordOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(String.format("/v1/customizations/%s/words/%s", deleteWordOptions
        .customizationId(), deleteWordOptions.wordName()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }