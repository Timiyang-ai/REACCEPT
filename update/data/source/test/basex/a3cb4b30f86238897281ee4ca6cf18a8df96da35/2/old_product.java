private void parse() throws QueryIOException {
    skipWs();
    if(spec == JsonSpec.RFC4627 && !(curr() == '{' || curr() == '['))
      throw error("Expected '{' or '[', found %", rest());
    value();
    if(more()) throw error("Unexpected trailing content: %", rest());
  }