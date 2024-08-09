private boolean match(final int[] t, final int tp, final int qp) {
    int qi = qp;
    int ti = tp;
    final int tl = t.length;
    final int wl = wc.length;
    while(qi < wl) {
      if(wc[qi] == DOT) {
        int n = min[qi];
        final int m = max[qi++];
        // recursively evaluates wildcards (non-greedy)
        while(!match(t, ti + n, qi)) if(ti + ++n > tl) return false;
        if(n > m) return false;
        ti += n;
      } else {
        if(ti >= tl || t[ti++] != wc[qi++]) return false;
      }
    }
    return ti == tl;
  }