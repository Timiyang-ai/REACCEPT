public static Expr[] concat(final Expr[] source, final Expr... add) {
    final int sl = source.length, al = add.length;
    final Expr[] tmp = new Expr[sl + al];
    System.arraycopy(source, 0, tmp, 0, sl);
    System.arraycopy(add, 0, tmp, sl, al);
    return tmp;
  }