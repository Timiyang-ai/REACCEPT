public Value cast(final Value value, final QueryContext qc, final StaticContext sc,
      final InputInfo ii) throws QueryException {

    final long vs = value.size();
    if(!occ.check(vs)) throw INVTYPE_X_X_X.get(ii, value.seqType(), this, value);

    if(value.isEmpty()) return Empty.SEQ;
    if(value instanceof Item) return cast((Item) value, qc, sc, ii, true);

    final ValueBuilder vb = new ValueBuilder();
    for(final Item it : value) vb.add(cast(it, qc, sc, ii, true));
    return vb.value();
  }