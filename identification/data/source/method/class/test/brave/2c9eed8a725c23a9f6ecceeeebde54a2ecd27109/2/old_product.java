public static Map<String, String> getAll(TraceContext context) {
    if (context == null) throw new NullPointerException("context == null");
    Extra extra = findExtra(context.extra());
    if (extra == null) return Collections.emptyMap();
    String[] elements = extra.values;
    if (elements == null) return Collections.emptyMap();

    Map<String, String> result = new LinkedHashMap<>();
    for (int i = 0, length = elements.length; i<length; i++) {
      String value = elements[i];
      if (value != null) result.put(extra.fieldNames[i], value);
    }
    return result;
  }