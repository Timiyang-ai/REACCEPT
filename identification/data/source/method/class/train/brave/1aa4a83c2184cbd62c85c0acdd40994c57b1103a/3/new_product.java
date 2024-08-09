public static void set(String name, String value) {
    TraceContext context = currentTraceContext();
    if (context != null) set(context, name, value);
  }