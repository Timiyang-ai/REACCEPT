private void parseStatement(List<Statement> list, ParsingLevel parsingLevel) {
    if (token.kind == TokenKind.DEF && dialect == SKYLARK) {
      if (parsingLevel == ParsingLevel.LOCAL_LEVEL) {
        reportError(lexer.createLocation(token.left, token.right),
            "nested functions are not allowed. Move the function to top-level");
      }
      parseFunctionDefStatement(list);
    } else if (token.kind == TokenKind.IF && dialect == SKYLARK) {
      list.add(parseIfStatement());
    } else if (token.kind == TokenKind.FOR && dialect == SKYLARK) {
      if (parsingLevel == ParsingLevel.TOP_LEVEL) {
        reportError(
            lexer.createLocation(token.left, token.right),
            "for loops are not allowed on top-level. Put it into a function");
      }
      parseForStatement(list);
    } else if (BLOCK_STARTING_SET.contains(token.kind)) {
      skipBlock();
    } else {
      parseSimpleStatement(list);
    }
  }