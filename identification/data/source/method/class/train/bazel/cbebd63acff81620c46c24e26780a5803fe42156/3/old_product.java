private void parseStatement(List<Statement> list) {
    if (token.kind == TokenKind.DEF) {
      list.add(parseFunctionDefStatement());
    } else if (token.kind == TokenKind.IF) {
      list.add(parseIfStatement());
    } else if (token.kind == TokenKind.FOR) {
      list.add(parseForStatement());
    } else if (token.kind == TokenKind.LOAD) {
      parseLoadStatement(list); // may add nothing
    } else {
      parseSimpleStatement(list);
    }
  }