@Test
  public void formatPermissionTest() {
    Assert.assertEquals("-rw-rw-rw-", FormatUtils.formatPermission((short) 0666, false));
    Assert.assertEquals("drw-rw-rw-", FormatUtils.formatPermission((short) 0666, true));
    Assert.assertEquals("-rwxrwxrwx", FormatUtils.formatPermission((short) 0777, false));
    Assert.assertEquals("drwxrwxrwx", FormatUtils.formatPermission((short) 0777, true));
    Assert.assertEquals("-r--r--r--", FormatUtils.formatPermission((short) 0444, false));
    Assert.assertEquals("dr--r--r--", FormatUtils.formatPermission((short) 0444, true));
    Assert.assertEquals("-r-xr-xr-x", FormatUtils.formatPermission((short) 0555, false));
    Assert.assertEquals("dr-xr-xr-x", FormatUtils.formatPermission((short) 0555, true));
    Assert.assertEquals("-rwxr-xr--", FormatUtils.formatPermission((short) 0754, false));
    Assert.assertEquals("drwxr-xr--", FormatUtils.formatPermission((short) 0754, true));
  }