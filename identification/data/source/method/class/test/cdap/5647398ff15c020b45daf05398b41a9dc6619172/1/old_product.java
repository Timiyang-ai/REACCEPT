public Set<Field> getFields(EndPoint endPoint, long start, long end, @Nullable String prefix,
                              boolean includeDatasetSchema) {

    Set<String> fields = fieldLineageReader.getFields(endPoint, start, end);
    // TODO (Rohit CDAP-14168) Look up dataset schema here and union it with the above with lineage info as false
    if (!Strings.isNullOrEmpty(prefix)) {
      fields = filter(prefix, fields);
    }
    return createFields(fields, true);
  }