  private Statement parseStatement(String... lines) {
    return Iterables.getOnlyElement(parseStatements(lines));
  }