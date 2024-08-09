static Set<String> annotationKeys(Span span) {
    Set<String> annotationKeys = new LinkedHashSet<>();
    String localServiceName = span.localServiceName();
    if (localServiceName == null) return annotationKeys;
    for (Annotation a : span.annotations()) {
      if (a.value().length() > SHORT_STRING_LENGTH) continue;

      // don't index core annotations as they aren't queryable
      if (CORE_ANNOTATIONS.contains(a.value())) continue;
      annotationKeys.add(localServiceName + ":" + a.value());
    }
    for (Map.Entry<String, String> e : span.tags().entrySet()) {
      if (e.getValue().length() > SHORT_STRING_LENGTH) continue;

      annotationKeys.add(localServiceName + ":" + e.getKey());
      annotationKeys.add(localServiceName + ":" + e.getKey() + ":" + e.getValue());
    }
    return annotationKeys;
  }