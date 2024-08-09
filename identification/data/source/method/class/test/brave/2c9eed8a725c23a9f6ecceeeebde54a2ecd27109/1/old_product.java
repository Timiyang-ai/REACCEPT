public static Map<String, String> getAll(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    PropagationFields fields = context.findExtra(Extra.class);
    return fields != null ? fields.toMap() : Collections.emptyMap();
  }