@Test public void serialize() {
    for(final String[] f : TOJSON) {
      final String qu = _JSON_SERIALIZE.args(f[0]);
      if(f.length == 1) {
        error(qu, Err.BXJS_SERIAL, Err.BXJS_CONFIG);
      } else if(f[1].startsWith("...")) {
        contains(qu, f[1].substring(3));
      } else {
        query(qu, f[1]);
      }
    }
  }