public static TraceContextOrSamplingFlags extract(String amznTraceId) {
    if (amznTraceId == null) throw new NullPointerException("amznTraceId == null");
    return STRING_EXTRACTOR.extract(amznTraceId);
  }