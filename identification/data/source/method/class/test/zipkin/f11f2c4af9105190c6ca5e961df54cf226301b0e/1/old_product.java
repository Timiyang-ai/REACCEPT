static Set<String> annotationKeys(Span span) {
    Set<String> annotationKeys = new LinkedHashSet<>();
    String localServiceName = span.localServiceName();
    if (localServiceName == null) return Collections.emptySet();
    for (Annotation a : span.annotations()) {
      // don't index core annotations as they aren't queryable
      if (CORE_ANNOTATIONS.contains(a.value())) continue;
      annotationKeys.add(localServiceName + ":" + a.value());
    }
    for (Map.Entry<String, String> e : span.tags().entrySet()) {
      if (e.getValue().length() <= LONGEST_VALUE_TO_INDEX) {
        annotationKeys.add(localServiceName + ":" + e.getKey());
        annotationKeys.add(localServiceName + ":" + e.getKey() + ":" + e.getValue());
      }
    }
    return annotationKeys;
  }