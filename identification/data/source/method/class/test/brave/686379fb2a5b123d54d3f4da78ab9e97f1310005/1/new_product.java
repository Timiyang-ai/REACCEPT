public static TraceContextOrSamplingFlags extract(String amznTraceId) {
    if (amznTraceId == null) return EMPTY;
    return STRING_EXTRACTOR.extract(amznTraceId);
  }