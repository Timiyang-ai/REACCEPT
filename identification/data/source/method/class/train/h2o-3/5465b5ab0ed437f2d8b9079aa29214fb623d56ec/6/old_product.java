public static Val exec(String rapids, Session ses) {
    AST ast = Rapids.parse(rapids);
    // Synchronize the session, to stop back-to-back overlapping Rapids calls
    // on the same session, which Flow sometimes does
    synchronized (ses) {
      Val val = ses.exec(ast, null);
      // Any returned Frame has it's REFCNT raised by +1, but is exiting the
      // session.  If it's a global, we simply need to lower the internal refcnts
      // (which won't delete on zero cnts because of the global).  If it's a
      // named temp, the ref cnts are accounted for by being in the temp table.
      if (val.isFrame()) {
        Frame fr = val.getFrame();
        assert fr._key != null; // No nameless Frame returns, as these are hard to cleanup
        if (ses.FRAMES.containsKey(fr)) {
          throw water.H2O.unimpl();
        } else {
          ses.addRefCnt(fr, -1);
        }
      }
      return val;
    }
  }