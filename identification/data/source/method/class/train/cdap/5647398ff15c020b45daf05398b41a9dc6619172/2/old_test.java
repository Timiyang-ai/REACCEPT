  private Set<Field> getFields(Set<String> fieldNames) {
    return fieldNames.stream().map(fieldName -> new Field(fieldName, true)).collect(Collectors.toSet());
  }