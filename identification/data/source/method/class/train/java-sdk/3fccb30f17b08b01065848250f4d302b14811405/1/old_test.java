@Test(expected = IllegalArgumentException.class)
  public void testUpdateProfile() {
    service.updateProfile(null, null, null);
  }