private Item copy(final File src, final QueryContext ctx)
      throws QueryException {

    File trg = new File(string(checkStr(expr[1], ctx)));
    // attach file name if target is a directory
    if(trg.isDirectory()) {
      trg = new File(trg, src.getName());
    } else if(!trg.isFile()) {
      final File par = trg.getParentFile();
      if(!par.exists() || !par.isDirectory()) PATHINVALID.thrw(input, trg);
    }

    if(!src.exists()) PATHNOTEXISTS.thrw(input, src);
    if(src.isDirectory()) PATHISDIR.thrw(input, src);

    if(!src.equals(trg)) {
      FileChannel sc = null;
      FileChannel dc = null;
      try {
        sc = new FileInputStream(src).getChannel();
        dc = new FileOutputStream(trg).getChannel();
        dc.transferFrom(sc, 0, sc.size());
      } catch(final IOException ex) {
        FILEERROR.thrw(input, ex);
      } finally {
        if(sc != null) try { sc.close(); } catch(final IOException ex) { }
        if(dc != null) try { dc.close(); } catch(final IOException ex) { }
      }
    }
    return null;
  }