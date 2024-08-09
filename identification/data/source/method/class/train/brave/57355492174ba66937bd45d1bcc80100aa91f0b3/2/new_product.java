@Nullable public static String get(TraceContext context, String name) {
    return PropagationFields.get(context, lowercase(name), Extra.class);
  }