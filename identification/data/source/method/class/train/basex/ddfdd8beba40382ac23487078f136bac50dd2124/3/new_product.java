private void array(final JsonHandler h) throws QueryException {
    consumeWs('[', true);
    h.openArray();
    if(!consumeWs(']', false)) {
      do {
        h.openItem();
        value(h);
        h.closeItem();
      } while(consumeWs(',', false) && !(spec == JsonSpec.LIBERAL && curr() == ']'));
      consumeWs(']', true);
    }
    h.closeArray();
  }