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
            EvalUtils.getDataTypeName(fieldValue, /*fullDetails=*/ true));
    try {
      NestedSet<Artifact> unwrappedValue = castValue.getSet(Artifact.class);
      if (!unwrappedValue.getOrder().isCompatible(Order.COMPILE_ORDER)) {
        throw new EvalException(
            /*location=*/ null,
            String.format(
                "Incompatible depset order for 'transitive_sources': expected 'default' or "
                    + "'postorder', but got '%s'",
                unwrappedValue.getOrder().getSkylarkName()));
      }
      return unwrappedValue;
    } catch (SkylarkNestedSet.TypeException exception) {
      throw new EvalException(
          null,
          String.format(
              "expected field '%s' to be a depset of type 'file', but was a depset of type '%s'",
              TRANSITIVE_SOURCES, castValue.getContentType()),
          exception);
    }
  }