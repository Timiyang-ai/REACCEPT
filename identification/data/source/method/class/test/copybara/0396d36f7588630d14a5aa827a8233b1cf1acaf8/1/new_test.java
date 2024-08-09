  @Test
  public void logLines_empty() {
    Consoles.logLines(console, "prefix", /*text*/ "");
    console.assertThat()
        .containsNoMoreMessages();
  }