private Iter attribute(final QueryContext ctx) throws QueryException {
    final IndexContext ic = new IndexContext(ctx, data(0, ctx), null, true);
    final IndexAccess ia = new IndexAccess(
        input, expr[1], IndexType.ATTRIBUTE, ic);

    // return iterator if no name test is specified
    if(expr.length < 3) return ia.iter(ctx);

    // parse and compile the name test
    final Item name = checkEmpty(expr[2].item(ctx, input));
    final QNm nm = new QNm(checkStr(name, ctx), ctx, input);

    final NameTest nt = new NameTest(nm, NameTest.Name.STD, true, input);
    // no results expected: return empty sequence
    if(!nt.comp(ctx)) return Empty.ITER;

    // wrap iterator with name test
    return new Iter() {
      final NodeIter ir = ia.iter(ctx);

      @Override
      public Item next() throws QueryException {
        ANode n;
        while((n = ir.next()) != null && !nt.eval(n));
        return n;
      }
    };
  }