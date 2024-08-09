private void array() throws QueryIOException {
    consumeWs('[', true);
    conv.openArray();
    if(!consumeWs(']', false)) {
      do {
        conv.openItem();
        value();
        conv.closeItem();
      } while(consumeWs(',', false) && !(spec == JsonSpec.LIBERAL && curr() == ']'));
      consumeWs(']', true);
    }
    conv.closeArray();
  }