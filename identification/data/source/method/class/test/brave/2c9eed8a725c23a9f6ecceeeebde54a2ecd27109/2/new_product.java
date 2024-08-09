public static Map<String, String> getAll(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    return getAll(context.extra());
  }