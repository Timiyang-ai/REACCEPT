public ServiceCall<Void> deleteClassifier(DeleteClassifierOptions deleteClassifierOptions) {
    Validator.notNull(deleteClassifierOptions, "deleteClassifierOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(String.format("/v1/classifiers/%s", deleteClassifierOptions
        .classifierId()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }