public Set<Field> getFields(EndPoint endPoint, long start, long end, @Nullable String prefix,
                              boolean includeCurrent) throws IOException {

    Set<String> lineageFields = fieldLineageReader.getFields(endPoint, start, end);

    Set<Field> result = createFields(lineageFields, true);
    if (includeCurrent) {
      result.addAll(createFields(getFieldsWithNoFieldLineage(endPoint, lineageFields), false));
    }
    return Strings.isNullOrEmpty(prefix) ? Collections.unmodifiableSet(result) :
      Collections.unmodifiableSet(filter(prefix, result));
  }