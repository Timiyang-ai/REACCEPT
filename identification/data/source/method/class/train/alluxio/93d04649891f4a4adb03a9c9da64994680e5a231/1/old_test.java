  @Test
  public void formatPermission() {
    assertEquals("-rw-rw-rw-", FormatUtils.formatMode((short) 0666, false, false));
    assertEquals("drw-rw-rw-", FormatUtils.formatMode((short) 0666, true, false));
    assertEquals("-rwxrwxrwx", FormatUtils.formatMode((short) 0777, false, false));
    assertEquals("drwxrwxrwx", FormatUtils.formatMode((short) 0777, true, false));
    assertEquals("-r--r--r--", FormatUtils.formatMode((short) 0444, false, false));
    assertEquals("dr--r--r--", FormatUtils.formatMode((short) 0444, true, false));
    assertEquals("-r-xr-xr-x", FormatUtils.formatMode((short) 0555, false, false));
    assertEquals("dr-xr-xr-x", FormatUtils.formatMode((short) 0555, true, false));
    assertEquals("-rwxr-xr--", FormatUtils.formatMode((short) 0754, false, false));
    assertEquals("drwxr-xr--", FormatUtils.formatMode((short) 0754, true, false));
  }