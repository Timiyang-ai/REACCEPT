public static ParseResult parseFile(
      ParserInputSource input, EventHandler eventHandler, Dialect dialect) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler, dialect);
    List<Statement> statements = parser.parseFileInput();
    return new ParseResult(
        statements,
        parser.comments,
        locationFromStatements(lexer, statements),
        parser.errorsCount > 0 || lexer.containsErrors());
  }