public ServiceCall<Classifier> updateClassifier(UpdateClassifierOptions updateClassifierOptions) {
    Validator.notNull(updateClassifierOptions, "updateClassifierOptions cannot be null");
    Validator.isTrue((updateClassifierOptions.classNames() != null) || (updateClassifierOptions
        .negativeExamples() != null),
        "At least one of classnamePositiveExamples or negativeExamples must be supplied.");
    RequestBuilder builder = RequestBuilder.post(String.format("/v3/classifiers/%s", updateClassifierOptions
        .classifierId()));
    builder.query(VERSION, versionDate);
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    if (updateClassifierOptions.classNames() != null) {
      // Classes
      for (String className : updateClassifierOptions.classNames()) {
        String dataName = className + "_positive_examples";
        File positiveExamples = updateClassifierOptions.positiveExamplesByClassName(className);
        RequestBody body = RequestUtils.fileBody(positiveExamples, "application/octet-stream");
        multipartBuilder.addFormDataPart(dataName, positiveExamples.getName(), body);
      }
    }
    if (updateClassifierOptions.negativeExamples() != null) {
      RequestBody negativeExamplesBody = RequestUtils.inputStreamBody(updateClassifierOptions.negativeExamples(),
          "application/octet-stream");
      multipartBuilder.addFormDataPart("negative_examples", updateClassifierOptions.negativeExamplesFilename(),
          negativeExamplesBody);
    }
    builder.body(multipartBuilder.build());
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Classifier.class));
  }