public ServiceCall<Void> addCorpus(String customizationId, String corpusName, File corpusFile,
      Boolean allowOverwrite) {
    Validator.notNull(customizationId, "customizationId cannot be null");
    Validator.notNull(corpusName, "corpusName cannot be null");
    Validator.isTrue((corpusFile != null) && corpusFile.exists(), "corpusFile is null or does not exist");
    RequestBuilder requestBuilder = RequestBuilder.post(String.format(PATH_CORPUS, customizationId, corpusName));
    if (allowOverwrite != null) {
      requestBuilder.query(ALLOW_OVERWRITE, allowOverwrite);
    }

    requestBuilder.body(RequestBody.create(HttpMediaType.TEXT, corpusFile));
    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getVoid());
  }