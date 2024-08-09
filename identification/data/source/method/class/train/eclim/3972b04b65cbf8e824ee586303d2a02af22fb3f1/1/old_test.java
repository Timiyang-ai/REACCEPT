@Test
  public void execute ()
  {
    String result = Eclim.execute(new String[]{"-command", "ping"});
    assertEquals("Unexpected result",
        "eclim " + System.getProperty("eclim.version"), result);
  }