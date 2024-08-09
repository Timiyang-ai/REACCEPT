final FuncType type(final int arity, final AnnList anns) {
    final SeqType[] st = new SeqType[arity];
    if(arity != 0 && minMax[1] == Integer.MAX_VALUE) {
      final int pl = params.length;
      System.arraycopy(params, 0, st, 0, pl);
      final SeqType var = params[pl - 1];
      for(int p = pl; p < arity; p++) st[p] = var;
    } else {
      System.arraycopy(params, 0, st, 0, arity);
    }
    return FuncType.get(anns, type, st);
  }