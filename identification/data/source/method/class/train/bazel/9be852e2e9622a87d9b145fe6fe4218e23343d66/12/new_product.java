public static ParseResult parseFile(
      ParserInputSource input, EventHandler eventHandler, Dialect dialect) {
    Lexer lexer = new Lexer(input, eventHandler);
    Parser parser = new Parser(lexer, eventHandler);
    List<Statement> statements = parser.parseFileInput();
    boolean errors = parser.errorsCount > 0 || lexer.containsErrors();
    // TODO(laurentlb): Remove dialect argument.
    if (dialect == Dialect.BUILD) {
      errors |= !ValidationEnvironment.checkBuildSyntax(statements, eventHandler);
    }
    return new ParseResult(
        statements, parser.comments, locationFromStatements(lexer, statements), errors);
  }