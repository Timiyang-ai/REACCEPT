static Set<String> annotationKeys(Span span) {
    Set<String> annotationKeys = new LinkedHashSet<>();
    for (Annotation a : span.annotations) {
      // don't index core annotations as they aren't queryable
      if (Constants.CORE_ANNOTATIONS.contains(a.value)) continue;

      if (a.endpoint != null && !a.endpoint.serviceName.isEmpty()) {
        annotationKeys.add(a.endpoint.serviceName + ":" + a.value);
      }
    }
    for (BinaryAnnotation b : span.binaryAnnotations) {
      if (b.type != BinaryAnnotation.Type.STRING
          || b.endpoint == null
          || b.endpoint.serviceName.isEmpty()
          || b.value.length > LONGEST_VALUE_TO_INDEX * 4) { // UTF_8 is up to 4bytes/char
        continue;
      }
      String value = new String(b.value, UTF_8);
      if (value.length() > LONGEST_VALUE_TO_INDEX) continue;

      // Using colon to allow allow annotation query search to work on key
      annotationKeys.add(b.endpoint.serviceName + ":" + b.key);
      annotationKeys.add(b.endpoint.serviceName + ";" + b.key + ";" + new String(b.value, UTF_8));
    }
    return annotationKeys;
  }