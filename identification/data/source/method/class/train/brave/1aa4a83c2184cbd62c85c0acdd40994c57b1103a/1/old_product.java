@Nullable public static String current(String name) {
    Tracing tracing = Tracing.current();
    if (tracing == null) return null;
    TraceContext context = tracing.currentTraceContext().get();
    if (context == null) return null;
    return get(context, name);
  }