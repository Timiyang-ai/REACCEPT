public static Ast parse(String rapids) {
    Rapids r = new Rapids(rapids);
    Ast res = r.parseNext();
    if (r.skipWS() != ' ')
      throw new IllegalASTException("Syntax error: illegal Rapids expression `" + rapids + "`");
    return res;
  }