public ServiceCall<Void> addCorpus(AddCorpusOptions addCorpusOptions) {
    Validator.notNull(addCorpusOptions, "addCorpusOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/customizations/%s/corpora/%s", addCorpusOptions
        .customizationId(), addCorpusOptions.corpusName()));
    if (addCorpusOptions.allowOverwrite() != null) {
      builder.query("allow_overwrite", String.valueOf(addCorpusOptions.allowOverwrite()));
    }
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    RequestBody corpusFileBody = RequestUtils.inputStreamBody(addCorpusOptions.corpusFile(), addCorpusOptions
        .corpusFileContentType());
    multipartBuilder.addFormDataPart("corpus_file", addCorpusOptions.corpusFilename(), corpusFileBody);
    builder.body(multipartBuilder.build());
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }