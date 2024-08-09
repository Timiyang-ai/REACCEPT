static @Nullable String annotationQuery(Span span) {
    if (span.annotations().isEmpty() && span.tags().isEmpty()) return null;

    char delimiter = '░'; // as very unlikely to be in the query
    StringBuilder result = new StringBuilder().append(delimiter);
    for (Annotation a : span.annotations()) {
      if (a.value().length() > LONGEST_VALUE_TO_INDEX) continue;

      result.append(a.value()).append(delimiter);
    }

    for (Map.Entry<String, String> tag : span.tags().entrySet()) {
      if (tag.getValue().length() > LONGEST_VALUE_TO_INDEX) continue;

      result.append(tag.getKey()).append(delimiter); // search is possible by key alone
      result.append(tag.getKey() + "=" + tag.getValue()).append(delimiter);
    }
    return result.length() == 1 ? null : result.toString();
  }