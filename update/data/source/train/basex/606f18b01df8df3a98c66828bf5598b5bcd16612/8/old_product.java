private void list(final int root, final File dir, final StringList list, final boolean rec,
      final Pattern pat) throws QueryException {

    // skip invalid directories
    final File[] ch = dir.listFiles();
    if(ch == null) return;

    // parse directories, do not follow links
    if(rec) {
      for(final File f : ch) {
        if(f.isDirectory() && !mayBeLink(f)) list(root, f, list, rec, pat);
      }
    }
    // parse files. ignore directories if a pattern is specified
    for(final File f : ch) {
      if(pat == null || pat.matcher(f.getName()).matches()) {
        final String file = f.getPath().substring(root);
        list.add(f.isDirectory() ? dir(file) : file);
      }
    }
  }