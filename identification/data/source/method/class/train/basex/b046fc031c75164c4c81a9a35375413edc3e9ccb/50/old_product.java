private Iter children(final QueryContext qc) throws QueryException, IOException {
    final TokenList children = new TokenList();
    try(DirectoryStream<Path> paths = Files.newDirectoryStream(checkPath(0, qc))) {
      for(final Path child : paths) children.add(get(child, Files.isDirectory(child)).string());
    }
    return StrSeq.get(children).iter();
  }