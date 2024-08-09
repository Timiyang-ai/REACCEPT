private Item write(final QueryContext ctx) throws QueryException {
    final java.nio.file.Path path = checkPath(0, ctx);
    final B64 archive = (B64) checkType(checkItem(expr[1], ctx), AtomType.B64);
    final TokenSet hs = entries(2, ctx);

    final ArchiveIn in = ArchiveIn.get(archive.input(info), info);
    try {
      while(in.more()) {
        final ZipEntry ze = in.entry();
        final String name = ze.getName();
        if(hs == null || hs.delete(token(name)) != 0) {
          final java.nio.file.Path file = path.resolve(name);
          if(ze.isDirectory()) {
            Files.createDirectory(file);
          } else {
            Files.createDirectory(file.getParent());
            Files.write(file, in.read());
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