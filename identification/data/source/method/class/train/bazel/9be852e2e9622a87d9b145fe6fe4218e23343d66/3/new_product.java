public static ParseResult parseFile(
      Lexer lexer, EventHandler eventHandler, CachingPackageLocator locator, boolean parsePython) {
    ParsingMode parsingMode = parsePython ? PYTHON : BUILD;
    Parser parser = new Parser(lexer, eventHandler, locator, parsingMode);
    List<Statement> statements = parser.parseFileInput();
    return new ParseResult(
        statements, parser.comments, parser.errorsCount > 0 || lexer.containsErrors());
  }