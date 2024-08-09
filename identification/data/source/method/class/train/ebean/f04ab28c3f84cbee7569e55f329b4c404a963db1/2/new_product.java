public static <T> void parse(String raw, SpiQuery<T> query) {

    EQLLexer lexer = new EQLLexer(new ANTLRInputStream(raw));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    EQLParser parser = new EQLParser(tokens);
    parser.addErrorListener(errorListener);
    EQLParser.Select_statementContext context = parser.select_statement();

    EqlAdapter<T> adapter = new EqlAdapter<>(query);

    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(adapter, context);

    query.simplifyExpressions();
  }