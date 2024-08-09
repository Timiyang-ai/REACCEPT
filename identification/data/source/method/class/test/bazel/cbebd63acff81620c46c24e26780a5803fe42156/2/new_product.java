@VisibleForTesting
  public static Statement parseStatement(
      ParserInputSource input, EventHandler eventHandler, ParsingLevel parsingLevel) {
    List<Statement> stmts = parseStatements(input, eventHandler, parsingLevel);
    return Iterables.getOnlyElement(stmts);
  }