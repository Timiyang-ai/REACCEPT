public Set<Field> getFields(EndPoint endPoint, long start, long end, @Nullable String prefix,
                              boolean includeCurrent) throws IOException {

    Set<String> lineageFields = fieldLineageReader.getFields(endPoint, start, end);

    Set<Field> result = createFields(lineageFields, true);
    if (includeCurrent) {
      // get the system properties of this dataset
      Map<String, String> properties = metadataAdmin
        .getProperties(MetadataScope.SYSTEM, MetadataEntity.ofDataset(endPoint.getNamespace(), endPoint.getName()));
      // the system metadata contains the schema of the dataset which is written by the DatasetSystemMetadataWriter
      if (properties.containsKey(MetadataConstants.SCHEMA_KEY)) {
        String schema = properties.get(MetadataConstants.SCHEMA_KEY);
        Schema sc = Schema.parseJson(schema);
        if (sc.getFields() != null) {
          Set<String> schemaFields = sc.getFields().stream().map(Schema.Field::getName).collect(Collectors.toSet());
          // Sets.difference will return all the fields which are present in schemaFields and not present in lineage
          // fields. If there are common fields then they will not be present in the difference and will be treated
          // as lineage fields containing lineage information.
          ImmutableSet<String> dsOnlyFields = Sets.difference(schemaFields, lineageFields).immutableCopy();
          result.addAll(createFields(dsOnlyFields, false));
        }
      } else {
        LOG.trace("Received request to include schema fields for {} but no schema was found. Only fields present in " +
                    "the lineage store will be returned.", endPoint);
      }
    }
    return Strings.isNullOrEmpty(prefix) ? Collections.unmodifiableSet(result) :
      Collections.unmodifiableSet(filter(prefix, result));
  }