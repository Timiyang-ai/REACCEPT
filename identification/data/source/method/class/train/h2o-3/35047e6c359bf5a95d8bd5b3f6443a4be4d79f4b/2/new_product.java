public static AstRoot parse(String rapids) {
    Rapids r = new Rapids(rapids);
    AstRoot res = r.parseNext();
    if (r.skipWS() != ' ')
      throw new IllegalASTException("Syntax error: illegal Rapids expression `" + rapids + "`");
    return res;
  }