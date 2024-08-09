private synchronized void copy(final Path src, final Path trg)
      throws QueryException, IOException {

    if(Files.isDirectory(src)) {
      Files.createDirectory(trg);
      try(DirectoryStream<Path> paths = Files.newDirectoryStream(src)) {
        for(final Path p : paths) copy(p, trg.resolve(p.getFileName()));
      }
    } else {
      Files.copy(src, trg, StandardCopyOption.REPLACE_EXISTING);
    }
  }