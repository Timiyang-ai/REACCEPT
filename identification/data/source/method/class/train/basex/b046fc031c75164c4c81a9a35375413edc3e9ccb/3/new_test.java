@Test
  public void decodeString() {
    query(_BIN_DECODE_STRING.args(base64("31")),                  "1");
    query(_BIN_DECODE_STRING.args(base64("31"), "US-ASCII"),      "1");
    query(_BIN_DECODE_STRING.args(base64("31"), "UTF-8"),         "1");
    query(_BIN_DECODE_STRING.args(base64("3132"), "UTF-8", 1, 1), "2");
    // errors
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", -1),    BIN_IOOR_X_X);
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", 0, -1), BIN_NS_X);
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", 1, 0),  BIN_IOOR_X_X);
    error(_BIN_DECODE_STRING.args(base64(""), "UTF-8", 0, 1),  BIN_IOOR_X_X);
    error(_BIN_DECODE_STRING.args(base64(""), "X"),            BIN_UE_X);
    error(_BIN_DECODE_STRING.args(base64("FF"), "UTF-8"),      BIN_CE_X);
    error(_BIN_DECODE_STRING.args(_BIN_HEX.args("03")),        BIN_CE_X);
  }