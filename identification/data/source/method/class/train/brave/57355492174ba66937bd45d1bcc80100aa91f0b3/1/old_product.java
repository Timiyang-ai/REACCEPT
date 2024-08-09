@Nullable public static String get(TraceContext context, String name) {
    if (context == null) throw new NullPointerException("context == null");
    if (name == null) throw new NullPointerException("name == null");
    name = name.toLowerCase(Locale.ROOT); // since not all propagation handle mixed case
    for (Object extra : context.extra()) {
      if (extra instanceof Extra) return ((Extra) extra).get(name);
    }
    return null;
  }