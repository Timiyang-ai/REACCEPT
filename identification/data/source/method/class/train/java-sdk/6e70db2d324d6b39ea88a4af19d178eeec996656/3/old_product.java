public ServiceCall<Void> deleteClassifier(String classifierId) {
    Validator.isTrue((classifierId != null) && !classifierId.isEmpty(), "classifierId cannot be null or empty");

    final Request request = RequestBuilder.delete(String.format(PATH_CLASSIFIER, classifierId)).build();
    return createServiceCall(request, ResponseConverterUtils.getVoid());
  }