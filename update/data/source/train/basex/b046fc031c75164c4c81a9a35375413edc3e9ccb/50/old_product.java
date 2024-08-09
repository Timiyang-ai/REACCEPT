private Item write(final QueryContext qc) throws QueryException {
    final java.nio.file.Path path = checkPath(0, qc);
    final B64 archive = (B64) checkType(checkItem(exprs[1], qc), AtomType.B64);
    final TokenSet hs = entries(2, qc);

    final ArchiveIn in = ArchiveIn.get(archive.input(info), info);
    try {
      while(in.more()) {
        final ZipEntry ze = in.entry();
        final String name = ze.getName();
        if(hs == null || hs.delete(token(name)) != 0) {
          final java.nio.file.Path file = path.resolve(name);
          if(ze.isDirectory()) {
            Files.createDirectories(file);
          } else {
            Files.createDirectories(file.getParent());
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