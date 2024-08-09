private void copy(final File src, final File trg) throws QueryException {
    if(src.isDirectory()) {
      if(!trg.mkdir()) CANNOTCREATE.thrw(input, trg);
      final File[] files = src.listFiles();
      if(files == null) CANNOTLIST.thrw(input, src);
      for(final File f : files) copy(f, new File(trg, f.getName()));
    } else {
      FileChannel sc = null;
      FileChannel tc = null;
      try {
        sc = new FileInputStream(src).getChannel();
        tc = new FileOutputStream(trg).getChannel();
        tc.transferFrom(sc, 0, sc.size());
      } catch(final IOException ex) {
        FILEERROR.thrw(input, ex);
      } finally {
        if(sc != null) try { sc.close(); } catch(final IOException ex) { }
        if(tc != null) try { tc.close(); } catch(final IOException ex) { }
      }
    }
  }