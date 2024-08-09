  private static void parse_err(String expr) {
    try {
      Rapids.parse(expr);
      fail("Expression " + expr + " expected to fail, however it did not.");
    } catch (IllegalASTException ignored) {}
  }