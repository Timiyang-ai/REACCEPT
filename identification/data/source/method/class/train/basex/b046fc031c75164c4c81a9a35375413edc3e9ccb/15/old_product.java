private B64 update(final QueryContext qc) throws QueryException {
    final B64 archive = (B64) checkType(checkItem(exprs[0], qc), AtomType.B64);
    // entries to be updated
    final TokenObjMap<Item[]> hm = new TokenObjMap<>();

    final Iter entr = qc.iter(exprs[1]);
    final Iter cont = qc.iter(exprs[2]);
    int e = 0;
    int c = 0;
    Item en, cn;
    while(true) {
      en = entr.next();
      cn = cont.next();
      if(en == null || cn == null) break;
      hm.put(checkElmStr(en).string(info), new Item[] { en, cn });
      e++;
      c++;
    }
    // count remaining entries
    if(cn != null) do c++; while(cont.next() != null);
    if(en != null) do e++; while(entr.next() != null);
    if(e != c) throw ARCH_DIFF.get(info, e, c);

    final ArchiveIn in = ArchiveIn.get(archive.input(info), info);
    final ArchiveOut out = ArchiveOut.get(in.format(), info);
    try {
      if(in instanceof GZIPIn)
        throw ARCH_MODIFY.get(info, in.format().toUpperCase(Locale.ENGLISH));
      // delete entries to be updated
      while(in.more()) if(!hm.contains(token(in.entry().getName()))) out.write(in);
      // add new and updated entries
      for(final byte[] h : hm) {
        if(h == null) continue;
        final Item[] it = hm.get(h);
        add(it[0], it[1], out, ZipEntry.DEFLATED, qc);
      }
    } catch(final IOException ex) {
      throw ARCH_FAIL.get(info, ex);
    } finally {
      in.close();
      out.close();
    }
    return new B64(out.toArray());
  }