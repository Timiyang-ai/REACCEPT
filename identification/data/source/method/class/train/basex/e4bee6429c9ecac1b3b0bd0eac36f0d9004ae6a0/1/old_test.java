@Test
  public void shift() {
    hexQuery(_BIN_SHIFT.args("()", 1),          "");
    hexQuery(_BIN_SHIFT.args(base64("77"), 0),  "77");
    hexQuery(_BIN_SHIFT.args(base64("33"), 1),  "66");
    hexQuery(_BIN_SHIFT.args(base64("66"), -1), "33");
  }