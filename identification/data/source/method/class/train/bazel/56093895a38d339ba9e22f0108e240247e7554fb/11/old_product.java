public static Expression parseExpression(
      ParserInputSource input, EventHandler eventHandler, Dialect dialect) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler, dialect);
    Expression result = parser.parseExpression();
    while (parser.token.kind == TokenKind.NEWLINE) {
      parser.nextToken();
    }
    parser.expect(TokenKind.EOF);
    return result;
  }