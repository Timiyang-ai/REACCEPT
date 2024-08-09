public ServiceCall<VisualClassifier> createClassifier(CreateClassifierOptions options) {
    Validator.notNull(options, " options cannot be null");

    Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    bodyBuilder.addFormDataPart(PARAM_NAME, options.classifierName());

    // Classes
    for (String className : options.classNames()) {
      String dataName = className + "_" + PARAM_POSITIVE_EXAMPLES;
      RequestBody requestBody =
          RequestBody.create(HttpMediaType.BINARY_FILE, options.positiveExamplesByClassName(className));
      bodyBuilder.addFormDataPart(dataName, options.positiveExamplesByClassName(className).getName(), requestBody);
    }

    if (options.negativeExamples() != null) {
      RequestBody requestBody = RequestBody.create(HttpMediaType.BINARY_FILE, options.negativeExamples());
      bodyBuilder.addFormDataPart(PARAM_NEGATIVE_EXAMPLES, options.negativeExamples().getName(), requestBody);
    }

    RequestBuilder requestBuilder = RequestBuilder.post(PATH_CLASSIFIERS);
    requestBuilder.query(VERSION, versionDate).body(bodyBuilder.build());

    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(VisualClassifier.class));
  }