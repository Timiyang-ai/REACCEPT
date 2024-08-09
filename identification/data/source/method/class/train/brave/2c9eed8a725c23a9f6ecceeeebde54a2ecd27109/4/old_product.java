public static Map<String, String> getAll(TraceContextOrSamplingFlags extracted) {
    if (extracted == null) throw new NullPointerException("extracted == null");
    return extracted.context() != null ? getAll(extracted.context()) : getAll(extracted.extra());
  }