@Test
  public void binaryToBytes() {
    query(_CONVERT_BINARY_TO_BYTES.args("xs:base64Binary('QmFzZVggaXMgY29vbA==')"),
      "66 97 115 101 88 32 105 115 32 99 111 111 108");
    query(_CONVERT_BINARY_TO_BYTES.args("xs:base64Binary(xs:hexBinary('4261736558'))"),
      "66 97 115 101 88");
    query(_CONVERT_BINARY_TO_BYTES.args("xs:base64Binary(<x>AAE=</x>)"), "0 1");
    query(_CONVERT_BINARY_TO_BYTES.args(_CONVERT_STRING_TO_BASE64.args("a")), 97);
    query(COUNT.args(_CONVERT_BINARY_TO_BYTES.args(_CONVERT_STRING_TO_BASE64.args("\u00e4"))), 2);
    query(COUNT.args(_CONVERT_BINARY_TO_BYTES.args(_CONVERT_STRING_TO_BASE64.args("123"))), 3);
  }