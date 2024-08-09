  @Test
  public void escapeString() {
    final String[] testPairs = {
      "", "",
      "plain string", "plain string",
      "string with \"quotes\"", "string with \"quotes\"",
      "string with 'quotes'", "string with 'quotes'",
      "string with 'quotes'", "string with 'quotes'",
      "C:\\Program Files\\MyProgram", "C:\\\\Program Files\\\\MyProgram",
      "string\nwith\nnewlines", "string\\nwith\\nnewlines",
      "string\twith\ttabs", "string\\twith\\ttabs",
    };
    for (int i = 0; i < testPairs.length; i += 2) {
      assertEquals(StringUtil.escapeString(testPairs[i]), testPairs[i + 1]);
    }
  }