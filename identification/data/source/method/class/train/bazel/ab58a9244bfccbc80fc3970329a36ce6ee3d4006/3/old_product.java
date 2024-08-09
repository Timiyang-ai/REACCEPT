public static List<Statement> parseStatements(
      ParserInputSource input, EventHandler eventHandler, ParsingLevel parsingLevel) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler);
    List<Statement> result = new ArrayList<>();
    parser.parseStatement(result, parsingLevel);
    while (parser.token.kind == TokenKind.NEWLINE) {
      parser.nextToken();
    }
    parser.expect(TokenKind.EOF);
    return result;
  }