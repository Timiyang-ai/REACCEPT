public static Resource create(
      @javax.annotation.Nullable String type, Map<String, String> labels) {
    return createInternal(
        type,
        Collections.unmodifiableMap(
            new LinkedHashMap<String, String>(Utils.checkNotNull(labels, "labels"))));
  }