private Iter list(final QueryContext qc) throws QueryException {
    final TokenList tl = new TokenList();
    final int el = exprs.length;
    if(el == 0) {
      for(final String s : qc.context.databases.listDBs()) tl.add(s);
    } else {
      final Data data = checkData(qc);
      final String path = string(el == 1 ? Token.EMPTY : toToken(exprs[1], qc));
      // add xml resources
      final Resources res = data.resources;
      final IntList il = res.docs(path);
      final int is = il.size();
      for(int i = 0; i < is; i++) tl.add(data.text(il.get(i), true));
      // add binary resources
      for(final byte[] file : res.binaries(path)) tl.add(file);
    }
    tl.sort(Prop.CASE);

    return new Iter() {
      int pos;
      @Override
      public Str get(final long i) { return Str.get(tl.get((int) i)); }
      @Override
      public Str next() { return pos < size() ? get(pos++) : null; }
      @Override
      public boolean reset() { pos = 0; return true; }
      @Override
      public long size() { return tl.size(); }
    };
  }