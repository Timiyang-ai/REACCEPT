public final ArrayOutput serialize() throws QueryException {
    final ArrayOutput ao = new ArrayOutput();
    try {
      final Serializer ser = Serializer.get(ao);
      final ValueIter vi = iter();
      for(Item it; (it = vi.next()) != null;) ser.serialize(it);
      ser.close();
    } catch(final QueryIOException ex) {
      throw ex.getCause(null);
    } catch(final IOException ex) {
      SERANY.thrw(null, ex);
    }
    return ao;
  }