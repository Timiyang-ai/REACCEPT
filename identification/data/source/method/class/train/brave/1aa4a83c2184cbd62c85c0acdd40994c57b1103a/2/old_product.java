@Nullable public static String current(String name) {
    TraceContext context = currentTraceContext();
    return context != null ? get(context, name) : null;
  }