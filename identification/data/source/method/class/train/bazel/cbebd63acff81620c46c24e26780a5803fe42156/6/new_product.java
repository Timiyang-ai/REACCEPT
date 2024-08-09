@VisibleForTesting
  static Statement parseStatement(ParserInput input, EventHandler eventHandler) {
    List<Statement> stmts = parseStatements(input, eventHandler);
    return Iterables.getOnlyElement(stmts);
  }