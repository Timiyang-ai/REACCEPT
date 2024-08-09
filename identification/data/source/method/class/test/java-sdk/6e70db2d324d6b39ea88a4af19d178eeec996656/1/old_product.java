public ServiceCall<Classifier> createClassifier(final String name, final String language, final File trainingData) {
    Validator.isTrue((trainingData != null) && trainingData.exists(), "trainingData cannot be null or not be found");
    Validator.isTrue((language != null) && !language.isEmpty(), "language cannot be null or empty");

    final JsonObject contentJson = new JsonObject();

    contentJson.addProperty(LANGUAGE, language);

    if ((name != null) && !name.isEmpty()) {
      contentJson.addProperty(NAME, name);
    }

    final RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addPart(Headers.of(HttpHeaders.CONTENT_DISPOSITION, FORM_DATA_TRAINING_DATA),
            RequestBody.create(HttpMediaType.BINARY_FILE, trainingData))
        .addFormDataPart(TRAINING_METADATA, contentJson.toString()).build();

    final Request request = RequestBuilder.post(PATH_CLASSIFIERS).body(body).build();
    return createServiceCall(request, ResponseConverterUtils.getObject(Classifier.class));
  }