  @Test
  public void getFatBeaconTitleTest() throws UnsupportedEncodingException {
    // Array length failure
    assertEquals("", EddystoneBeacon.getFatBeaconTitle(new byte[]{}));
    assertEquals("", EddystoneBeacon.getFatBeaconTitle(new byte[]{0x01}));
    assertEquals("", EddystoneBeacon.getFatBeaconTitle(new byte[]{0x01, 0x02}));

    // Invalid byte sequence
    assertEquals("", EddystoneBeacon.getFatBeaconTitle(new byte[]{0x01, 0x02, 0x00}));

    // Valid title
    String title = "title";
    byte[] titleBytes = title.getBytes("UTF-8");
    int length = titleBytes.length;
    byte[] serviceData = new byte[length + 3];
    System.arraycopy(titleBytes, 0, serviceData, 3, length);
    serviceData[0] = 0x10;
    serviceData[1] = 0x00;
    serviceData[2] = 0x0e;
    assertEquals(title, EddystoneBeacon.getFatBeaconTitle(serviceData));
  }