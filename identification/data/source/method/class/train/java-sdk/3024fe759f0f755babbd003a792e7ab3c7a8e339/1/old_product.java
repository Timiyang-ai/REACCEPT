public ServiceCall<SpeechRecognitionResults> recognize(RecognizeOptions recognizeOptions) {
    Validator.notNull(recognizeOptions, "recognizeOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v1/recognize");
    if (recognizeOptions.transferEncoding() != null) {
      builder.header("Transfer-Encoding", recognizeOptions.transferEncoding());
    }
    if (recognizeOptions.contentType() != null) {
      builder.header("Content-Type", recognizeOptions.contentType());
    }
    if (recognizeOptions.model() != null) {
      builder.query("model", recognizeOptions.model());
    }
    if (recognizeOptions.customizationId() != null) {
      builder.query("customization_id", recognizeOptions.customizationId());
    }
    if (recognizeOptions.acousticCustomizationId() != null) {
      builder.query("acoustic_customization_id", recognizeOptions.acousticCustomizationId());
    }
    if (recognizeOptions.customizationWeight() != null) {
      builder.query("customization_weight", String.valueOf(recognizeOptions.customizationWeight()));
    }
    if (recognizeOptions.version() != null) {
      builder.query("version", recognizeOptions.version());
    }
    if (recognizeOptions.inactivityTimeout() != null) {
      builder.query("inactivity_timeout", String.valueOf(recognizeOptions.inactivityTimeout()));
    }
    if (recognizeOptions.keywords() != null) {
      builder.query("keywords", RequestUtils.join(recognizeOptions.keywords(), ","));
    }
    if (recognizeOptions.keywordsThreshold() != null) {
      builder.query("keywords_threshold", String.valueOf(recognizeOptions.keywordsThreshold()));
    }
    if (recognizeOptions.maxAlternatives() != null) {
      builder.query("max_alternatives", String.valueOf(recognizeOptions.maxAlternatives()));
    }
    if (recognizeOptions.wordAlternativesThreshold() != null) {
      builder.query("word_alternatives_threshold", String.valueOf(recognizeOptions
          .wordAlternativesThreshold()));
    }
    if (recognizeOptions.wordConfidence() != null) {
      builder.query("word_confidence", String.valueOf(recognizeOptions.wordConfidence()));
    }
    if (recognizeOptions.timestamps() != null) {
      builder.query("timestamps", String.valueOf(recognizeOptions.timestamps()));
    }
    if (recognizeOptions.profanityFilter() != null) {
      builder.query("profanity_filter", String.valueOf(recognizeOptions.profanityFilter()));
    }
    if (recognizeOptions.smartFormatting() != null) {
      builder.query("smart_formatting", String.valueOf(recognizeOptions.smartFormatting()));
    }
    if (recognizeOptions.speakerLabels() != null) {
      builder.query("speaker_labels", String.valueOf(recognizeOptions.speakerLabels()));
    }
    if (recognizeOptions.audio() != null) {
      builder.body(RequestBody.create(MediaType.parse(recognizeOptions.contentType()),
          recognizeOptions.audio()));
    }
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    if (recognizeOptions.metadata() != null) {
      multipartBuilder.addFormDataPart("metadata", GsonSingleton.getGson().toJson(recognizeOptions.metadata()));
      if (recognizeOptions.upload() != null) {
        RequestBody uploadBody = RequestUtils.inputStreamBody(recognizeOptions.upload(),
            recognizeOptions.uploadContentType());
        multipartBuilder.addFormDataPart("upload", recognizeOptions.uploadFilename(), uploadBody);
      }
      builder.body(multipartBuilder.build());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(SpeechRecognitionResults.class));
  }