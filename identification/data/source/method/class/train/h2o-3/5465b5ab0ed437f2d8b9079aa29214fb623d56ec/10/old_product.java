@SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
  public static Val exec(String rapids, Session session) {
    AstRoot ast = Rapids.parse(rapids);
    // Synchronize the session, to stop back-to-back overlapping Rapids calls
    // on the same session, which Flow sometimes does
    synchronized (session) {
      Val val = session.exec(ast, null);
      // Any returned Frame has it's REFCNT raised by +1, but is exiting the
      // session.  If it's a global, we simply need to lower the internal refcnts
      // (which won't delete on zero cnts because of the global).  If it's a
      // named temp, the ref cnts are accounted for by being in the temp table.
      if (val.isFrame()) {
        Frame frame = val.getFrame();
        assert frame._key != null : "Returned frame has no key";
        session.addRefCnt(frame, -1);
      }
      return val;
    }
  }