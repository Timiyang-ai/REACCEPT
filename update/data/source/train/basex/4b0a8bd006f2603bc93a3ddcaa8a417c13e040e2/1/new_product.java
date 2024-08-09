private Value foldLeft1(final QueryContext qc) throws QueryException {
    final FItem f = withArity(1, 2, qc);
    final Iter iter = exprs[0].iter(qc);

    Value sum = checkNoEmpty(iter.next());
    for(Item it; (it = iter.next()) != null;) sum = f.invokeValue(qc, info, sum, it);
    return sum;
  }