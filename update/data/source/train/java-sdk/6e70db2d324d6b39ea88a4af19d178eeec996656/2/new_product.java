public ServiceCall<Classifier> getClassifier(GetClassifierOptions getClassifierOptions) {
    Validator.notNull(getClassifierOptions, "getClassifierOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/classifiers/%s", getClassifierOptions
        .classifierId()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Classifier.class));
  }