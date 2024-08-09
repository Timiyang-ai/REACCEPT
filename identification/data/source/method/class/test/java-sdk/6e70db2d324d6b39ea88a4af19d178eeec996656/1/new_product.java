public ServiceCall<Classifier> createClassifier(CreateClassifierOptions createClassifierOptions) {
    Validator.notNull(createClassifierOptions, "createClassifierOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v1/classifiers");
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    RequestBody trainingMetadataBody = RequestUtils.inputStreamBody(createClassifierOptions.metadata(),
        "application/json");
    multipartBuilder.addFormDataPart("training_metadata", createClassifierOptions.metadataFilename(),
        trainingMetadataBody);
    RequestBody trainingDataBody = RequestUtils.inputStreamBody(createClassifierOptions.trainingData(), "text/csv");
    multipartBuilder.addFormDataPart("training_data", createClassifierOptions.trainingDataFilename(), trainingDataBody);
    builder.body(multipartBuilder.build());
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Classifier.class));
  }