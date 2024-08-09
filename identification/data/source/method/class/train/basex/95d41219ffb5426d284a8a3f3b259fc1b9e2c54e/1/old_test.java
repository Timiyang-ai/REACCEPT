@Test
  public void decodeString() {
    query(_BIN_DECODE_STRING.args(base64("3132"), "US-ASCII"),      "12");
    query(_BIN_DECODE_STRING.args(base64("3132"), "UTF-8"),         "12");
    query(_BIN_DECODE_STRING.args(base64("313233"), "UTF-8", 1, 1), "2");
    // errors
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", -1),    BIN_NO_X);
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", 0, -1), BIN_NS_X);
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", 1, 0),  BIN_OBE_X_X);
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", 0, 1),  BIN_OBE_X_X_X);
    error(_BIN_DECODE_STRING.args(base64(""), "X"),            BIN_UE_X);
    error(_BIN_DECODE_STRING.args(base64("FF"), "UTF-8"),      BIN_DE);
  }