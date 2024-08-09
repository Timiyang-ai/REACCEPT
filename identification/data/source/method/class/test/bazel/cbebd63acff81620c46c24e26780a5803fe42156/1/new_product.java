@VisibleForTesting
  public static Statement parseStatement(
      Lexer lexer, EventHandler eventHandler) {
    Parser parser = new Parser(lexer, eventHandler, null);
    Statement result = parser.parseSmallStatement();
    while (parser.token.kind == TokenKind.NEWLINE) {
      parser.nextToken();
    }
    parser.expect(TokenKind.EOF);
    return result;
  }