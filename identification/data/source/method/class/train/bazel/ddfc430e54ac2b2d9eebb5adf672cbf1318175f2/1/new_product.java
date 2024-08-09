public static boolean getUsesSharedLibraries(StructImpl info) throws EvalException {
    Object fieldValue = getValue(info, USES_SHARED_LIBRARIES);
    return SkylarkType.cast(
        fieldValue,
        Boolean.class,
        null,
        "'%s' provider's '%s' field should be a boolean (got a '%s')",
        PROVIDER_NAME,
        USES_SHARED_LIBRARIES,
        EvalUtils.getDataTypeName(fieldValue, /*fullDetails=*/ true));
  }