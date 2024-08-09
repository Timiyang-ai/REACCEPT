private void parse() throws QueryIOException {
    skipWs();
    value();
    if(more()) throw error("Unexpected trailing content: %", rest());
  }