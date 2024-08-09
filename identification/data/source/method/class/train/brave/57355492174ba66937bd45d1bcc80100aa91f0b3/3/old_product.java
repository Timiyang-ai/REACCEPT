@Nullable public static String get(TraceContext context, String name) {
    if (context == null) throw new NullPointerException("context == null");
    if (name == null) throw new NullPointerException("name == null");
    Extra extra = findExtra(context.extra());
    if (extra == null) return null;
    int index = extra.indexOf(name.toLowerCase(Locale.ROOT));
    return index != -1 ? extra.get(index) : null;
  }