@Test
  public void sessionId() throws Exception {
    assertEquals("1", get("?query=" + request("count(S:session-id())")));
  }