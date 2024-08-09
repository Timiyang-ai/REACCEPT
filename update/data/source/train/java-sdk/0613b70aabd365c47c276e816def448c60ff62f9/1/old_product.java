public ServiceCall<SpeechRecognitionResults> recognize(RecognizeOptions recognizeOptions) {
    Validator.notNull(recognizeOptions, "recognizeOptions cannot be null");
    Validator.isTrue(((recognizeOptions.audio() != null
            && recognizeOptions.upload() == null)
            || (recognizeOptions.audio() == null
            && recognizeOptions.upload() != null)),
        "Exactly one of audio or upload must be supplied.");
    RequestBuilder builder = RequestBuilder.post("/v1/recognize");
    builder.header("Content-Type", recognizeOptions.contentType());
    if (recognizeOptions.transferEncoding() != null) {
      builder.header("Transfer-Encoding", recognizeOptions.transferEncoding());
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
      builder.query("customization_weight", recognizeOptions.customizationWeight());
    }
    if (recognizeOptions.version() != null) {
      builder.query("version", recognizeOptions.version());
    }
    if (recognizeOptions.inactivityTimeout() != null) {
      builder.query("inactivity_timeout", recognizeOptions.inactivityTimeout());
    }
    if (recognizeOptions.keywords() != null) {
      builder.query("keywords", RequestUtils.join(recognizeOptions.keywords(), ","));
    }
    if (recognizeOptions.keywordsThreshold() != null) {
      builder.query("keywords_threshold", recognizeOptions.keywordsThreshold());
    }
    if (recognizeOptions.maxAlternatives() != null) {
      builder.query("max_alternatives", recognizeOptions.maxAlternatives());
    }
    if (recognizeOptions.wordAlternativesThreshold() != null) {
      builder.query("word_alternatives_threshold", recognizeOptions.wordAlternativesThreshold());
    }
    if (recognizeOptions.wordConfidence() != null) {
      builder.query("word_confidence", recognizeOptions.wordConfidence());
    }
    if (recognizeOptions.timestamps() != null) {
      builder.query("timestamps", recognizeOptions.timestamps());
    }
    if (recognizeOptions.profanityFilter() != null) {
      builder.query("profanity_filter", recognizeOptions.profanityFilter());
    }
    if (recognizeOptions.smartFormatting() != null) {
      builder.query("smart_formatting", recognizeOptions.smartFormatting());
    }
    if (recognizeOptions.speakerLabels() != null) {
      builder.query("speaker_labels", recognizeOptions.speakerLabels());
    }
    if (recognizeOptions.audio() != null) {
      builder.body(RequestBody.create(MediaType.parse(recognizeOptions.contentType()), recognizeOptions.audio()));
    }
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    if (recognizeOptions.upload() != null) {
      RequestBody fileBody = RequestUtils.inputStreamBody(recognizeOptions.upload(), recognizeOptions
          .contentType());
      multipartBuilder.addFormDataPart("upload", recognizeOptions.uploadFilename(), fileBody);
      if (recognizeOptions.metadata() != null) {
        multipartBuilder.addFormDataPart("metadata", GsonSingleton.getGson().toJson(recognizeOptions.metadata()));
      }
      builder.body(multipartBuilder.build());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(SpeechRecognitionResults.class));
  }