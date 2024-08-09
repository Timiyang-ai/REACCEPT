public static ParseResult parseFile(ParserInputSource input, EventHandler eventHandler) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler);
    List<Statement> statements = parser.parseFileInput();
    boolean errors = parser.errorsCount > 0 || lexer.containsErrors();
    return new ParseResult(
        statements, lexer.getComments(), locationFromStatements(lexer, statements), errors);
  }