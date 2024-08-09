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
    return castValue.getSet(String.class);
  }