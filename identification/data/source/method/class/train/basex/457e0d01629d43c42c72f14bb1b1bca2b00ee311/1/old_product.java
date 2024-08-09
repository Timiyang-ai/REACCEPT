private boolean match(final int[] t, final int tp, final int qp) {
    int ql = qp;
    int tl = tp;
    while(ql < wc.length) {
      if(wc[ql] == DOT) {
        int n = min[ql];
        final int m = max[ql++];
        // recursively evaluates wildcards (non-greedy)
        while(!match(t, tl + n, ql))
          if(tl + ++n > t.length) return false;
        if(n > m) return false;
        tl += n;
      } else {
        if(tl >= t.length || t[tl++] != wc[ql++]) return false;
      }
    }
    return tl == t.length;
  }