@Test
  public void execute ()
  {
    String[] result = StringUtils.split(Eclim.execute(new String[]{"ping"}), '\n');
    assertEquals("Unexpected result",
        "eclim   " + System.getProperty("eclim.version"), result[0]);
    assertTrue("Unexpected result", result[1].startsWith("eclipse 3."));
  }