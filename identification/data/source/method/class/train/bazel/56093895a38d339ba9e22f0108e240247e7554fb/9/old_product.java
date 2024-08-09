private Expression parseExpression() {
    int start = token.left;
    Expression expr = parseExpression(0);
    if (token.kind == TokenKind.IF) {
      nextToken();
      Expression condition = parseExpression(0);
      if (token.kind == TokenKind.ELSE) {
        nextToken();
        Expression elseClause = parseExpression();
        return setLocation(new ConditionalExpression(expr, condition, elseClause),
            start, elseClause);
      } else {
        reportError(lexer.createLocation(start, token.left),
            "missing else clause in conditional expression or semicolon before if");
        return expr; // Try to recover from error: drop the if and the expression after it. Ouch.
      }
    }
    return expr;
  }