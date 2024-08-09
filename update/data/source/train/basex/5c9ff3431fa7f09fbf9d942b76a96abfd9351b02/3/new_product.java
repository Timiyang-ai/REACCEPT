public final ArrayOutput serialize() throws QueryIOException {
    final ArrayOutput ao = new ArrayOutput();
    try {
      final Serializer ser = Serializer.get(ao);
      final ValueIter vi = iter();
      for(Item it; (it = vi.next()) != null;) ser.serialize(it);
      ser.close();
    } catch(QueryIOException ex) {
      throw ex;
    } catch(final IOException ex) {
      SERANY.thrwIO(ex);
    }
    return ao;
  }