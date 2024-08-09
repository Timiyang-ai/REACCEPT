@VisibleForTesting
  public static Expression parseExpression(Lexer lexer, EventHandler eventHandler) {
    Parser parser = new Parser(lexer, eventHandler, null);
    Expression result = parser.parseExpression();
    while (parser.token.kind == TokenKind.NEWLINE) {
      parser.nextToken();
    }
    parser.expect(TokenKind.EOF);
    return result;
  }