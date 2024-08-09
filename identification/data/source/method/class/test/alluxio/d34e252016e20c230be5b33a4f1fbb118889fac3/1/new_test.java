@Test
  public void byteArrayToHexStringTest() {
    Assert.assertEquals("", FormatUtils.byteArrayToHexString(new byte[0]));
    Assert.assertEquals("0x01", FormatUtils.byteArrayToHexString(new byte[]{1}));
    Assert.assertEquals("0x01 0xac", FormatUtils.byteArrayToHexString(new byte[]{1, (byte) 0xac}));
    Assert.assertEquals("01ac",
        FormatUtils.byteArrayToHexString(new byte[] {1, (byte) 0xac}, "", ""));
  }