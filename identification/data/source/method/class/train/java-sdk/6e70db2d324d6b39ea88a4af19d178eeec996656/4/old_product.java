public ServiceCall<Classifier> getClassifier(String classifierId) {
    Validator.isTrue((classifierId != null) && !classifierId.isEmpty(), "classifierId cannot be null or empty");

    final Request request = RequestBuilder.get(String.format(PATH_CLASSIFIER, classifierId)).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(Classifier.class));
  }