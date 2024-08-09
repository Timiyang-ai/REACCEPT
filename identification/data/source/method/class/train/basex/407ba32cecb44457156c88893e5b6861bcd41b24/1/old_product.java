public static Integer[] sort(final ValueList vl, final StandardFunc sf, final Collation coll)
      throws QueryException {

    final int al = vl.size();
    final Integer[] order = new Integer[al];
    for(int o = 0; o < al; o++) order[o] = o;
    try {
      Arrays.sort(order, (i1, i2) -> {
        try {
          final Value v1 = vl.get(i1), v2 = vl.get(i2);
          final long s1 = v1.size(), s2 = v2.size(), sl = Math.min(s1, s2);
          for(int v = 0; v < sl; v++) {
            Item m = v1.itemAt(v), n = v2.itemAt(v);
            if(m == Dbl.NAN || m == Flt.NAN) m = null;
            if(n == Dbl.NAN || n == Flt.NAN) n = null;
            if(m != null && n != null && !m.comparable(n)) {
              throw m instanceof FItem ? FIEQ_X.get(sf.info, m.type) :
                    n instanceof FItem ? FIEQ_X.get(sf.info, n.type) :
                    diffError(m, n, sf.info);
            }
            final int d = m == null ? n == null ? 0 : -1 : n == null ? 1 :
              m.diff(n, coll, sf.info);
            if(d != 0 && d != Item.UNDEF) return d;
          }
          return (int) (s1 - s2);
        } catch(final QueryException ex) {
          throw new QueryRTException(ex);
        }
      });
    } catch(final QueryRTException ex) {
      throw ex.getCause();
    }
    return order;
  }