private AstRoot parseNext() {
    switch (skipWS()) {
      case '(':  return parseFunctionApplication();
      case '{':  return parseFunctionDefinition();
      case '[':  return parseList();
      case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
        return new AstNum(number());
      case '-':  return (peek(1)>='0' && peek(1) <='9') ? new AstNum(number()) : new AstId(token());
      case '\"': case '\'':
        return new AstStr(string());
      case ' ':  throw new IllegalASTException("Expected an expression but ran out of text");
      default:  return new AstId(token());
    }
  }