  private static Expression parseExpression(String... lines) throws SyntaxError {
    ParserInput input = ParserInput.fromLines(lines);
    return Expression.parse(input);
  }