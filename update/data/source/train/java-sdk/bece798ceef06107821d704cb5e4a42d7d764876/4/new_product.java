public Classification classify(final String classifierId, final String text) {
    if (classifierId == null || classifierId.isEmpty())
      throw new IllegalArgumentException("classifierId cannot be null or empty");

    if (text == null || text.isEmpty())
      throw new IllegalArgumentException("text cannot be null or empty");

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("text", text);

    final String path = String.format("/v1/classifiers/%s/classify", classifierId);

    final Request request = RequestBuilder.post(path).withBodyJson(contentJson).build();

    final Response response = execute(request);
    final Classification classification = ResponseUtil.getObject(response, Classification.class);

    for (final ClassifiedClass klass : classification.getClasses()) {
      if (klass.getName().equals(classification.getTopClass())) {
        classification.setTopConfidence(klass.getConfidence());
        break;
      }
    }
    return classification;
  }