private Expression parseExpression() {
    int start = token.left;
    Expression expression = parseNonTupleExpression();
    if (token.kind != TokenKind.COMMA) {
      return expression;
    }

    // It's a tuple
    List<Expression> tuple = parseExprList();
    tuple.add(0, expression);  // add the first expression to the front of the tuple
    return setLocation(ListLiteral.makeTuple(tuple), start, token.right);
  }