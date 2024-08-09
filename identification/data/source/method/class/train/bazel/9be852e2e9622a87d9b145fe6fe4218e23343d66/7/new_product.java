static ParseResult parseFile(ParserInput input) {
    List<Event> errors = new ArrayList<>();
    Lexer lexer = new Lexer(input, errors);
    Parser parser = new Parser(lexer, errors);
    List<Statement> statements;
    try (SilentCloseable c =
        Profiler.instance()
            .profile(ProfilerTask.STARLARK_PARSER, input.getPath().getPathString())) {
      statements = parser.parseFileInput();
    }
    return new ParseResult(
        statements,
        lexer.getComments(),
        locationFromStatements(lexer, statements),
        errors,
        lexer.getStringEscapeEvents());
  }