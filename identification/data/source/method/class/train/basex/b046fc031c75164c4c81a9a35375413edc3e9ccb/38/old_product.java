private int contains(final QueryContext qc) throws QueryException {
    first = true;
    final FTLexer lexer = ftt.lexer(qc.ftToken);

    // use faster evaluation for default options
    int num = 0;
    if(fast) {
      for(final byte[] t : tokens) {
        final FTTokens qtok = ftt.cache(t);
        num = Math.max(num, ftt.contains(qtok, lexer) * qtok.length());
      }
      return num;
    }

    // find and count all occurrences
    final boolean all = mode == FTMode.ALL || mode == FTMode.ALL_WORDS;
    int oc = 0;
    for(final byte[] w : unique(tokens(qc))) {
      final FTTokens qtok = ftt.cache(w);
      final int o = ftt.contains(qtok, lexer);
      if(all && o == 0) return 0;
      num = Math.max(num, o * qtok.length());
      oc += o;
    }

    // check if occurrences are in valid range. if yes, return number of tokens
    final long mn = occ != null ? checkItr(occ[0], qc) : 1;
    final long mx = occ != null ? checkItr(occ[1], qc) : Long.MAX_VALUE;
    if(mn == 0 && oc == 0) matches = FTNot.not(matches);
    return oc >= mn && oc <= mx ? Math.max(1, num) : 0;
  }