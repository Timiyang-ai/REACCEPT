@Test
  public void getAdvertisedMessageEncodings_noEncodingsAdvertised() {
    assertTrue(registry.getAdvertisedMessageEncodings().isEmpty());
  }