private void parseStatement(List<Statement> list) {
    // TODO(adonovan): factor the list.add() calls, in this function.
    if (token.kind == TokenKind.DEF) {
      parseFunctionDefStatement(list);
    } else if (token.kind == TokenKind.IF) {
      list.add(parseIfStatement());
    } else if (token.kind == TokenKind.FOR) {
      parseForStatement(list);
    } else if (token.kind == TokenKind.LOAD) {
      parseLoadStatement(list);
    } else {
      parseSimpleStatement(list);
    }
  }