public static Integer[] sort(final ValueList vl, final StandardFunc sf) throws QueryException {
    final int al = vl.size();
    final Integer[] order = new Integer[al];
    for(int o = 0; o < al; o++) order[o] = o;
    try {
      Arrays.sort(order, new Comparator<Integer>() {
        @Override
        public int compare(final Integer i1, final Integer i2) {
          try {
            final Value v1 = vl.get(i1), v2 = vl.get(i2);
            final long s1 = v1.size(), s2 = v2.size(), sl = Math.min(s1, s2);
            for(int v = 0; v < sl; v++) {
              final Item it1 = v1.itemAt(v), it2 = v2.itemAt(v);
              if(!it1.comparable(it2)) {
                throw it1 instanceof FItem ? FIEQ_X.get(sf.info, it1.type) :
                      it2 instanceof FItem ? FIEQ_X.get(sf.info, it2.type) :
                      diffError(it1, it2, sf.info);
              }
              final int d = it1.diff(it2, sf.sc.collation, sf.info);
              if(d != 0 && d != Item.UNDEF) return d;
            }
            return (int) (s1 - s2);
          } catch(final QueryException ex) {
            throw new QueryRTException(ex);
          }
        }
      });
    } catch(final QueryRTException ex) {
      throw ex.getCause();
    }
    return order;
  }