public static ParseResult parseFile(
      ParserInputSource input, EventHandler eventHandler, ParsingMode parsingMode) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler, parsingMode);
    List<Statement> statements = parser.parseFileInput();
    return new ParseResult(
        statements,
        parser.comments,
        locationFromStatements(lexer, statements),
        parser.errorsCount > 0 || lexer.containsErrors());
  }