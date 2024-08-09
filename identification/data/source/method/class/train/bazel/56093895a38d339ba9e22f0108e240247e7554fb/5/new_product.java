@VisibleForTesting
  public static Expression parseExpression(ParserInputSource input, EventHandler eventHandler) {
    return parseExpression(input, eventHandler, BUILD);
  }