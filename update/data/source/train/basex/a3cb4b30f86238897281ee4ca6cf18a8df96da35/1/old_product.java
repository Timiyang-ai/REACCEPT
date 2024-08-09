private void object() throws QueryIOException {
    consumeWs('{', true);
    conv.openObject();
    if(!consumeWs('}', false)) {
      do {
        conv.openPair(spec != JsonSpec.LIBERAL || curr() == '"' ? string() : unquoted());
        consumeWs(':', true);
        value();
        conv.closePair();
      } while(consumeWs(',', false) && !(spec == JsonSpec.LIBERAL && curr() == '}'));
      consumeWs('}', true);
    }
    conv.closeObject();
  }