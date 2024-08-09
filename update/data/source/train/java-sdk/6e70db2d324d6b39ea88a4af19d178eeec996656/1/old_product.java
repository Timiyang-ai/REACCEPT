public ServiceCall<Classifiers> getClassifiers() {
    final Request request = RequestBuilder.get(PATH_CLASSIFIERS).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(Classifiers.class));

  }