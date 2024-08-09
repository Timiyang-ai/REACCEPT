final Iter attribute(final IndexAccess ia, final QueryContext qc, final int a)
      throws QueryException {

    // no attribute specified
    if(exprs.length <= a) return ia.iter(qc);

    // parse and compile the name test
    final QNm nm = new QNm(toToken(exprs[a], qc), sc);
    if(!nm.hasPrefix()) nm.uri(sc.ns.uri(Token.EMPTY));

    final NameTest nt = new NameTest(nm, Test.Kind.URI_NAME, true, sc.elemNS);
    // return empty sequence if test will yield no results
    if(!nt.optimize(qc)) return Empty.ITER;

    // wrap iterator with name test
    return new NodeIter() {
      final NodeIter ir = ia.iter(qc);
      @Override
      public ANode next() throws QueryException {
        ANode n;
        while((n = ir.next()) != null && !nt.eq(n));
        return n;
      }
    };
  }