private synchronized Item createDir(final QueryContext qc) throws QueryException, IOException {
    final Path path = absolute(toPath(0, qc));

    // find lowest existing path
    for(Path p = path; p != null;) {
      if(Files.exists(p)) {
        if(Files.isRegularFile(p)) throw FILE_EXISTS_X.get(info, p);
        break;
      }
      p = p.getParent();
    }

    Files.createDirectories(path);
    return null;
  }