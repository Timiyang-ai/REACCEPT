  @Test
  public void test_validateHostName_hostNameTooLong() {
    assertThrows(
        HostNameTooLongException.class,
        () -> validateHostName(Strings.repeat("na", 200) + ".wat.man"));
  }