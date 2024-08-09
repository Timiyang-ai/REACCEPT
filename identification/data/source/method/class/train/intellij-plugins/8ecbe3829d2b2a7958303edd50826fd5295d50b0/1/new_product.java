static boolean isDynamicExpression(@NotNull @NonNls final String attributeValue) {
    return attributeValue.startsWith("%{");
  }