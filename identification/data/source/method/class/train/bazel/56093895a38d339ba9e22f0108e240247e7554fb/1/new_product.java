static Expression parseExpression(ParserInput input) throws SyntaxError {
    List<Event> errors = new ArrayList<>();
    Lexer lexer = new Lexer(input, errors);
    Parser parser = new Parser(lexer, errors);
    Expression result = parser.parseExpression();
    while (parser.token.kind == TokenKind.NEWLINE) {
      parser.nextToken();
    }
    parser.expect(TokenKind.EOF);
    if (!errors.isEmpty()) {
      throw new SyntaxError(errors);
    }
    return result;
  }