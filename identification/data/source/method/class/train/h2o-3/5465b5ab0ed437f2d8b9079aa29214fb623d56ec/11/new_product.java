public static Val exec(String rapids) {
    Session ses = new Session();
    try {
      AstRoot ast = Rapids.parse(rapids);
      Val val = ses.exec(ast, null);
      // Any returned Frame has it's REFCNT raised by +1, and the end(val) call
      // will account for that, copying Vecs as needed so that the returned
      // Frame is independent of the Session (which is disappearing).
      return ses.end(val);
    } catch (Throwable ex) {
      throw ses.endQuietly(ex);
    }
  }