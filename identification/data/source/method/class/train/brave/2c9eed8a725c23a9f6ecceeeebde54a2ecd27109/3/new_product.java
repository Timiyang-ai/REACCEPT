public static Map<String, String> getAll(TraceContextOrSamplingFlags extracted) {
    if (extracted == null) throw new NullPointerException("extracted == null");
    TraceContext extractedContext = extracted.context();
    if (extractedContext != null) return getAll(extractedContext);
    Extra fields = TraceContext.findExtra(Extra.class, extracted.extra());
    return fields != null ? fields.toMap() : Collections.emptyMap();
  }