public Set<String> getFields(EndPoint endPoint, long start, long end, @Nullable String prefix) {
    Set<String> fields = fieldLineageReader.getFields(endPoint, start, end);
    if (prefix == null) {
      return fields;
    }

    Set<String> prefixFields = new HashSet<>();
    for (String field : fields) {
      if (field.startsWith(prefix)) {
        prefixFields.add(field);
      }
    }
    return prefixFields;
  }