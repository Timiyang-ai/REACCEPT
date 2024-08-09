@VisibleForTesting
  public static Statement parseStatement(
      Lexer lexer, EventHandler eventHandler) {
    return new Parser(lexer, eventHandler, null).parseSmallStatement();
  }