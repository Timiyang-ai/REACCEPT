private B64 delete(final QueryContext qc) throws QueryException {
    final B64 archive = (B64) checkType(checkItem(exprs[0], qc), AtomType.B64);
    // entries to be deleted
    final TokenObjMap<Item[]> hm = new TokenObjMap<>();
    final Iter names = qc.iter(exprs[1]);
    for(Item en; (en = names.next()) != null;) {
      hm.put(checkElmStr(en).string(info), null);
    }

    final ArchiveIn in = ArchiveIn.get(archive.input(info), info);
    final ArchiveOut out = ArchiveOut.get(in.format(), info);
    try {
      if(in instanceof GZIPIn)
        throw ARCH_MODIFY.get(info, in.format().toUpperCase(Locale.ENGLISH));
      while(in.more()) if(!hm.contains(token(in.entry().getName()))) out.write(in);
    } catch(final IOException ex) {
      throw ARCH_FAIL.get(info, ex);
    } finally {
      in.close();
      out.close();
    }
    return new B64(out.toArray());
  }