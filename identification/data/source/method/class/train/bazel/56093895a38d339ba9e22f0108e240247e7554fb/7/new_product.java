private Expression parseExpression(boolean insideParens) {
    int start = token.left;
    Expression expression = parseNonTupleExpression();
    if (token.kind != TokenKind.COMMA) {
      return expression;
    }

    // It's a tuple
    List<Expression> tuple = parseExprList(insideParens);
    tuple.add(0, expression); // add the first expression to the front of the tuple
    return setLocation(
        new ListExpression(/*isTuple=*/ true, tuple), start, Iterables.getLast(tuple));
  }