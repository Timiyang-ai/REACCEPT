public static NestedSet<Artifact> getTransitiveSources(StructImpl info) throws EvalException {
    Object fieldValue = getValue(info, TRANSITIVE_SOURCES);
    SkylarkNestedSet castValue =
        SkylarkType.cast(
            fieldValue,
            SkylarkNestedSet.class,
            Artifact.class,
            null,
            "'%s' provider's '%s' field should be a depset of Files (got a '%s')",
            PROVIDER_NAME,
            TRANSITIVE_SOURCES,
            EvalUtils.getDataTypeNameFromClass(fieldValue.getClass()));
    return castValue.getSet(Artifact.class);
  }