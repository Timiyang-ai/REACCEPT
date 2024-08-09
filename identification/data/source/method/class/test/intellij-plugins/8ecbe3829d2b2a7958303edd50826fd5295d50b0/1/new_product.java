@NotNull
  @NonNls
  static String trimActionPath(@NotNull @NonNls final String attributeValue) {
    final int bangIndex = attributeValue.indexOf(BANG_SYMBOL);
    if (bangIndex == -1) {
      return attributeValue;
    }

    return attributeValue.substring(0, bangIndex);
  }