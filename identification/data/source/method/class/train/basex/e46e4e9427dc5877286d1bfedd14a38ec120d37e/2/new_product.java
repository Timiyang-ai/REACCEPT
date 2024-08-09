public static Expr get(final Op cmp, final Expr a, final Expr o,
      final InputInfo ii) throws QueryException {

    // position() = last() -> last()
    if(a.isFun(Function.LAST) && cmp == Op.EQ) return a;

    if(a.item()) {
      final Item it = (Item) a;
      if(it.num()) {
        final long p = it.itr(ii);
        final boolean ex = p == it.dbl(ii);
        switch(cmp) {
          case EQ: return ex ? get(p, p, ii) : Bln.FALSE;
          case GE: return get(ex ? p : p + 1, Long.MAX_VALUE, ii);
          case GT: return get(p + 1, Long.MAX_VALUE, ii);
          case LE: return get(1, p, ii);
          case LT: return get(1, ex ? p - 1 : p, ii);
          default:
        }
      }
    }
    return o;
  }