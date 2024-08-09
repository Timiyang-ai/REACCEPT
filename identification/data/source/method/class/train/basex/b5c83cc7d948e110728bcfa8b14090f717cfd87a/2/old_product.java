private synchronized void copy(final File src, final File trg)
      throws QueryException, IOException {

    if(src.isDirectory()) {
      if(!trg.mkdir()) throw FILE_CREATE.get(info, trg);
      final File[] files = src.listFiles();
      if(files == null) throw FILE_LIST.get(info, src);
      for(final File f : files) copy(f, new File(trg, f.getName()));
    } else {
      new IOFile(src).copyTo(new IOFile(trg));
    }
  }