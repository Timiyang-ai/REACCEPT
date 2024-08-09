private B64 update(final QueryContext qc) throws QueryException {
    final B64 archive = toB64(exprs[0], qc, false);
    // entries to be updated
    final TokenObjMap<Item[]> hm = new TokenObjMap<>();

    final Iter entr = qc.iter(exprs[1]), cont = qc.iter(exprs[2]);
    int e = 0, c = 0;
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
    if(e != c) throw ARCH_DIFF_X_X.get(info, e, c);

    final ArchiveIn in = ArchiveIn.get(archive.input(info), info);
    final ArchiveOut out = ArchiveOut.get(in.format(), info);
    try {
      if(in instanceof GZIPIn)
        throw ARCH_MODIFY_X.get(info, in.format().toUpperCase(Locale.ENGLISH));
      // delete entries to be updated
      while(in.more()) if(!hm.contains(token(in.entry().getName()))) out.write(in);
      // add new and updated entries
      for(final byte[] h : hm) {
        if(h == null) continue;
        final Item[] it = hm.get(h);
        add(it[0], it[1], out, ZipEntry.DEFLATED, qc);
      }
    } catch(final IOException ex) {
      throw ARCH_FAIL_X.get(info, ex);
    } finally {
      in.close();
      out.close();
    }
    return new B64(out.toArray());
  }