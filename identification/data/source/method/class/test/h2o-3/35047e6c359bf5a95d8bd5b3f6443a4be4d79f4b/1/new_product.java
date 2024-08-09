public AstRoot parse() {
    switch (skipWS()) {
      case '(':  return new AstExec(this); // function application
      case '{':  return new AstFunction(this);  // function definition
      case '#':  _x++;                     // Skip before double, FALL THRU
      case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
        return new AstNum(this);
      case '\"': return new AstStr(this,'\"');
      case '\'': return new AstStr(this,'\'');
      case '[':  return isQuote(xpeek('[').skipWS()) ? new AstStrList(this) : new AstNumList(this);
      case ' ':  throw new IllegalASTException("Expected an expression but ran out of text");
      case '-':  return (peek(1)>='0' && peek(1) <='9') ? new AstNum(this) : new AstId(this);
      default:  return new AstId(this);
    }    
  }