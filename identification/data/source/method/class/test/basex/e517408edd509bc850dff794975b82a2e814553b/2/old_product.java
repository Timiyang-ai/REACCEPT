private void sort(final QueryContext qc) throws QueryException {
        // keys are stored at odd positions, values at even ones
        List<Value[]> tuples = new ArrayList<>();
        while(sub.next(qc)) {
          final int kl = keys.length;
          final Item[] key = new Item[kl];
          for(int k = 0; k < kl; k++) key[k] = keys[k].expr.atomItem(qc, keys[k].info);
          tuples.add(key);

          final int rl = refs.length;
          final Value[] vals = new Value[rl];
          for(int r = 0; r < rl; r++) vals[r] = refs[r].value(qc);
          tuples.add(vals);
        }

        final int len = tuples.size() >>> 1;
        final Item[][] ks = new Item[len][];
        perm = new Integer[len];
        tpls = new Value[len][];
        for(int i = 0; i < len; i++) {
          perm[i] = i;
          tpls[i] = tuples.get(i << 1 | 1);
          ks[i] = (Item[]) tuples.get(i << 1);
        }
        // be nice to the garbage collector
        tuples = null;
        try {
          Arrays.sort(perm, new Comparator<Integer>() {
            @Override
            public int compare(final Integer x, final Integer y) {
              try {
                final Item[] a = ks[x], b = ks[y];
                final int kl = keys.length;
                for(int k = 0; k < kl; k++) {
                  final Key or = keys[k];
                  Item m = a[k], n = b[k];
                  if(m == Dbl.NAN || m == Flt.NAN) m = null;
                  if(n == Dbl.NAN || n == Flt.NAN) n = null;
                  if(m != null && n != null && !m.comparable(n))
                    throw castError(n, m.type, or.info);

                  final int c = m == null
                      ? n == null ? 0                 : or.least ? -1 : 1
                      : n == null ? or.least ? 1 : -1 : m.diff(n, or.coll, or.info);
                  if(c != 0) return or.desc ? -c : c;
                }
                return 0;
              } catch(final QueryException ex) {
                throw new QueryRTException(ex);
              }
            }
          });
        } catch(final QueryRTException ex) {
          throw ex.getCause();
        }
      }