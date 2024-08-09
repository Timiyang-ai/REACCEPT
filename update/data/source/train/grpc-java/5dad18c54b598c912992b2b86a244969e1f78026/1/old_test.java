@Test
  public void getAdvertisedMessageEncodings_noEncodingsAdvertised() {
    assertTrue(registry.internalGetAdvertisedMessageEncodings().isEmpty());
  }