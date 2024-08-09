private Item event(final QueryContext qc) throws QueryException {
    final byte[] name = toToken(exprs[0], qc);
    try {
      final ArrayOutput ao = qc.value(exprs[1]).serialize(SerializerOptions.get(false));
      // throw exception if event is unknown
      if(!qc.context.events.notify(qc.context, name, ao.finish()))
        throw BXDB_EVENT_X.get(info, name);
      return null;
    } catch(final QueryIOException ex) {
      throw ex.getCause(info);
    }
  }