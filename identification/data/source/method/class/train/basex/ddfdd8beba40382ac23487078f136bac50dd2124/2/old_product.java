private void object(final JsonHandler h) throws QueryException {
    consumeWs('{', true);
    h.openObject();
    if(!consumeWs('}', false)) {
      do {
        h.openPair(spec != Spec.LIBERAL || curr() == '"' ? string() : unquoted());
        consumeWs(':', true);
        value(h);
        h.closePair();
      } while(consumeWs(',', false) && !(spec == Spec.LIBERAL && curr() == '}'));
      consumeWs('}', true);
    }
    h.closeObject();
  }