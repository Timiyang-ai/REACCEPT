@Test
  public void encodeString() {
    hexQuery(_BIN_ENCODE_STRING.args(""),                "");
    hexQuery(_BIN_ENCODE_STRING.args("", "US-ASCII"),    "");
    hexQuery(_BIN_ENCODE_STRING.args("a", "US-ASCII"),   "61");
    hexQuery(_BIN_ENCODE_STRING.args("\u00c4", "UTF-8"), "C384");
    // errors
    error(_BIN_ENCODE_STRING.args("", "X"),              BIN_UE_X);
    error(_BIN_ENCODE_STRING.args("\u00c4", "US-ASCII"), BIN_CE_X);
  }