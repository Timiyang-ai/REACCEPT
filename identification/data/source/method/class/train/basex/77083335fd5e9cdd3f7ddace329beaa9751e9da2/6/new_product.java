private Dbl mb(final QueryContext ctx) throws QueryException {
    // check caching flag
    final boolean c = expr.length == 2 &&
      checkType(expr[1].item(ctx, input), Type.BLN).bool(input);

    // measure initial memory consumption
    Performance.gc(3);
    final long l = Performance.mem();

    // create (and, optionally, cache) result value
    Iter ir = expr[0].iter(ctx);
    final Value v = (c ? ItemIter.get(ir) : ir).finish();

    // measure resulting memory consumption
    Performance.gc(2);
    final double d = Performance.mem() - l;

    // loop through all results to avoid premature result disposal
    ir = v.iter();
    while(ir.next() != null);

    // return memory consumption in megabytes
    return Dbl.get(Math.max(0, d) / 1024 / 1024d);
  }