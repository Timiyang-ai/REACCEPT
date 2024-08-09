public static Expr[] concat(final Expr[] source, final Expr... add) {
    final int sl = source.length, al = add.length;
    final Expr[] tmp = new Expr[sl + al];
    Array.copy(source, sl, tmp);
    Array.copyFromStart(add, al, tmp, sl);
    return tmp;
  }