public static Statement parseStatement(
      ParserInputSource input, EventHandler eventHandler,
      ParsingLevel parsingLevel, Dialect dialect) {
    List<Statement> stmts = parseStatements(
        input, eventHandler, parsingLevel, dialect);
    return Iterables.getOnlyElement(stmts);
  }