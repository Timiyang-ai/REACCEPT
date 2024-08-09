@Test
  public void encodeJsonTest() {
    Set<String> escapedChars = new HashSet<>();
    escapedChars.add("\"");
    escapedChars.add("/");
    escapedChars.add("\\");
    for (char i = 32; i < 128; i++) {
      String s = String.valueOf(i);
      Assert.assertEquals("\"" + (escapedChars.contains(s) ? "\\" : "") + s + "\"",
          FormatUtils.encodeJson(s));
    }
  }