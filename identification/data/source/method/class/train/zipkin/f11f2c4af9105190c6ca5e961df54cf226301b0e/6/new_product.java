static Set<String> annotationKeys(Span span) {
    if (span.annotations().isEmpty() && span.tags().isEmpty()) return Collections.emptySet();

    Set<String> result = null;
    for (Annotation a : span.annotations()) {
      if (a.value().length() > LONGEST_VALUE_TO_INDEX) continue;

      if (result == null) result = new LinkedHashSet<>();

      result.add(a.value());
    }

    for (Map.Entry<String,String> tag : span.tags().entrySet()) {
      if (tag.getValue().length() > LONGEST_VALUE_TO_INDEX) continue;

      if (result == null) result = new LinkedHashSet<>();

      result.add(tag.getKey());
      // Using colon to allow allow annotation query search to work on key
      result.add(tag.getKey() + ":" + tag.getValue());
    }
    return result != null ? result : Collections.emptySet();
  }