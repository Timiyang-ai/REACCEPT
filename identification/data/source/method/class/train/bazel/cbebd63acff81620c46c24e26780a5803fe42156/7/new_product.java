private void parseStatement(List<Statement> list, boolean isTopLevel) {
    if (token.kind == TokenKind.DEF && skylarkMode) {
      if (!isTopLevel) {
        reportError(lexer.createLocation(token.left, token.right),
            "nested functions are not allowed. Move the function to top-level");
      }
      parseFunctionDefStatement(list);
    } else if (token.kind == TokenKind.IF && skylarkMode) {
      list.add(parseIfStatement());
    } else if (token.kind == TokenKind.FOR && skylarkMode) {
      if (isTopLevel) {
        reportError(lexer.createLocation(token.left, token.right),
            "for loops are not allowed on top-level. Put it into a function");
      }
      parseForStatement(list);
    } else if (token.kind == TokenKind.IF
        || token.kind == TokenKind.ELSE
        || token.kind == TokenKind.FOR
        || token.kind == TokenKind.CLASS
        || token.kind == TokenKind.DEF
        || token.kind == TokenKind.TRY) {
      skipBlock();
    } else {
      parseSimpleStatement(list);
    }
  }