public static <T> void parse(String raw, SpiQuery<T> query) {

    EQLParser parser = new EQLParser(new CommonTokenStream(new EQLLexer(CharStreams.fromString(raw))));
    parser.addErrorListener(errorListener);

    new ParseTreeWalker().walk(new EqlAdapter<>(query), parser.select_statement());
    query.simplifyExpressions();
  }