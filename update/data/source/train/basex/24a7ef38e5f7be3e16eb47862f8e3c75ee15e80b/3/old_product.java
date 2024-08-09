private Item move(final File src, final QueryContext ctx)
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
    if(!src.renameTo(trg)) CANNOTMOVE.thrw(input, src, trg);
    return null;
  }