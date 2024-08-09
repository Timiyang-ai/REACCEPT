public static boolean isDynamicExpression(@NotNull @NonNls final String attributeValue) {
    return StringUtil.startsWith(attributeValue, OgnlLanguage.EXPRESSION_PREFIX) ||
        StringUtil.containsChar(attributeValue, '{');
  }