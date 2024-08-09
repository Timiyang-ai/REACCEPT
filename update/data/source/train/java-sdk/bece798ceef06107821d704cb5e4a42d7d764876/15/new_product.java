public Classifiers getClassifiers() {
    final Request request = RequestBuilder.get("/v1/classifiers").build();
    return executeRequest(request, Classifiers.class);
  }