@NotNull
  @NonNls
  public static String trimActionPath(@NotNull @NonNls final String attributeValue) {
    final int bangIndex = StringUtil.indexOf(attributeValue, BANG_SYMBOL);
    if (bangIndex == -1) {
      return attributeValue;
    }

    return attributeValue.substring(0, bangIndex);
  }