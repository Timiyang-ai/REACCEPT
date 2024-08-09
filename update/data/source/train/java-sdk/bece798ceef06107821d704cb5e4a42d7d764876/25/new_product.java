public Classifier getClassifier(String classifierId) {
    if (classifierId == null || classifierId.isEmpty())
      throw new IllegalArgumentException("classifierId cannot be null or empty");

    final Request request = RequestBuilder.get("/v1/classifiers/" + classifierId).build();
    return executeRequest(request, Classifier.class);
  }