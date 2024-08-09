public Value cast(final Item it, final QueryContext qc, final StaticContext sc,
      final InputInfo info, final boolean error) throws QueryException {

    if(it.type.eq(type)) return it;
    try {
      if(!error && info != null) info.check(true);
      final Value v = type.cast(it, qc, sc, info);
      if(kind != null) {
        for(final Item i : v) if(!kind.eq(it)) throw Err.castError(info, i, type);
      }
      return v;
    } catch(final QueryException ex) {
      if(error) throw ex;
      return null;
    } finally {
      if(!error && info != null) info.check(false);
    }
  }