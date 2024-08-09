public ServiceCall<SpeechResults> recognize(File audio, String contentType, RecognizeOptions options) {
    RecognizeOptions opt = options;
    if (opt == null)
      opt = new RecognizeOptions().contentType(contentType);

    return recognize(audio, opt);
  }