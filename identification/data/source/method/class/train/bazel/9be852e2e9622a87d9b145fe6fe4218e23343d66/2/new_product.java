public static ParseResult parseFile(
      ParserInputSource input, EventHandler eventHandler, boolean parsePython) {
    Lexer lexer = new Lexer(input, eventHandler, parsePython);
    ParsingMode parsingMode = parsePython ? PYTHON : BUILD;
    Parser parser = new Parser(lexer, eventHandler, parsingMode);
    List<Statement> statements = parser.parseFileInput();
    return new ParseResult(statements, parser.comments, locationFromStatements(lexer, statements),
        parser.errorsCount > 0 || lexer.containsErrors());
  }