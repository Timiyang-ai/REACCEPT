public static ParseResult parseFile(
      Lexer lexer, EventHandler eventHandler, CachingPackageLocator locator,
      boolean parsePython) {
    Parser parser = new Parser(lexer, eventHandler, locator, parsePython);
    List<Statement> statements = parser.parseFileInput();
    return new ParseResult(statements, parser.comments,
        parser.errorsCount > 0 || lexer.containsErrors());
  }