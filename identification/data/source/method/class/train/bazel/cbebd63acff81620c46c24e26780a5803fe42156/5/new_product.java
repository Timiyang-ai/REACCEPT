@VisibleForTesting
  static Statement parseStatement(ParserInputSource input, EventHandler eventHandler) {
    List<Statement> stmts = parseStatements(input, eventHandler);
    return Iterables.getOnlyElement(stmts);
  }