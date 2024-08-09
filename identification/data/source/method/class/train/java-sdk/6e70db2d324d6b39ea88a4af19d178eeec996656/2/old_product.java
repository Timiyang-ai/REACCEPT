public ServiceCall<Classification> classify(final String classifierId, final String text) {
    Validator.isTrue((classifierId != null) && !classifierId.isEmpty(), "classifierId cannot be null or empty");
    Validator.isTrue((text != null) && !text.isEmpty(), "text cannot be null or empty");

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty(TEXT, text);
    final String path = String.format(PATH_CLASSIFY, classifierId);
    final Request request = RequestBuilder.post(path).bodyJson(contentJson).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(Classification.class));
  }