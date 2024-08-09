public static ParseResult parseFile(ParserInputSource input, EventHandler eventHandler) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler, BUILD);
    List<Statement> statements = parser.parseFileInput();
    return new ParseResult(statements, parser.comments, locationFromStatements(lexer, statements),
        parser.errorsCount > 0 || lexer.containsErrors());
  }