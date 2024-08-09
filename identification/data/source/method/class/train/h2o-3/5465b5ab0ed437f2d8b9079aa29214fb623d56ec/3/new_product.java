public static Val exec(String rapids) {
    Session session = new Session();
    try {
      AstRoot ast = Rapids.parse(rapids);
      Val val = session.exec(ast, null);
      // Any returned Frame has it's REFCNT raised by +1, and the end(val) call
      // will account for that, copying Vecs as needed so that the returned
      // Frame is independent of the Session (which is disappearing).
      return session.end(val);
    } catch (Throwable ex) {
      throw session.endQuietly(ex);
    }
  }