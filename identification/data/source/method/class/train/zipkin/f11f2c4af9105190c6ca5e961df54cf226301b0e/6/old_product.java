static Set<String> annotationKeys(Span span) {
    Set<String> annotationKeys = new LinkedHashSet<>();
    span.annotations().forEach((a) -> annotationKeys.add(a.value()));

    for (Map.Entry<String,String> tag : span.tags().entrySet()) {
      if (tag.getValue().length() > LONGEST_VALUE_TO_INDEX) continue;

      // Using colon to allow allow annotation query search to work on key
      annotationKeys.add(tag.getKey());
      annotationKeys.add(tag.getKey() + ":" + tag.getValue());
    }
    return annotationKeys;
  }