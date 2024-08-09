static List<Statement> parseStatements(ParserInput input, EventHandler eventHandler) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler);
    List<Statement> result = new ArrayList<>();
    parser.parseStatement(result);
    while (parser.token.kind == TokenKind.NEWLINE) {
      parser.nextToken();
    }
    parser.expect(TokenKind.EOF);
    return result;
  }