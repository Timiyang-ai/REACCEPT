private Value foldLeft1(final QueryContext qc) throws QueryException {
    final FItem f = withArity(1, 2, qc);
    final Iter xs = exprs[0].iter(qc);

    Value sum = checkNoEmpty(xs.next());
    for(Item x; (x = xs.next()) != null;) sum = f.invokeValue(qc, info, sum, x);
    return sum;
  }