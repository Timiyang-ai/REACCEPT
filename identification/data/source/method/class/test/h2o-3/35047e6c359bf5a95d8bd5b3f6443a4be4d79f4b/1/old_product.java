public AST parse() {
    switch (skipWS()) {
      case '(':  return new ASTExec(this); // function application
      case '{':  return new ASTFun(this);  // function definition
      case '#':  _x++;                     // Skip before double, FALL THRU
      case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
        return new ASTNum(this);
      case '\"': return new ASTStr(this,'\"');
      case '\'': return new ASTStr(this,'\'');
      case '[':  return isQuote(xpeek('[').skipWS()) ? new ASTStrList(this) : new ASTNumList(this);
      case ' ':  throw new IllegalASTException("Expected an expression but ran out of text");
      case '-':  return (peek(1)>='0' && peek(1) <='9') ? new ASTNum(this) : new ASTId(this);
      default:  return new ASTId(this);
    }    
  }