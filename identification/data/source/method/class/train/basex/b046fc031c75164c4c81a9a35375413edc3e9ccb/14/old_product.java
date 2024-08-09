private synchronized Item createDir(final QueryContext qc) throws QueryException, IOException {
    final Path path = absolute(checkPath(0, qc));

    // find lowest existing path
    for(Path p = path; p != null;) {
      if(Files.exists(p)) {
        if(Files.isRegularFile(p)) throw FILE_EXISTS.get(info, p);
        break;
      }
      p = p.getParent();
    }

    Files.createDirectories(path);
    return null;
  }