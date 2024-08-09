static Set<String> annotationKeys(V1Span span) {
    Set<String> annotationKeys = new LinkedHashSet<>();
    for (V1Annotation a : span.annotations()) {
      // don't index core annotations as they aren't queryable
      if (CORE_ANNOTATIONS.contains(a.value())) continue;

      if (a.endpoint() != null && a.endpoint().serviceName() != null) {
        annotationKeys.add(a.endpoint().serviceName() + ":" + a.value());
      }
    }
    for (V1BinaryAnnotation b : span.binaryAnnotations()) {
      if (b.stringValue() != null
          && b.endpoint() != null
          && b.endpoint().serviceName() != null
          && b.stringValue().length() <= LONGEST_VALUE_TO_INDEX) {
        String value = b.stringValue();
        if (value.length() > LONGEST_VALUE_TO_INDEX) continue;

        annotationKeys.add(b.endpoint().serviceName() + ":" + b.key());
        annotationKeys.add(b.endpoint().serviceName() + ":" + b.key() + ":" + value);
      }
    }
    return annotationKeys;
  }