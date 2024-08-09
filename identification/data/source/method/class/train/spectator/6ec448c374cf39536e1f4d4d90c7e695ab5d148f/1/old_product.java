public DataExpr dataExpr() {
    if (expr == null) {
      expr = Parser.parseDataExpr(expression);
    }
    return expr;
  }