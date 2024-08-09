public static NestedSet<String> getImports(StructImpl info) throws EvalException {
    Object fieldValue = getValue(info, IMPORTS);
    SkylarkNestedSet castValue =
        SkylarkType.cast(
            fieldValue,
            SkylarkNestedSet.class,
            String.class,
            null,
            "'%s' provider's '%s' field should be a depset of strings (got a '%s')",
            PROVIDER_NAME,
            IMPORTS,
            EvalUtils.getDataTypeNameFromClass(fieldValue.getClass()));
    try {
      return castValue.getSet(String.class);
    } catch (SkylarkNestedSet.TypeException exception) {
      throw new EvalException(
          null,
          String.format(
              "expected field '%s' to be a depset of type 'file', but was a depset of type '%s'",
              IMPORTS, castValue.getContentType()),
          exception);
    }
  }