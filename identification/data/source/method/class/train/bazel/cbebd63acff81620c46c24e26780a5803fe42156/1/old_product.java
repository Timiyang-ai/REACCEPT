private void parseStatement(List<Statement> list, ParsingLevel parsingLevel) {
    if (token.kind == TokenKind.DEF) {
      if (parsingLevel == ParsingLevel.LOCAL_LEVEL) {
        reportError(
            lexer.createLocation(token.left, token.right),
            "nested functions are not allowed. Move the function to top-level");
      }
      parseFunctionDefStatement(list);
    } else if (token.kind == TokenKind.IF) {
      list.add(parseIfStatement());
    } else if (token.kind == TokenKind.FOR) {
      if (parsingLevel == ParsingLevel.TOP_LEVEL) {
        reportError(
            lexer.createLocation(token.left, token.right),
            "for loops are not allowed on top-level. Put it into a function");
      }
      parseForStatement(list);
    } else {
      parseSimpleStatement(list);
    }
  }