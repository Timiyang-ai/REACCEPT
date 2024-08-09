final FuncType type(final int arity, final AnnList anns) {
    final SeqType[] st = new SeqType[arity];
    if(arity != 0 && minMax[1] == Integer.MAX_VALUE) {
      final int al = args.length;
      System.arraycopy(args, 0, st, 0, al);
      final SeqType var = args[al - 1];
      for(int a = al; a < arity; a++) st[a] = var;
    } else {
      System.arraycopy(args, 0, st, 0, arity);
    }
    return FuncType.get(anns, type, st);
  }