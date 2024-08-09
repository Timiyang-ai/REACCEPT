private Item write(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    final B64 archive = (B64) checkType(checkItem(expr[1], ctx), AtomType.B64);
    final TokenSet hs = entries(2, ctx);

    final ArchiveIn in = ArchiveIn.get(archive.input(info), info);
    try {
      while(in.more()) {
        final ZipEntry ze = in.entry();
        final String name = ze.getName();
        if(hs == null || hs.delete(token(name)) != 0) {
          final IOFile file = new IOFile(path.getPath(), name);
          if(ze.isDirectory()) {
            file.md();
          } else {
            file.dir().md();
            file.write(in.read());
          }
        }
      }
    } catch(final IOException ex) {
      throw ARCH_FAIL.get(info, ex);
    } finally {
      in.close();
    }
    return null;
  }