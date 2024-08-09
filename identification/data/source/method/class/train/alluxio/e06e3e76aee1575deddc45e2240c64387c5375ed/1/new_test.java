  @Test
  public void execCommand() throws Exception {
    String testString = "alluxio";
    // Execute echo for testing command execution.
    String result = ShellUtils.execCommand("bash", "-c", "echo " + testString);
    assertEquals(testString + "\n", result);
  }